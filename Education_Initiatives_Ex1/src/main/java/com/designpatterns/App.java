package com.designpatterns;

import com.designpatterns.behavioral.ObserverPattern;
import com.designpatterns.behavioral.StrategyPattern;
import com.designpatterns.creational.FactoryPattern;
import com.designpatterns.creational.SingletonPattern;
import com.designpatterns.structural.AdapterPattern;
import com.designpatterns.structural.DecoratorPattern;
import com.designpatterns.utils.Logger;
import com.designpatterns.utils.UserInput;

public class App {
    public static void main(String[] args) {
        Logger.log("Design Patterns Demo");
        boolean running = true;
        while (running) {
            Logger.log("\nChoose a pattern to demo:");
            Logger.log("1. Observer Pattern");
            Logger.log("2. Strategy Pattern");
            Logger.log("3. Factory Pattern");
            Logger.log("4. Singleton Pattern");
            Logger.log("5. Adapter Pattern");
            Logger.log("6. Decorator Pattern");
            Logger.log("0. Exit");
            int choice = UserInput.getInt("Enter your choice: ");
            switch (choice) {
                case 1: ObserverPattern.run(); break;
                case 2: StrategyPattern.run(); break;
                case 3: FactoryPattern.run(); break;
                case 4: SingletonPattern.run(); break;
                case 5: AdapterPattern.run(); break;
                case 6: DecoratorPattern.run(); break;
                case 0: running = false; break;
                default: Logger.log("Invalid choice");
            }
        }
    }
}
