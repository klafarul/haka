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
        if (birthDate != null){
            Calendar currentDate = new GregorianCalendar();
            Calendar birthDateCalendar = new GregorianCalendar();
            birthDateCalendar.setTime(birthDate);
            boolean fl = birthDateCalendar.before(currentDate);



            if (birthDateCalendar.before(currentDate)){
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
