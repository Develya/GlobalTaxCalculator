package dev.paintilya.globaltaxcalculator.BLL.Model;

public class Transaction {
    private String date;
    private double grossRevenue, tax;

    public Transaction() {}

    public Transaction(String date, double grossRevenue, double tax) {
        this.date = date;
        this.grossRevenue = grossRevenue;
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date='" + date + '\'' +
                ", grossRevenue=" + grossRevenue +
                ", tax=" + tax +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getGrossRevenue() {
        return grossRevenue;
    }

    public void setGrossRevenue(double grossRevenue) {
        this.grossRevenue = grossRevenue;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }
}
