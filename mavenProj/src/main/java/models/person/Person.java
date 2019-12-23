package models.person;


import models.address.Address;

public class Person {


    private String name;

    private String surname;

    private String patronymic;

    private Address address;
    public Person(){
    }


    public Person(String name, String surname, String patronymic){
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Address getAddress() { return address;}

    public void setAddress(Address address) { this.address = address; }

    @Override
    public String toString() {
        return "\nPerson:\n" +
                "name= " + name + "\n" +
                "surname= " + surname + '\n' +
                "patronymic= " + patronymic + '\n';
    }
}
