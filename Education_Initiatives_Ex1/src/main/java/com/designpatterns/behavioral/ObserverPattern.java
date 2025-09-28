package com.designpatterns.behavioral;

import java.util.ArrayList;
import java.util.List;
import com.designpatterns.utils.UserInput;

interface Observer {
    void update(float temperature);
}

class WeatherStation {
    private float temperature;
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }
}

class PhoneDisplay implements Observer {
    @Override
    public void update(float temperature) {
        System.out.printf("Phone Display: Temperature is %.1f°C%n", temperature);
    }
}

class TVDisplay implements Observer {
    @Override
    public void update(float temperature) {
        System.out.printf("TV Display: Temperature is %.1f°C%n", temperature);
    }
}

public class ObserverPattern {
    public static void run() {
        WeatherStation station = new WeatherStation();
        station.addObserver(new PhoneDisplay());
        station.addObserver(new TVDisplay());

        double temp = UserInput.getDouble("Enter the temperature: ");
        station.setTemperature((float) temp);
    }
}
