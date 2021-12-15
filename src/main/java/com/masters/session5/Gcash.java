package com.masters.session5;

public record Gcash(String subscriberName, String mobileNumber) implements PaymentMethod {

    @Override
    public String getAccountDetails(){
        return """
               Subscriber Name:\s%s
               Mobile Number:\s%s
               """.formatted(subscriberName, mobileNumber);
    }

}
