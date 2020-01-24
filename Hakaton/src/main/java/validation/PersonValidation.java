package validation;

import models.person.Person;
import services.DBService;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



public class PersonValidation {



    public boolean isPersonValid(Person person, DBService dbService){
        if (isNameValid(person.getName()) && isBirthDateVaild(person.getBirthDate()) && isIdValid(person.getId(), dbService)){
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


    private boolean isIdValid(long id, DBService dbService){
        if (dbService.isPersonInDb(id) == false){
            return true;
        }
        return false;

    }
}
