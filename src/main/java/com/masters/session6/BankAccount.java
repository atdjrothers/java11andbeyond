package com.masters.session6;

public sealed class BankAccount implements PaymentMethod permits CheckingAccount, SavingsAccount {

    @Override
    public String getAccountDetails(){
        return """
               Account\sName: Mau Tuazon
               Acoount\sNumber: 005412345678
               Bank Name: BDO
               """;
    }
}
