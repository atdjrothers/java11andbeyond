package com.masters.session6;


import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class Exercise6 {

    public static void main(String[] args){

        final List<PaymentMethod> paymentMethodList = new ArrayList<>();
        paymentMethodList.add(new BankAccount());
        paymentMethodList.add(new CheckingAccount());
        paymentMethodList.add(new CreditCard());
        paymentMethodList.add(new Gcash("Mau Tuazon", "09171234567"));
        paymentMethodList.add(new CustomPaymentMethod());
        paymentMethodList.add(null);

        RandomGenerator randomGenerator = RandomGeneratorFactory.of("Xoshiro256PlusPlus").create(999);
        paymentMethodList.forEach(paymentMethod -> {

            String type = switch (paymentMethod) {
                case CheckingAccount checkingAccount -> "Checking Account";
                case CreditCard creditCard -> "CreditCard";
                case CustomPaymentMethod customPaymentMethod -> "Custom Payment Method";
                case Gcash gcash -> "Gcash";
                case SavingsAccount savingsAccount -> "Savings Account";
                case BankAccount bankAccount -> "Bank Account";
                default -> "null payment method";

            };

            if (type == "null payment method") {
                System.out.println(type);
            }

            System.out.println("""
                Randomly Generated Index: %d
                %s
                %s
                """.formatted(randomGenerator.nextInt(11), type, paymentMethod.getAccountDetails()));
        });
    }
}
