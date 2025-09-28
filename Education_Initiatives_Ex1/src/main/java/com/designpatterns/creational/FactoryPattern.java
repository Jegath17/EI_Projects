package com.designpatterns.creational;

import com.designpatterns.utils.UserInput;

interface Vehicle {
    void drive();
}

class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a car");
    }
}

class Bike implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Riding a bike");
    }
}

class VehicleFactory {
    public Vehicle createVehicle(String type) {
        return switch (type.toLowerCase()) {
            case "car" -> new Car();
            case "bike" -> new Bike();
            default -> throw new IllegalArgumentException("Unknown vehicle type");
        };
    }
}

public class FactoryPattern {
    public static void run() {
        VehicleFactory factory = new VehicleFactory();
        System.out.println("Choose vehicle type:\n1. Car\n2. Bike");
        int choice = UserInput.getInt("Enter your choice: ");
        Vehicle vehicle = switch (choice) {
            case 1 -> factory.createVehicle("car");
            case 2 -> factory.createVehicle("bike");
            default -> {
                System.out.println("Invalid choice! Defaulting to Car.");
                yield factory.createVehicle("car");
            }
        };
        vehicle.drive();
    }
}
