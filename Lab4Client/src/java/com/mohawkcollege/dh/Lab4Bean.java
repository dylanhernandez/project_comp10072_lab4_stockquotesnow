/*
    |---------------------------------------
    | Statement of Authorship
    |---------------------------------------
    | I Dylan Hernandez, 000307857 certify that this material is my original work.
    | No other person's work has been used without due acknowledgement.
    | I have not made my work available to anyone else.
    |---------------------------------------
*/
package com.mohawkcollege.dh;
import ca.mohawkcollege.my.StockWebService_Service;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.WebServiceRef;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Named(value = "lab4Bean")
@SessionScoped
public class Lab4Bean implements Serializable {

    @WebServiceRef(wsdlLocation = "http://localhost:8080/StockPricer/StockWebService?wsdl")
    private StockWebService_Service service;

    private String addStock; //This is the text that holds the stock input
    private String addStockXML; //The converted xml of the input
    private String errorMessage; //All error messages will be stored here
    private List<Stock> stocks = new ArrayList<>(); //List will hold the stocks
    
    public Lab4Bean() {}

    public String getAddStock() {
        return addStock;
    }

    public void setAddStock(String addStock) {
        this.addStock = addStock;
    }
    
    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public String getAddStockXML() {
        return addStockXML;
    }

    public void setAddStockXML(String addStockXML) {
        this.addStockXML = addStockXML;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    /*
        | addStockToList
        | This is the function that is called when the add stock form submits
        | Removes any detected whitespace first and then separates all symbols with ','
        | Loops through all seperated stocks
        | Builds xml and error strings where appopiate
        | Adds all valid stocks
    */
    public void addStockToList() {  
        setErrorMessage("");
        this.addStock = this.addStock.replaceAll("\\s+","");
        String totalXmlResults = "";
        String buildErrorMessage = "";
        String[] stockListArray = this.addStock.split(",");
        for(String stockValue : stockListArray) {
            String xmlResults = getCurrentPrice(stockValue);
            totalXmlResults += xmlResults;
            Stock newStock = parseXML(xmlResults);
            if(newStock != null) {
                this.stocks.add(newStock);
            }
            else{
                buildErrorMessage += stockValue + " is not a valid symbol.\n";
            }
        }
        setAddStockXML(totalXmlResults);
        if(!buildErrorMessage.equals("")) {
            setErrorMessage(buildErrorMessage);
        }   
    }
    
    /*
        | removeStockFromList
        | checks if the provided stock exists
        | delete stock from the list
    */
    public void removeStockFromList(Stock record) {
        if(record != null) {
            clearStrings();
            this.stocks.remove(record);
        }
    }
    
    /*
        | clearAll
        | Clears stock list
        | Also clears input, messages and xml
    */
    public void clearAll() {
        clearStrings();
        this.stocks.clear();
    }
    
    /*
        | clearStrings
        | Clears input, text and xml
    */
    public void clearStrings() {
        setAddStock("");
        setAddStockXML("");
        setErrorMessage("");
    }
    
    private String getCurrentPrice(java.lang.String symbol) {
        ca.mohawkcollege.my.StockWebService port = service.getStockWebServicePort();
        return port.getCurrentPrice(symbol);
    }
    
    private Stock parseXML(String xmlDoc) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        xmlDoc = xmlDoc.replaceAll("&", "&amp;");
        xmlDoc = xmlDoc.replaceAll("\"", "&quot;");
        xmlDoc = xmlDoc.replaceAll("\'", "&apos;");
        
        Document document;
        Stock parsedStock = new Stock();
        try
        {
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream is = new ByteArrayInputStream(xmlDoc.getBytes());
            document = builder.parse(is);
            Element root = document.getDocumentElement();
            NodeList nList = document.getElementsByTagName("StockData");
            for (int temp = 0; temp < nList.getLength(); temp++)
            {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) nNode;
                    //Tags are assigned to hard-coded properties in the Stock class
                    parsedStock.setSymbol(getTagValue("Symbol", eElement));
                    parsedStock.setName(getTagValue("Name", eElement));
                    parsedStock.setOpen(getTagValue("Open", eElement));
                    parsedStock.setHigh(getTagValue("High", eElement));
                    parsedStock.setLow(getTagValue("Low", eElement));
                    parsedStock.setClose(getTagValue("Close", eElement));
                }
            }
        } 
        catch (Exception e)
        {
            System.out.println("Exception Occured:");
            System.out.println(e.getMessage());
            return null;
        }
        return parsedStock;
    }
    
    private String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        return nValue.getNodeValue();
    }
    
}
