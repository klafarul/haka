package models.cars;

import models.person.Person;
import pojo.CarPojo;

public class Car {

    private long id;

    private String model;

    private int horsePower;

    private long ownerId;

    private Person person;

    public Car(){

    }

    public Car(CarPojo carPojo){
        this.id = carPojo.getId();
        this.model = carPojo.getModel();
        this.horsePower = carPojo.getHorsePower();
        this.ownerId = carPojo.getOwnerId();

    }

    public CarPojo toCarPojo(){
        CarPojo carPojo = new CarPojo();

        carPojo.setId(this.id);
        carPojo.setModel(this.model);
        carPojo.setHorsePower(this.horsePower);
        carPojo.setOwnerId(this.ownerId);
        return carPojo;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
