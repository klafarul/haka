package pojo;

import java.util.List;

public class PersonWithCarsPojo {

    private long id;

    private String name;

    private String date;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<CarPojo> getCars() {
        return cars;
    }

    public void setCars(List<CarPojo> cars) {
        this.cars = cars;
    }
}
