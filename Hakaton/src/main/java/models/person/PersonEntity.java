package models.person;


import models.cars.CarEntity;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Component
@Entity
@Table(name = "persons")
public class PersonEntity {

    @Id
    @Column(nullable = false)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthdate")
    private Date birthDate;



    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personEntity")
    private List<CarEntity> cars;

    public PersonEntity(){

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

    public List<CarEntity> getCars() {
        return cars;
    }

    public void setCars(List<CarEntity> cars) {
        this.cars = cars;
    }
}
