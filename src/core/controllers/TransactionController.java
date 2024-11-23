/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controller.utils.Response;
import core.controller.utils.Status;
import core.models.Account;
import core.models.transaction.Transaction;
import core.models.transaction.TransactionType;
import core.models.storage.AccountStorage;
import core.models.storage.TransactionStorage;
import core.models.transaction.DepositTransaction;
import core.models.transaction.TransferTransaction;
import core.models.transaction.WithdrawTransaction;
import java.util.ArrayList;

public class TransactionController {

    public static Response deposit(String amount, String destinationAccountId) {

        try {
            double moneyAmount;
            try {
                if (amount.equals("")) {
                    return new Response("Amount cannot be empty", Status.BAD_REQUEST);
                }

                moneyAmount = Double.parseDouble(amount);
                if (moneyAmount <= 0) {
                    return new Response("Amount must be greater than zero", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Amount must be numeric", Status.BAD_REQUEST);
            }

            TransactionStorage transactionStorage = TransactionStorage.getInstance();
            AccountStorage accountStorage = AccountStorage.getInstance();
            Account destinationAccount = null;
            for (Account account : accountStorage.getAccounts()) {
                if (account.getId().equals(destinationAccountId)) {
                    destinationAccount = account;
                }
            }
            if (destinationAccount == null) {
                return new Response("This destination account does not exist.", Status.BAD_REQUEST);
            } 

            Transaction transaction = new Transaction(new DepositTransaction(), null, destinationAccount, moneyAmount);
            transaction.getStrategy().execute(transaction.getSourceAccount(), transaction.getDestinationAccount(), transaction.getAmount());
            transactionStorage.executeTransaction(transaction); 
            return new Response("Transaction completed.", Status.CREATED, transaction);

        } catch (Exception ex) {
            return new Response("Unexpected error: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response withdraw(String amount, String sourceAccountId) {
        try {
            TransactionStorage transactionStorage = TransactionStorage.getInstance();
            AccountStorage accountStorage = AccountStorage.getInstance();

            Account sourceAccount = null;
            for (Account account : accountStorage.getAccounts()) {
                if (account.getId().equals(sourceAccountId)) {
                    sourceAccount = account;
                    break;
                }
            }

            if (sourceAccount == null) {
                return new Response("Account not found.", Status.NOT_FOUND);
            }

            double moneyAmount;
            try {
                moneyAmount = Double.parseDouble(amount);
            } catch (NumberFormatException e) {
                return new Response("Amount must be numeric.", Status.BAD_REQUEST);
            }

            if (moneyAmount <= 0) {
                return new Response("Amount must be greater than 0.", Status.BAD_REQUEST); 
            }
            if (moneyAmount > sourceAccount.getBalance()) {
                return new Response("Insufficient funds.", Status.BAD_REQUEST);
            }

            Transaction transaction = new Transaction(new WithdrawTransaction(), sourceAccount, null, moneyAmount);
            transaction.getStrategy().execute(transaction.getSourceAccount(), null, transaction.getAmount());
            transactionStorage.executeTransaction(transaction);
            return new Response("Transaction completed.", Status.CREATED, transaction);

        } catch (Exception ex) {
            return new Response("Unexpected error: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response transfer(String sourceAccountId, String destinationAccountId, String amount) {
        try {
            TransactionStorage transactionStorage = TransactionStorage.getInstance();
            AccountStorage accountStorage = AccountStorage.getInstance();
            ArrayList<Account> accounts = accountStorage.getAccounts();
            Double amountD;
            Account sourceAccount = null;
            Account destinationAccount = null;

            if (sourceAccountId == null || sourceAccountId.trim().isEmpty()) {
                return new Response("Source Account ID cannot be empty.", Status.BAD_REQUEST);
            }

            if (destinationAccountId == null || destinationAccountId.trim().isEmpty()) {
                return new Response("Destination Account ID cannot be empty.", Status.BAD_REQUEST);
            }

            try {
                if (amount.equals("")) {
                    return new Response("Amount cannot be empty", Status.BAD_REQUEST);
                }
                amountD = Double.parseDouble(amount);
                if (amountD <= 0) {
                    return new Response("Amount must be greater than zero", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Amount must be numeric", Status.BAD_REQUEST);
            }

            for (Account account : accountStorage.getAccounts()) {
                if (account.getId().equals(sourceAccountId)) {
                    sourceAccount = account;
                }
            }
            for (Account account : accountStorage.getAccounts()) {
                if (account.getId().equals(destinationAccountId)) {
                    destinationAccount = account;
                }
            }
            if (sourceAccount == null || destinationAccount == null) {
                return new Response("Source or destination account not found.", Status.NOT_FOUND);
            }

            if (amountD > sourceAccount.getBalance()) {
                return new Response("Insufficient funds.", Status.BAD_REQUEST);
            }

            Transaction transaction = new Transaction(new TransferTransaction(), sourceAccount,destinationAccount,amountD);
            transaction.getStrategy().execute(transaction.getSourceAccount(), transaction.getDestinationAccount(), transaction.getAmount());
            transactionStorage.executeTransaction(transaction); 

            return new Response("Transaction completed.", Status.OK, transaction);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
