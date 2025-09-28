package com.designpatterns.behavioral;

import com.designpatterns.utils.UserInput;

interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal");
    }
}

class UpiPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using UPI");
    }
}

class PaymentProcessor {
    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void executePayment(int amount) {
        strategy.pay(amount);
    }
}

public class StrategyPattern {
    public static void run() {
        PaymentProcessor processor = new PaymentProcessor();
        System.out.println("Choose payment method:\n1. Credit Card\n2. PayPal\n3. UPI");
        int choice = UserInput.getInt("Enter your choice: ");
        int amount = UserInput.getInt("Enter amount to pay: ");

        switch (choice) {
            case 1 -> processor.setStrategy(new CreditCardPayment());
            case 2 -> processor.setStrategy(new PayPalPayment());
            case 3 -> processor.setStrategy(new UpiPayment());
            default -> {
                System.out.println("Invalid choice! Defaulting to Credit Card.");
                processor.setStrategy(new CreditCardPayment());
            }
        }
        processor.executePayment(amount);
    }
}
