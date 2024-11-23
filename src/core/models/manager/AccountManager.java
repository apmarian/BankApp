/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.manager;

import core.models.Account;
import core.models.User;

/**
 *
 * @author maria
 */
public class AccountManager {

    public static void linkAccountToUser(Account account, User user) {
        user.addAccount(account);
    }
}
