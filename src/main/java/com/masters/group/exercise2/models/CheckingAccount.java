package com.masters.group.exercise2.models;

public final class CheckingAccount extends BankAccount {

    @Override
    public String getAccountDetails(){
        return """
                Account nickname :\s%s
                Account Name:\s%s
                Account Number:\s%s
                Account Type: Checking
                Bank Name:\s%s
                """.formatted(this.nickname, this.accountName, this.accountNumber, this.bankName);
    }

    public CheckingAccount(String accountName, String accountNumber, String bankName) {
        super(accountName, accountNumber, bankName);
    }

    public CheckingAccount(String accountName, String accountNumber, String bankName, String nickname) {
        super(accountName, accountNumber, bankName, nickname);
    }


}
