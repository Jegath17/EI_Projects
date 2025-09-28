package com.designpatterns.creational;

class DatabaseConnection {
    private static DatabaseConnection instance;

    private DatabaseConnection() {}

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void connect() {
        System.out.println("Connected to the database");
        System.out.println("Instance hashcode: " + this.hashCode());
    }
}

public class SingletonPattern {
    public static void run() {
        System.out.println("Getting first instance:");
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        db1.connect();

        System.out.println("Getting second instance:");
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        db2.connect();

        System.out.println("Check same instance: " + (db1 == db2));
    }
}
