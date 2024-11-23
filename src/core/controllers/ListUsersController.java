/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controller.utils.Response;
import core.controller.utils.Status;
import core.models.User;
import core.models.storage.UserStorage;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author maria
 */
public class ListUsersController {

    public static Response refreshUserList(DefaultTableModel model) {

        try {
            UserStorage userStorage = UserStorage.getInstance();
            ArrayList<User> users = userStorage.userOrderbyId();

            if (!users.isEmpty()) {
                model.setRowCount(0);
                for (User user : users) {
                    model.addRow(new Object[]{user.getId(), user.getFirstname() + " " + user.getLastname(), user.getAge(), user.getNumAccounts()});
                }
                return new Response("User list refreshed succesfully.", Status.OK);
            }else{
                return new Response("User list is empty.", Status.NO_CONTENT);
            }

        } catch (Exception ex) {
            return new Response("Unexpected error.", Status.INTERNAL_SERVER_ERROR);
        }
    }

}
