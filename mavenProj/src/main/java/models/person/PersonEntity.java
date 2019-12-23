package models.person;


import models.address.Address;
import models.address.AddressEntity;
import services.HibService;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "PATRONYMIC")
    private String patronymic;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    private AddressEntity addressEntity;

    public PersonEntity(){}

    public PersonEntity(Person person){
        HibService hibService = HibService.getHibService();
        this.name = person.getName();
        this.surname = person.getSurname();
        this.patronymic = person.getPatronymic();
        this.addressEntity = hibService.findByAddress(person.getAddress());
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
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
    public Person toPerson(){
        Person person = new Person();
        person.setName(this.getName());
        person.setSurname(this.getSurname());
        person.setPatronymic(this.getPatronymic());
        return person;
    }

}
