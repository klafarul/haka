package services;

import models.car.Car;
import models.person.Person;
import models.person.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.CarRepository;
import repositories.PersonRepository;
import services.DBService;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
@Transactional
public class CarValidationService {


    @Autowired
    private CarRepository carRepository;

    @Autowired
    private PersonRepository personRepository;

    public boolean isCarValid(Car car){

        if (isIdValid(car.getId()) && isVendorValid(car.getVendor()) && isModelValid(car.getModel()) && isHorsePowerValid(car.getHorsePower()) && isOwnerIdValid(car.getOwnerId())){
            return true;
        }
        return false;
    }

    private boolean isIdValid(long id){
        if ((id!=0)&&(carRepository.findById(id) == null)){
            return true;
        }
        return false;
    }

    private boolean isModelValid(String model){
        if ((model != null)){
            return true;
        }
        return false;
    }

    private boolean isVendorValid(String vendor){
        if ((vendor != null) && (!vendor.contains("-"))){
            return true;
        }
        return false;
    }

    private boolean isHorsePowerValid(int horsePower){
        if (horsePower > 0){
            return true;
        }
        return false;
    }

    private boolean isOwnerIdValid(long ownerId){

        PersonEntity personEntity  = personRepository.findById(ownerId);

        if (personEntity != null) {
            if (isOwnerAgeValid(personEntity)) {
                return true;
            }
        }
        return false;
    }

    private boolean isOwnerAgeValid(PersonEntity personEntity){

        Calendar currentDate = new GregorianCalendar();
        Calendar birthDateCalendar = new GregorianCalendar();
        birthDateCalendar.setTime(personEntity.getBirthDate());

        if ((currentDate.getWeekYear() - birthDateCalendar.getWeekYear() >= 18)){
            return true;
        }

        return false;

    }
}
