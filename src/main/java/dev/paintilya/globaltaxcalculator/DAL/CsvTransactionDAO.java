package dev.paintilya.globaltaxcalculator.DAL;

import dev.paintilya.globaltaxcalculator.BLL.Model.Transaction;

import java.io.FileWriter;
import java.io.IOException;

public class CsvTransactionDAO implements ITransactionDAO {
    private String filename;

    public CsvTransactionDAO(String filename) {
        this.filename = filename;
    }

    public void addTransaction(Transaction transaction) {
        try {
            FileWriter writer = new FileWriter(filename, true);
            String csvLine = transaction.getDate() + "," + transaction.getGrossRevenue() + "," + transaction.getTax() + "\n";
            writer.write(csvLine);
            writer.close();
        } catch (IOException e) {
            System.out.println("Erreur lors de l'enregistrement de la transaction: " + e.getMessage());
        }
    }
}