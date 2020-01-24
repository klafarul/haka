package validation;

import models.car.Car;
import services.DBService;

public class CarValidation {

    public boolean isCarValid(Car car, DBService dbService){

        if (isIdValid(car.getId(), dbService) && isVendorValid(car.getVendor()) && isModelValid(car.getModel()) && isHorsePowerValid(car.getHorsePower()) && isOwnerIdValid(car.getOwnerId(), dbService)){
            return true;
        }
        return false;
    }

    private boolean isIdValid(long id, DBService dbService){
        if ((id > 0) && (dbService.getCarById(id) == null)){
            return true;
        }
        return false;
    }

    private boolean isModelValid(String model){
        if ((model != null) && (!model.contains("-"))){
            return true;
        }
        return true;
    }

    private boolean isVendorValid(String vendor){
        if ((vendor != null) && (!vendor.contains("-"))){
            return true;
        }
        return true;
    }

    private boolean isHorsePowerValid(int horsePower){
        if (horsePower > 0){
            return true;
        }
        return false;
    }

    private boolean isOwnerIdValid(long ownerId, DBService dbService){

        if (dbService.isPersonInDb(ownerId)){
            return true;
        }
        return false;
    }
}
