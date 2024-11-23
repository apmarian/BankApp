/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.models.User;
import java.util.ArrayList;

/**
 *
 * @author edangulo
 */

public  class Account implements AccountInterface {

    private String id;
    private UserInterface owner;
    private double balance;

    public Account(String id, UserInterface owner) {
        this.id = id;
        this.owner = owner;
        this.balance = 0;
    }

    public Account(String id, UserInterface owner, double balance) {
        this.id = id;
        this.owner = owner;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public UserInterface getOwner() {
        return owner;
    }

    @Override
    public void withdraw(double amount) {
        balance -= amount;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public double getBalance() {
        return balance;
    }

}
