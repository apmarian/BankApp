/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.transaction;

import core.models.Account;

/**
 *
 * @author maria
 */
public class TransferTransaction implements TransactionStrategy {

    @Override
    public TransactionType getTransactionType() {
        return TransactionType.TRANSFER;  
    }

    @Override
    public void execute(Account sourceAccount, Account destinationAccount, double amount) {
        sourceAccount.deposit(amount);
        destinationAccount.withdraw(amount);
    }
}
