package models.person;

import models.cars.Car;
import pojo.CarPojo;
import pojo.PersonPojo;
import pojo.PersonWithCarsPojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Person {

    private long id;

    private String name;

    private Date birthDate;

    private List<Car> cars;

    public Person(){

    }

    public Person(PersonPojo personPojo) {
        this.id = personPojo.getId();
        this.name = personPojo.getName();
        try {
            birthDate = new SimpleDateFormat("dd.MM.yy").parse(personPojo.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public PersonWithCarsPojo toPersonWithCars(){
        PersonWithCarsPojo personWithCarsPojo = new PersonWithCarsPojo();
        personWithCarsPojo.setId(this.id);
        personWithCarsPojo.setName(this.name);
        personWithCarsPojo.setDate(this.birthDate.toString());

        List<CarPojo> carsPojo = new ArrayList<>();

        for (int i = 0; i < this.cars.size(); i++){
            carsPojo.add(this.cars.get(i).toCarPojo());
        }
        personWithCarsPojo.setCars(carsPojo);
        return personWithCarsPojo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
