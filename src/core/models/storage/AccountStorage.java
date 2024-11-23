/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Account;
import core.models.User;
import java.util.ArrayList;

/**
 *
 * @author maria
 */
public class AccountStorage extends Storage implements AccountStorageInterface{

    private ArrayList<Account> accounts;
    private static AccountStorage accountStorage;

    public static AccountStorage getInstance() {
        if (accountStorage == null) {
            accountStorage = new AccountStorage();
        }
        return accountStorage;
    }
    
    private AccountStorage() {
        accounts = new ArrayList<>();  // Aseguramos que accounts nunca sea null
    }

    public boolean createAccount(Account account) {
        for (Account a : this.accounts) {
            if (a.getId() == account.getId()) {
                return false;
            }
        }
        this.accounts.add(account);
        return true;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

}
