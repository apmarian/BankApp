/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.transaction;

import core.models.Account;

/**
 *
 * @author maria
 */
public interface TransactionStrategy {

    void execute(Account sourceAccount, Account destinationAccount, double amount);

}
