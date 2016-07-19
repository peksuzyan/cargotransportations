package com.tsystems.cargotransportations.webservice;

import java.util.Random;

public class Car {

    private String model;

    private int price;

    private int horsePowers;

    public Car(String model) {
        this.model = model;
        this.price = new Random().nextInt(1000000);
        this.horsePowers = new Random().nextInt(500);
    }

    public Car() {}

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getHorsePowers() {
        return horsePowers;
    }

    public void setHorsePowers(int horsePowers) {
        this.horsePowers = horsePowers;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", price=" + price +
                ", horsePowers=" + horsePowers +
                '}';
    }
}

