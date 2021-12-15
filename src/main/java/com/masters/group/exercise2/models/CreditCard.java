package com.masters.group.exercise2.models;

import com.masters.group.exercise2.models.PaymentMethod;

public final class CreditCard implements PaymentMethod {

    @Override
    public String getAccountDetails(){
        return """
                Account nickname :\s%s
                Name on Card:\s%s
                Credit Card number:\s%s
                Account Type: Credit Card
                Expiry Date:\s%s
                """.formatted(this.nickname, this.cardName, this.cardNumber, this.expiryDate);
    }

    private String cardName;
    private String cardNumber;
    private String expiryDate;
    private String nickname;

    public CreditCard(String cardName, String cardNumber, String expiryDate) {
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.nickname = "NA";
    }
    public CreditCard(String cardName, String cardNumber, String expiryDate, String nickname) {
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.nickname = nickname;
    }
}
