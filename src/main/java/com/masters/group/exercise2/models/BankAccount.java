package com.masters.group.exercise2.models;

import com.masters.group.exercise2.models.PaymentMethod;
import com.masters.group.exercise2.models.SavingsAccount;

public sealed class BankAccount implements PaymentMethod permits CheckingAccount, SavingsAccount {

    @Override
    public String getAccountDetails() {
        return """
				Account nickname :\s%s
				Account Name:\s%s
				Account Number:\s%s
				Bank name:\s%s
				""".formatted(this.nickname, this.accountName, this.accountNumber, this.bankName);
    }

    protected String accountName;
    protected String nickname;
    protected String accountNumber;
    protected String bankName;

    public BankAccount(String accountName, String accountNumber, String bankName) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.nickname = "NA";
    }

    public BankAccount(String accountName, String accountNumber, String bankName, String nickname) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.nickname = nickname;
    }


}
