package dev.paintilya.globaltaxcalculator.FEL;

import dev.paintilya.globaltaxcalculator.BLL.Control.GlobalTaxController;
import dev.paintilya.globaltaxcalculator.DAL.CsvTransactionDAO;

import java.util.Map;


public class AppConsoleDriver {
    public static void main(String[] args) {
        GlobalTaxController globalTaxController = new GlobalTaxController(new CsvTransactionDAO("/home/kiyranya/IdeaProjects/TaxCalculator/GlobalTaxCalculator/src/main/resources/transactions.csv"));
        Map<String, Double> response = globalTaxController.calculateGlobalTax(50000);
        response.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}