/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controller.utils.Response;
import core.controller.utils.Status;
import core.models.transaction.Transaction;
import core.models.storage.TransactionStorage;
import core.models.storage.utils.TransactionUtils;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author maria
 */
public class ListTransactionsController {

    public static Response refreshTransactionList(DefaultTableModel model) {

        try {
            TransactionStorage transactionStorage = TransactionStorage.getInstance();
            ArrayList<Transaction> transactionsCopy = TransactionUtils.transactionsOrdered();

            if (!transactionsCopy.isEmpty()) {
                model.setRowCount(0);
                for (Transaction transaction : transactionsCopy) {
                    model.addRow(new Object[]{transaction.getType().name(), (transaction.getSourceAccount() != null ? transaction.getSourceAccount().getId() : "None"), (transaction.getDestinationAccount() != null ? transaction.getDestinationAccount().getId() : "None"), transaction.getAmount()});
                }
                return new Response("Transaction list refreshed succesfully.", Status.OK);
            } else {
                return new Response("Transaction list is empty.", Status.NO_CONTENT);
            }

        } catch (Exception ex) {
            return new Response("Unexpected error. ", Status.INTERNAL_SERVER_ERROR);
        }
    }

}
