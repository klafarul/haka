package models.address;

import models.person.Person;

import java.util.ArrayList;
import java.util.List;

public class Address {
    private String city;
    private int house;
    private int apartment;
    private List<Person> persons;
    private int id;


    public Address(){
        persons = new ArrayList<>();
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void addPerson(Person person){

        person.setAddress(this);
        if (!persons.contains(person)) {
            persons.add(person);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public int getHouse() {
        return house;
    }

    public int getApartment() {
        return apartment;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return house == address.house &&
                apartment == address.apartment &&
                city.equals(address.city);
    }

    @Override
    public String toString() {
        return city +", " + house + "/" + apartment ;
    }
}
