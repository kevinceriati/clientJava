package com.RottenCar.client.model;

public class Car {

    private String name;
    private String brand;
    private Integer id;

    // Spring instancie des objets avec le constructur par defaut (vide), et ici il est surchargé donc pas de constructeur vide donc il faut en instancier un
    public Car(){}

    public Car(String name, String brand, Integer id) {
        this.name = name;
        this.brand = brand;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
