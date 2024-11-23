/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models;

/**
 *
 * @author maria
 */
public interface AccountInterface {

    void withdraw(double amount);
    void deposit(double amount);
    double getBalance();
}
