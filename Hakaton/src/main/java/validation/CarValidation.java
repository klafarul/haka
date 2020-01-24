package validation;

import models.car.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.DBService;

@Component
public class CarValidation {

    @Autowired
    private DBService dbService;

    public boolean isCarValid(Car car){

        if (isIdValid(car.getId()) && isVendorValid(car.getVendor()) && isModelValid(car.getModel()) && isHorsePowerValid(car.getHorsePower()) && isOwnerIdValid(car.getOwnerId())){
            return true;
        }
        return false;
    }

    private boolean isIdValid(long id){
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

    private boolean isOwnerIdValid(long ownerId){

        if (dbService.isPersonInDb(ownerId)){
            return true;
        }
        return false;
    }
}
