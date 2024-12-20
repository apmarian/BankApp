/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.transaction;

import core.models.Account;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author edangulo
 */
public class Transaction {

    private TransactionType type;
    private Account sourceAccount;
    private Account destinationAccount;
    private double amount;
    private TransactionStrategy strategy;

    public Transaction(TransactionStrategy strategy, Account sourceAccount, Account destinationAccount, double amount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.strategy = strategy;
        this.type = strategy.getTransactionType();
    }

    public TransactionType getType() {
        return type;
    }

    public TransactionStrategy getStrategy() {
        return strategy;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public double getAmount() {
        return amount;
    }

}
