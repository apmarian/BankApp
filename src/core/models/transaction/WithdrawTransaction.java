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
public class WithdrawTransaction implements TransactionStrategy {

    @Override
    public TransactionType getTransactionType() {
        return TransactionType.WITHDRAW;  
    }

    @Override
    public void execute(Account sourceAccount, Account destinationAccount, double amount) {
        sourceAccount.withdraw(amount);  
    }
}
