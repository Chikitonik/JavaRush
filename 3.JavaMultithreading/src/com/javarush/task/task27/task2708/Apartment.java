package com.javarush.task.task27.task2708;

public class Apartment {
    private String location;
    private final RealEstate realEstate;

    public Apartment(RealEstate realEstate) {
        this.realEstate = realEstate;
        setLocation(String.valueOf(Math.random() * 10));
    }

    public String getLocation() {
        synchronized (this) {
            return location;
        }
    }//убрал синхронизацию

    public synchronized void setLocation(String location) {
        synchronized (this) {
            this.location = location;
        }
    }//убрал синхронизацию

    public void revalidate(boolean isEmpty) {//убрал синхронизацию
        synchronized (this) {
            if (isEmpty)
                realEstate.up(this);
        }
    }
}
