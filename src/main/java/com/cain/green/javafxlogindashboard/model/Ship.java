package com.cain.green.javafxlogindashboard.model;

/**
 * regualr ship with name and reg number

 */
public class Ship {

    private String name;
    private String registrationNumber;

    public Ship(String name, String registrationNumber) {
        this.name = name;
        this.registrationNumber = registrationNumber;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getRegistrationNumber() { return registrationNumber; }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String toString() {
        return name + " (" + registrationNumber + ")";
    }
}
