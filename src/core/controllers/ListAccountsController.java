/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controller.utils.Response;
import core.controller.utils.Status;
import core.models.Account;
import core.models.storage.AccountStorage;
import core.models.storage.utils.AccountUtils;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author maria
 */
public class ListAccountsController {

    public static Response refreshAccountList(DefaultTableModel model) {

        try {
            AccountStorage accountStorage = AccountStorage.getInstance();
            ArrayList<Account> accounts = AccountUtils.accountOrderbyId();

            if (!accounts.isEmpty()) {
                model.setRowCount(0);
                //cargar tabla 
                for (Account account : accounts) {
                    model.addRow(new Object[]{account.getId(), account.getOwner().getId(), account.getBalance()});
                }
                return new Response("Account list refreshed succesfully.", Status.OK);
            } else {
                return new Response("Account list is empty.", Status.NO_CONTENT);
            }

        } catch (Exception ex) {
            return new Response("Unexpected error.", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
