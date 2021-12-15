package com.masters.session6;

public final class SavingsAccount extends BankAccount {

    @Override
    public String getAccountDetails(){
        return """
               Account\sName: Mau Tuazon
               Acoount\sNumber: 005412345678
               Bank Name: BDO
               """;
    }
}
