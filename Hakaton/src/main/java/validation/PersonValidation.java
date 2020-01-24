package validation;

import models.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.DBService;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


@Component
public class PersonValidation {

    @Autowired
    private DBService dbService;



    public boolean isPersonValid(Person person){
        if (isNameValid(person.getName()) && isBirthDateVaild(person.getBirthDate()) && isIdValid(person.getId())){
            return true;
        }
        return false;

    }

    private boolean isNameValid(String name){
        if (name != null){
            return true;
        }
        return false;
    }

    private boolean isBirthDateVaild(Date birthDate){

        Calendar currentDate = new GregorianCalendar();
        Calendar birthDateCalendar = new GregorianCalendar();
        birthDateCalendar.setTime(birthDate);

        if (birthDateCalendar.before(currentDate) && (currentDate.getWeekYear() - birthDateCalendar.getWeekYear() >= 18)){
            return true;
        }

        return false;
    }


    private boolean isIdValid(long id){
        if (dbService.isPersonInDb(id) == false){
            return true;
        }
        return false;

    }
}
