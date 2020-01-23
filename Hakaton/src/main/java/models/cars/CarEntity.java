package models.cars;


import models.person.PersonEntity;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "cars")
public class CarEntity {

    @Id
    private long id;

    @Column(name = "model")
    private String model;

    @Column(name = "horsepower")
    private int horsePower;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerId")
    private PersonEntity personEntity;

    public CarEntity(){
    }



    public CarEntity(Car car){
        this.id = car.getId();
        this.model = car.getModel();
        this.horsePower = car.getHorsePower();

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




    public PersonEntity getPersonEntity() {
        return personEntity;
    }

    public void setPersonEntity(PersonEntity personEntity) {
        this.personEntity = personEntity;
    }

    public Car toCar() {
        Car car = new Car();

        car.setId(this.id);
        car.setModel(this.model);
        car.setHorsePower(this.horsePower);

        return car;
    }
}
