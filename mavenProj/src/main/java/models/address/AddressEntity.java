package models.address;

import models.person.Person;
import models.person.PersonEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AddressEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    @Column(name = "CITY")
    private String city;
    @Column(name = "HOUSE")
    private int house;
    @Column(name = "APARTMENT")
    private int apartment;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addressEntity")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<PersonEntity> persons;

    public AddressEntity(){
    }
    public AddressEntity(Address address){
        this.city = address.getCity();
        this.house = address.getHouse();
        this.apartment = address.getApartment();
        persons = new ArrayList<>();
        for (int i = 0; i < address.getPersons().size(); i++){
            PersonEntity personEntity = new PersonEntity(address.getPersons().get(i));
            personEntity.setAddressEntity(this);
            persons.add(personEntity);
        }
    }

    public void addPerson(PersonEntity personEntity) {
        personEntity.setAddressEntity(this);
        persons.add(personEntity);
    }



    public List<PersonEntity> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonEntity> persons) {
        this.persons = persons;
    }

    public int getId() { return id; }

    public String getCity() { return city; }

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

    public Address toAddress(){
        Address address = new Address();
        address.setCity(this.getCity());
        address.setHouse(this.getHouse());
        address.setApartment(this.getApartment());

        List<Person> personList = new ArrayList<>();
        for (int i = 0; i <this.getPersons().size(); i++){
            personList.add(this.getPersons().get(i).toPerson());
        }
        address.setPersons(personList);
        return address;
    }
    public AddressEntity toAddressEntity(Address address) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(address.getCity());
        addressEntity.setHouse(address.getHouse());
        addressEntity.setApartment(address.getApartment());
        return addressEntity;
    }
}
