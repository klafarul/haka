package models.cars;


import models.person.PersonEntity;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "cars")
public class CarEntity {

    @Id
    private long id;

//    @Column(name = "model")
//    private String model;

    @Column(name = "horsepower")
    private int horsePower;

    @Column(name = "vendor")
    private String vendor;

    @Column(name = "model")
    private String model;


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerId")
    private PersonEntity personEntity;

    public CarEntity(){
    }



    public CarEntity(Car car){
        this.id = car.getId();
        this.vendor = car.getVendor();
        this.model = car.getModel();
        this.horsePower = car.getHorsePower();

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

    public PersonEntity getPersonEntity() {
        return personEntity;
    }

    public void setPersonEntity(PersonEntity personEntity) {
        this.personEntity = personEntity;
    }

    public Car toCar() {
        Car car = new Car();

        car.setId(this.id);
        car.setVendor(this.vendor);
        car.setModel(this.model);
        car.setHorsePower(this.horsePower);
        car.setOwnerId(this.personEntity.getId());

        return car;
    }
}
