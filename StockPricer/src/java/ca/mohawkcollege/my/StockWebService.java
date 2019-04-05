/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.mohawkcollege.my;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.servlet.ServletContext;

/**
 *
 * @author mark.yendt
 */
@WebService(serviceName = "StockWebService")
public class StockWebService {

    private static final String STOCK_DATA_FILE = "ca/mohawkcollege/my/resources/stockprices.txt";
    private static ArrayList<Ticker> stockData = new ArrayList<Ticker>();
    private static boolean loadedData = false;

    // Loads on first instance 
    void loadData() {
        try {

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(STOCK_DATA_FILE);
            System.out.println("Input Stream = " + inputStream);
            InputStreamReader reader = new InputStreamReader(inputStream);
            Scanner fin = new Scanner(reader);
            fin.useDelimiter("\\,|\\n");  // Filter - DO NOT CHANGE 
            //<name>,<ticker>,<date>,<open>,<high>,<low>,<close>,<vol>
            while (fin.hasNext()) {
                String companyName = fin.next();
                String symbol = fin.next();
                String date = fin.next();
                double open = fin.nextDouble();
                double high = fin.nextDouble();
                double low = fin.nextDouble();
                double close = fin.nextDouble();
                int vol = fin.nextInt();
                stockData.add(new Ticker(companyName, symbol, date, open, high, low, close, vol));

            }
            fin.close();
        } catch (Exception e) {
            System.err.println("StockWebService - Exception caught: " + e.getMessage());
        }
        loadedData = true;

    }

    @WebMethod(operationName = "getCurrentPrice")
    public String currentPrice(@WebParam(name = "symbol") String stockSymbol) {
        if (!StockWebService.loadedData) {
            loadData();
        }

        int index = stockData.indexOf(new Ticker(stockSymbol));

        if (index > -1) {
            return stockData.get(index).toXMLString();
        }

        return Ticker.emptyXMLString(stockSymbol);
    }

}
