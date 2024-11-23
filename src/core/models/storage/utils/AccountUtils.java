/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage.utils;

import core.models.Account;
import java.util.ArrayList;

/**
 *
 * @author maria
 */
public class AccountUtils {

    private static ArrayList<Account> accounts;

    public static ArrayList<Account> accountOrderbyId() {
        accounts.sort((obj1, obj2) -> (obj1.getId().compareTo(obj2.getId())));
        return accounts;
    }
}
