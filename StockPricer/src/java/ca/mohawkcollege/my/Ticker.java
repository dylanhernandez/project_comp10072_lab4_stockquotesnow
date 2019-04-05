package ca.mohawkcollege.my;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

/**
 * Used to encapsulate single daily data for a Stock
 *
 * @author mark.yendt
 */
public class Ticker {

    private String ticker;
    private String companyName;
    private Calendar date;
    private double open;
    private double high;
    private double low;
    private double close;
    private int volume;

    public Ticker(String compName, String symbol, String priceDate, double open, double high, double low, double close, int volume) {
        companyName = compName;
        ticker = symbol;
        date = Calendar.getInstance();
        date.set(Integer.valueOf(priceDate.substring(0, 4)),
                Integer.valueOf(priceDate.substring(4, 6))-1,
                Integer.valueOf(priceDate.substring(6, 8)));
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    /**
     * Used for searching
     *
     * @param symbol the symbol to search
     */
    public Ticker(String symbol) {
        ticker = symbol;
    }

    @Override
    public boolean equals(Object other) {
        if (getClass() == other.getClass()
                && other != null) {
            Ticker oTicker = (Ticker) other;
            return ticker.equals(oTicker.ticker);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.ticker);
        return hash;
    }

    /* Property accessors */
    public String getTicker() {
        return ticker;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date.getTime());            
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public int getVolume() {
        return volume;
    }

    public String toXMLString() {
        return "<StockData>"
                + "<Symbol>" + getTicker() + "</Symbol>"
                + "<Name>" + getCompanyName() + "</Name>"
                + "<Date>" + getDate() + "</Date>"
                + "<Open>" + getOpen() + "</Open>"
                + "<High>" + getHigh() + "</High>"
                + "<Low>" + getLow() + "</Low>"
                + "<Close>" + getClose() + "</Close>"
                + "<Volume>" + getVolume() + "</Volume>"
                + "</StockData>";
    }

    public static String emptyXMLString(String symbol) {
        return "<StockData>"
                + "<Symbol>" + symbol + "</Symbol>"
                + "<Name>" + "No suck Ticker" + "</Name>"
                + "</StockData>";
    }

}
