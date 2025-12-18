package com.cain.green.javafxlogindashboard.model;

/**
 * AidShip extends Ship and implements navigation and emergency support informations.

 */

public class AidShip extends Ship implements Navigable, EmergencySupport {

    private String currentLocation;
    private String supportType;

    public AidShip(String name,
                   String registrationNumber,
                   String currentLocation,
                   String supportType) {
        super(name, registrationNumber);
        this.currentLocation = currentLocation;
        this.supportType = supportType;
    }

    @Override
    public String getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public void setCurrentLocation(String location) {
        this.currentLocation = location;
    }

    @Override
    public String getSupportType() {
        return supportType;
    }

    public void setSupportType(String supportType) {
        this.supportType = supportType;
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Location: " + currentLocation
                + " | Support: " + supportType;
    }
}
