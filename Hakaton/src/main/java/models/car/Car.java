package models.car;

import models.person.Person;
import pojo.CarPojo;

public class Car {

    private long id;

    private String vendor;

    private String model;

    private int horsePower;

    private long ownerId;

    private Person person;

    public Car(){

    }

    public Car(CarPojo carPojo){
        this.id = carPojo.getId();
        if ( (carPojo.getModel() != null) && (carPojo.getModel() != "") ) {
            this.vendor = carPojo.getModel().substring(0, carPojo.getModel().indexOf('-'));
            this.model = carPojo.getModel().substring(carPojo.getModel().indexOf('-') + 1);
        }else{
            this.model = null;
            this.vendor = null;
        }
        if ((this.vendor != null) && (this.vendor.equals(""))){
            this.vendor = null;
        }
        this.horsePower = carPojo.getHorsepower();
        this.ownerId = carPojo.getOwnerId();

    }

    public CarPojo toCarPojo(){
        CarPojo carPojo = new CarPojo();

        carPojo.setId(this.id);
        carPojo.setModel(this.vendor.trim() + "-" + this.model.trim());
        carPojo.setHorsepower(this.horsePower);
        carPojo.setOwnerId(this.ownerId);
        return carPojo;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
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
