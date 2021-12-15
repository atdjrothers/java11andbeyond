package com.masters.session6;

public final class CreditCard implements PaymentMethod {

    @Override
    public String getAccountDetails(){
        return """
               Name on Card: Mau Tuazon
               Credit Card Number: 402123456789012
               Expiry Date: "12/2022"
               """;
    }
}
