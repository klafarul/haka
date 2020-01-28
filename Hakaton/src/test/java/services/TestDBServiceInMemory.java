package services;


import models.car.Car;
import models.person.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        {
                "classpath:contextMVC.xml"
        }
)
@Transactional
public class TestDBServiceInMemory {

    @Resource
    private DBService dbService;

    @Test
    public void TestSavePerson() {
        Person person = new Person();
        person.setId(1);
        person.setName("Maks");

        Date currentDate = new Date();
        person.setBirthDate(currentDate);

        dbService.savePerson(person);

        Person receivedPerson = dbService.getPersonById(1);
        assertEquals("Maks", receivedPerson.getName());
    }

    @Test
    public void TestSaveCar(){
        Person person = new Person();
        person.setId(1);
        person.setName("Maks");

        Date currentDate = new Date();
        person.setBirthDate(currentDate);

        dbService.savePerson(person);

        Car car = new Car();
        car.setId(1);
        car.setVendor("BMW");
        car.setModel("X5");
        car.setHorsePower(300);
        car.setOwnerId(1);

        dbService.saveCar(car);
        Car receivedCar = dbService.getCarById(1);
        assertEquals("BMW", receivedCar.getVendor());
    }

    @Test
    public void TestGetPersonCount(){
        Person person = new Person();
        person.setId(1);
        person.setName("Maks");

        Date currentDate = new Date();
        person.setBirthDate(currentDate);

        dbService.savePerson(person);

        assertEquals(1, dbService.getPersonsCount());

    }
}
