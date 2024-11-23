/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controller.utils.Response;
import core.controller.utils.Status;
import core.models.Account;
import core.models.User;
import core.models.manager.AccountManager;
import core.models.storage.AccountStorage;
import core.models.storage.Storage;
import core.models.storage.UserStorage;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author maria
 */
public class AccountController {

    public static Response createAccount(String userId, String initialBalance) {

        try {

            int userIdInt = 0;
            double initialBalanceD;
            String accountId = null;
            User selectedUser = null;
            Random random = new Random();
            int first = random.nextInt(1000);
            int second = random.nextInt(1000000);
            int third = random.nextInt(100);

            AccountStorage accountStorage = AccountStorage.getInstance();
            ArrayList<Account> accounts = accountStorage.getAccounts();
            UserStorage userStorage = UserStorage.getInstance();
            ArrayList<User> users = userStorage.getUsers();

            //validacion Id si es negativo o menor que 0
            try {
                userIdInt = Integer.parseInt(userId);
                if (userIdInt < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            //validacion de que el monto sea mayor q 0 
            try {
                initialBalanceD = Double.parseDouble(initialBalance);
                if (initialBalanceD < 0) {
                    return new Response("Initial balance must be zero or greater than zero.", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Initial balance must be numeric.", Status.BAD_REQUEST);
            }

            //validacion de que las cuentas solo se puedan crear para los usuarios previamente registrados en la plataforma.
            for (User user : users) {
                if (user.getId() == userIdInt) {
                    selectedUser = user;  // Usuario encontrado

                }
            }

            if (selectedUser == null) {
                return new Response("User not registered.", Status.BAD_REQUEST);
            }

            accountId = String.format("%03d", first) + "-" + String.format("%06d", second) + "-" + String.format("%02d", third);

            //que no haya un id account igual 
            for (Account account : accounts) {
                if (account.getId().equals(accountId)) {
                    return new Response("There is already a registered account with this id.", Status.BAD_REQUEST);
                }
            }
            Account newAccount = new Account(accountId, selectedUser, initialBalanceD);
            AccountManager.linkAccountToUser(newAccount, selectedUser);
            accountStorage.createAccount(newAccount);
            
            return new Response("Account created successfully", Status.OK);
        } catch (Exception ex) {
            return new Response("Unexpected error.", Status.INTERNAL_SERVER_ERROR);
        }

    }
}
