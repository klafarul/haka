package pojo;

import java.util.List;

public class PersonWithCarsPojo {

    private long id;

    private String name;

    private String birthdate;

    List<CarPojo> cars;



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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public List<CarPojo> getCars() {
        return cars;
    }

    public void setCars(List<CarPojo> cars) {
        this.cars = cars;
    }
}
