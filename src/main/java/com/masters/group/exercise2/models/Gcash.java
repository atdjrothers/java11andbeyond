package com.masters.group.exercise2.models;

import com.masters.group.exercise2.models.PaymentMethod;

public record Gcash(String subscriberName, String mobileNumber, String nickname) implements PaymentMethod {

    @Override
    public String getAccountDetails(){
        return """
                Account nickname: %s
                Subscriber name:\s%s
                Mobile Number:\s%s
                Account Type: Gcash
                """.formatted(nickname, subscriberName, mobileNumber);
    }

    public Gcash(String subscriberName, String mobileNumber) {
        this(subscriberName, mobileNumber, "NA");
    };


}
