package com.masters.group.exercise2.models;

import com.masters.group.exercise2.models.BankAccount;
import com.masters.group.exercise2.models.CreditCard;
import com.masters.group.exercise2.models.Gcash;

public sealed interface PaymentMethod permits BankAccount, CreditCard, Gcash {

    public String getAccountDetails();
}
