package com.masters.session6;

import java.util.random.RandomGeneratorFactory;

public record Gcash(String subscriberName, String mobileNumber) implements PaymentMethod {

    @Override
    public String getAccountDetails(){
        return """
               Subscriber Name:\s%s
               Mobile Number:\s%s
               """.formatted(subscriberName, mobileNumber);
    }
}
