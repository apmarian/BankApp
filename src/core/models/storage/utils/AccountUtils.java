/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage.utils;

import core.models.Account;
import core.models.storage.AccountStorage;
import java.util.ArrayList;

/**
 *
 * @author maria
 */
public class AccountUtils {

    public static ArrayList<Account> accountOrderbyId() {

        AccountStorage accountStorage = AccountStorage.getInstance();
        ArrayList<Account> accounts = accountStorage.getAccounts();
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
        accounts.sort((obj1, obj2) -> (obj1.getId().compareTo(obj2.getId())));
        return accounts;
    }
}
