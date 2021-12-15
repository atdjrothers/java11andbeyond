package com.masters.session5;


import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public sealed interface PaymentMethod permits BankAccount, CreditCard, Gcash, CustomPaymentMethod {

    String getAccountDetails();
}
