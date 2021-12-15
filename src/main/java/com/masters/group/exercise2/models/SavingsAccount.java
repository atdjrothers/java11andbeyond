package com.masters.group.exercise2.models;

import com.masters.group.exercise2.models.BankAccount;

public final class SavingsAccount extends BankAccount {

    @Override
    public String getAccountDetails(){
        return """
                Account nickname :\s%s
                Account Name:\s%s
                Account Number:\s%s
                Account Type: Savings
                Bank Name:\s%s
                """.formatted(this.nickname, this.accountName, this.accountNumber, this.bankName);
    }

    public SavingsAccount(String accountName, String accountNumber, String bankName) {
        super(accountName, accountNumber, bankName);
    }

    public SavingsAccount(String accountName, String accountNumber, String bankName, String nickname) {
        super(accountName, accountNumber, bankName, nickname);
    }


}
