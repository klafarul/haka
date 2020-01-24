package pojo;

public class CarPojo {

    private long id;

    private String model;

    private int horsepower;

    private long ownerId;

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

    public int getHorsepower() {
        return horsepower;
    }


    public void setHorsepower(int horsePower) {
        this.horsepower = horsePower;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }
}
