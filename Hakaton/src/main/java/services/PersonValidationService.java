package services;

import models.person.Person;
import models.person.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.PersonRepository;
import services.DBService;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
@Transactional
public class PersonValidationService {

    @Autowired
    private PersonRepository personRepository;

    public boolean isPersonValid(Person person, String enteredDateFromPojo){
        if (isNameValid(person.getName()) && isBirthDateVaild(person.getBirthDate(), enteredDateFromPojo) && isIdValid(person.getId())){
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

    private boolean isBirthDateVaild(Date birthDate, String enteredDateFromPojo){
        if (birthDate != null){
            Calendar currentDateCalendar = new GregorianCalendar();
            Calendar birthDateCalendar = new GregorianCalendar();
            birthDateCalendar.setTime(birthDate);

            int a = birthDateCalendar.get(Calendar.MONTH);

            int enteredDays = Integer.parseInt(enteredDateFromPojo.substring(0, enteredDateFromPojo.indexOf(".")));
            int enteredMonth = Integer.parseInt(enteredDateFromPojo.substring(enteredDateFromPojo.indexOf(".") + 1, enteredDateFromPojo.lastIndexOf(".")));
            int enteredYear = Integer.parseInt(enteredDateFromPojo.substring(enteredDateFromPojo.lastIndexOf(".") + 1));

            if (birthDateCalendar.get(Calendar.DAY_OF_MONTH)   != enteredDays  ||
                birthDateCalendar.get(Calendar.MONTH) + 1      != enteredMonth ||
                birthDateCalendar.get(Calendar.YEAR)           != enteredYear){
                return false;
            }


            boolean fl = birthDateCalendar.before(currentDateCalendar);
            if (birthDateCalendar.before(currentDateCalendar)){
                return true;
            }
        }

        return false;
    }


    private boolean isIdValid(long id){
        PersonEntity personEntity = personRepository.findById(id);

        if ((personEntity == null) && (id!=0)){
            return true;
        }
        return false;

    }
}
