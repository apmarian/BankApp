/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Account;
import core.models.Transaction;
import core.models.User;
import java.util.ArrayList;

/**
 *
 * @author maria
 */
public class Storage {

    // Instancia Singleton
    private static Storage instance;

    // Atributos del Storage
    private ArrayList<User> users;
    private ArrayList<Account> accounts;
    private ArrayList<Transaction> transactions;

    private Storage() {
        this.users = new ArrayList<>();
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    //User 
    public boolean createUser(User user) {
        for (User u : this.users) {
            if (u.getId() == user.getId()) {
                return false;
            }
        }
        this.users.add(user);
        return true;
    }

    public User getUser(int id) {
        for (User user : this.users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    // Account 
    public boolean createAccount(Account account) {
        for (Account a : this.accounts) {
            if (a.getId() == account.getId()) { // hacer metodo get ID en accounts 
                return false;
            }
        }
        this.accounts.add(account);
        return true;
    }

    public Account getAccount(String id) {
        for (Account account : this.accounts) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }

    // Transaction 
    public Transaction executeTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        return transaction; 
    }

}
