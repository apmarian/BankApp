/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.transaction;

import java.util.ArrayList;

/**
 *
 * @author maria
 */
public interface TransactionStorageInterface {

    Transaction executeTransaction(Transaction transaction);
    ArrayList<Transaction> getTransactions();
}
