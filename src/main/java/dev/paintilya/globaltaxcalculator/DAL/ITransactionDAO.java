package dev.paintilya.globaltaxcalculator.DAL;

import dev.paintilya.globaltaxcalculator.BLL.Model.Transaction;

public interface ITransactionDAO {
    public void addTransaction(Transaction transaction);
}
