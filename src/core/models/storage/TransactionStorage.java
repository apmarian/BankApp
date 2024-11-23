/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.transaction.Transaction;
import core.models.transaction.TransactionStorageInterface;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author maria
 */
public class TransactionStorage extends Storage implements TransactionStorageInterface {

    private ArrayList<Transaction> transactions;
    private static TransactionStorage transactionStorage;

    public static TransactionStorage getInstance() {
        if (transactionStorage == null) {
            transactionStorage = new TransactionStorage();
        }
        return transactionStorage;
    }

    private TransactionStorage() {
        this.transactions = new ArrayList<>();
    }

    public Transaction executeTransaction(Transaction transaction) {
        if (this.transactions == null) {
            this.transactions = new ArrayList<>();
        }
        this.transactions.add(transaction);
        return transaction;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

}
