/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage.utils;

import core.models.transaction.Transaction;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author maria
 */
public class TransactionUtils {

    private static ArrayList<Transaction> transactions;

    public static ArrayList<Transaction> transactionsOrdered() {
        ArrayList<Transaction> transactionsCopy = (ArrayList<Transaction>) transactions.clone();
        Collections.reverse(transactionsCopy);
        return transactionsCopy;
    }
}
