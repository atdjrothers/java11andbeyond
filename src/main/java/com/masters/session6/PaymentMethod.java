package com.masters.session6;

public sealed interface PaymentMethod permits BankAccount, CreditCard, Gcash, CustomPaymentMethod {

    String getAccountDetails();
}
