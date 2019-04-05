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

/*
    | Stock class
    | This class holds information on a single stock
 */
public class Stock {
    private String Symbol; //Ex: FB
    private String Name; //Ex: Facebook
    private String Open; //Ex: 000.00
    private String High; //Ex: 000.00
    private String Low; //Ex: 000.00
    private String Close; //Ex: 000.00
    private String percentChange; //Ex: -0.4%
    private String statusClass; //text-danger, text-success or text-muted

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String Symbol) {
        this.Symbol = Symbol;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getOpen() {
        return Open;
    }

    public void setOpen(String Open) {
        this.Open = Open;
    }

    public String getHigh() {
        return High;
    }

    public void setHigh(String High) {
        this.High = High;
    }

    public String getLow() {
        return Low;
    }

    public void setLow(String Low) {
        this.Low = Low;
    }

    public String getClose() {
        return Close;
    }

    public void setClose(String Close) {
        this.Close = Close;
    }

    /*
        | getPercentChange
        | If no existing value is set, calculate the change between the close and open values
    */
    public String getPercentChange() {
        if(this.percentChange == null || this.percentChange.isEmpty()) {
            double open = Double.parseDouble(getOpen());
            double close = Double.parseDouble(getClose());
            double percent = ((close-open)/open)*100;
            String convertedPercent = String.format("%.2f", percent);
            setPercentChange(convertedPercent);
        }
        return percentChange;
    }

    public void setPercentChange(String percentChange) {
        this.percentChange = percentChange;
    }

    /*
        | getStatusClass
        | Checks to see if the return on the stock is positive, negative or same
        | Returns the appropiate bootstrap class for text colour to indicate status
    */
    public String getStatusClass() {
        if(this.statusClass == null || this.statusClass.isEmpty()) {
            double open = Double.parseDouble(getOpen());
            double close = Double.parseDouble(getClose());
            if(close > open) {
                setStatusClass("text-success");
            }
            else if(close < open) {
                setStatusClass("text-danger");
            }
            else {
                setStatusClass("text-muted");
            }
        }
        return statusClass;
    }

    public void setStatusClass(String statusClass) {
        this.statusClass = statusClass;
    }

}