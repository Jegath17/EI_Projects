package com.designpatterns.structural;

import com.designpatterns.utils.UserInput;

interface Coffee {
    double getCost();
    String getDescription();
}

class SimpleCoffee implements Coffee {
    @Override
    public double getCost() {
        return 2.0;
    }

    @Override
    public String getDescription() {
        return "Simple coffee";
    }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public double getCost() {
        return coffee.getCost();
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) { super(coffee); }

    @Override
    public double getCost() { return super.getCost() + 0.5; }

    @Override
    public String getDescription() { return super.getDescription() + ", with milk"; }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) { super(coffee); }

    @Override
    public double getCost() { return super.getCost() + 0.3; }

    @Override
    public String getDescription() { return super.getDescription() + ", with sugar"; }
}

public class DecoratorPattern {
    public static void run() {
        Coffee coffee = new SimpleCoffee();
        System.out.println("Do you want milk? (1-Yes / 0-No)");
        if (UserInput.getInt("Choice: ") == 1) coffee = new MilkDecorator(coffee);
        System.out.println("Do you want sugar? (1-Yes / 0-No)");
        if (UserInput.getInt("Choice: ") == 1) coffee = new SugarDecorator(coffee);

        System.out.printf("%s: $%.2f%n", coffee.getDescription(), coffee.getCost());
    }
}
