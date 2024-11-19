/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controller.utils.Response;
import core.controller.utils.Status;
import core.models.Account;
import core.models.User;
import core.models.storage.Storage;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author maria
 */
public class AccountController {

    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Account> accounts;

    public static Response createAccount(String userId, String initialBalance) {

        try {
            int userIdInt;
            double initialBalanceD;
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
                    return new Response("Initial balance must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            //validacion de que las cuentas solo se puedan crear para los usuarios previamente registrados en la plataforma.
            try {
                userIdInt = Integer.parseInt(userId);
                initialBalanceD = Double.parseDouble(initialBalance);
                User selectedUser = null;
                for (User user : users) {
                    if (user.getId() == userIdInt && selectedUser == null) {
                        selectedUser = user;
                        break; // se encuentra el usuario
                    }
                }

                // id de cuenta debe ser unico, generado aleatoriamente 
                if (selectedUser != null) {
                    String accountId = null;
                    boolean isUnique = false;
                    Random random = new Random();

                    while (!isUnique) {
                        int first = random.nextInt(1000);
                        int second = random.nextInt(1000000);
                        int third = random.nextInt(100);
                        accountId = String.format("%03d", first) + "-" + String.format("%06d", second) + "-" + String.format("%02d", third);

                        isUnique = true;
                        for (Account account : accounts) {
                            if (account.getId().equals(accountId)) {
                                isUnique = false;
                                break;
                            }
                        }

                    }
                }

            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            return new Response("Account created successfully", Status.CREATED);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }

    }


}
