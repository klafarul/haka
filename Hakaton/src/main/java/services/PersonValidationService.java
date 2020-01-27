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

    public boolean isPersonValid(Person person, String enteredDate){
        if (isNameValid(person.getName()) && isBirthDateVaild(person.getBirthDate(), enteredDate) && isIdValid(person.getId())){
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

    private boolean isBirthDateVaild(Date birthDate, String enteredDate){
        if (birthDate != null){
            Calendar currentDateCalendar = new GregorianCalendar();
            Calendar birthDateCalendar = new GregorianCalendar();
            //birthDateCalendar.setLenient(false);
            birthDateCalendar.setTime(birthDate);

            int a = birthDateCalendar.get(Calendar.MONTH);

            int enteredDays = Integer.parseInt(enteredDate.substring(0, enteredDate.indexOf(".")));
            int enteredMonth = Integer.parseInt(enteredDate.substring(enteredDate.indexOf(".") + 1, enteredDate.lastIndexOf(".")));
            int enteredYear = Integer.parseInt(enteredDate.substring(enteredDate.lastIndexOf(".") + 1));

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
