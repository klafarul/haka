package models.person;

import jdk.nashorn.internal.runtime.ParserException;
import models.car.Car;
import pojo.CarPojo;
import pojo.PersonPojo;
import pojo.PersonWithCarsPojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Calendar.YEAR;

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
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

            if(personPojo.getBirthdate()!=null) {
                Calendar calendar = new GregorianCalendar();
                birthDate = dateFormat.parse(personPojo.getBirthdate());
                calendar.setLenient(false);




            }
            else{
                birthDate = null;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public PersonWithCarsPojo toPersonWithCars(){
        PersonWithCarsPojo personWithCarsPojo = new PersonWithCarsPojo();
        personWithCarsPojo.setId(this.id);
        personWithCarsPojo.setName(this.name);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        personWithCarsPojo.setBirthdate(dateFormat.format(this.birthDate));

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
