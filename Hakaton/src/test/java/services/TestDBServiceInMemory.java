package services;


import models.car.Car;
import models.person.Person;
import models.person.PersonEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pojo.CarPojo;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @Resource
    private PersonValidationService personValidationService;
    @Resource
    private CarValidationService carValidationService;

    @Test
    public void testAddValidPerson() throws ParseException {
        Person person = new Person();
        person.setId(1);
        person.setName("Maks");

        String dateFormat = "01.12.1997";
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dateFormat);
        person.setBirthDate(date);

        dbService.savePerson(person);

        Person receivedPerson = dbService.getPersonById(1);

        assertEquals("Maks", receivedPerson.getName());
        assertEquals(0, receivedPerson.getCars().size());
        assertEquals(1, receivedPerson.getId());
    }

    @Test
    public void testAddValidPersonLessEighteen() throws ParseException{
        Person person = new Person();
        person.setId(-20);
        person.setName("ValidPerson2");

        String dateFormat = "01.12.2017";
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dateFormat);
        person.setBirthDate(date);

        if (personValidationService.isPersonValid(person, "01.12.2017")) {
            dbService.savePerson(person);
        }

        Person receivedPerson = dbService.getPersonById(-20);

        assertEquals(0, receivedPerson.getCars().size());
        assertEquals("ValidPerson2", receivedPerson.getName());
        assertEquals(date, receivedPerson.getBirthDate());
    }

    @Test
    public void testAddPersonEmptyName() throws ParseException {
        Person person = new Person();
        person.setId(1);
        person.setName("");

        String dateFormat = "01.12.1997";
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dateFormat);
        person.setBirthDate(date);

        if (personValidationService.isPersonValid(person, "01.12.1997")) {
            dbService.savePerson(person);
        }


        Person receivedPerson = dbService.getPersonById(1);

        assertEquals("", receivedPerson.getName());
    }

    @Test
    public void testAddBadPerosnFutureBirthDate() throws ParseException{
        Person person = new Person();
        person.setId(-20);
        person.setName("ValidPerson2");

        String dateFormat = "01.12.2207";
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dateFormat);
        person.setBirthDate(date);

        if (personValidationService.isPersonValid(person, "01.12.2207")) {
            dbService.savePerson(person);
        }

        Person receivedPerson = dbService.getPersonById(-20);
        assertEquals(null, receivedPerson);
    }

    @Test
    public void testAddBadPersonLanientDate() throws ParseException{
        Person person = new Person();
        person.setId(-100);
        person.setName("ValidPerson2");

        String dateFormat = "01.15.2017";
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dateFormat);
        person.setBirthDate(date);

        if (personValidationService.isPersonValid(person, "01.15.2017")) {
            dbService.savePerson(person);
        }

        Person receivedPerson = dbService.getPersonById(-100);
        assertEquals(null, receivedPerson);

    }





    @Test
    public void testAddValidCar() throws ParseException{
        Person person = new Person();
        person.setId(-130);
        person.setName("ValidPerson1");

        String dateFormat = "01.12.2000";
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dateFormat);
        person.setBirthDate(date);

        if (personValidationService.isPersonValid(person, "01.12.2000")) {
            dbService.savePerson(person);
        }

        Car car = new Car();
        car.setId(-130);
        car.setVendor("BMW");
        car.setModel("X5");
        car.setHorsePower(100);
        car.setOwnerId(-130);

        if (carValidationService.isCarValid(car)){
            dbService.saveCar(car);
        }

        Car receivedCar = dbService.getCarById(1);
        Person receivedPerson = dbService.getPersonById(-130);
        assertEquals(1, receivedPerson.getCars().size());
    }

    @Test
    public void testAddValidCars() throws ParseException{
        Person person = new Person();
        person.setId(-140);
        person.setName("ValidPerson1");

        String dateFormat = "01.12.2000";
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dateFormat);
        person.setBirthDate(date);

        if (personValidationService.isPersonValid(person, "01.12.2000")) {
            dbService.savePerson(person);
        }

        int carId = -140;
        for (int i = 0; i < 3; i++){
            Car car = new Car();
            car.setId(carId + i);
            car.setVendor("BMW");
            car.setModel("X5");
            car.setHorsePower(100 + i);
            car.setOwnerId(-140);

            if (carValidationService.isCarValid(car)){
                dbService.saveCar(car);
            }
        }
        Car receivedCar = dbService.getCarById(-140);
        Person receivedPerson = dbService.getPersonById(-140);

        assertEquals(3, receivedPerson.getCars().size());
        assertEquals("ValidPerson1", receivedPerson.getName());
        assertEquals("BMW", receivedCar.getVendor());
    }

    @Test
    public void testAddValidCarsModelFormatVariations() throws ParseException{
        Person person = new Person();
        person.setId(-150);
        person.setName("ValidPerson1");

        String dateFormat = "01.12.2000";
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dateFormat);
        person.setBirthDate(date);

        if (personValidationService.isPersonValid(person, "01.12.2000")) {
            dbService.savePerson(person);
        }

        CarPojo carPojo = new CarPojo();
        carPojo.setId(-149);
        carPojo.setModel("La-da-Devyatka");
        carPojo.setHorsepower(50);
        carPojo.setOwnerId(-150);

        Car car = new Car(carPojo);

        if (carValidationService.isCarValid(car)){
            dbService.saveCar(car);
        }

        carPojo.setId(-148);
        carPojo.setModel("La-da-");
        carPojo.setHorsepower(50);
        carPojo.setOwnerId(-150);

        car = new Car(carPojo);

        if (carValidationService.isCarValid(car)){
            dbService.saveCar(car);
        }

        Car receivedCar = dbService.getCarById(1);
        Person receivedPerson = dbService.getPersonById(-150);
        assertEquals(2, receivedPerson.getCars().size());

    }

    @Test
    public void testAddBadCarModelFormat() throws ParseException {
        Person person = new Person();
        person.setId(-160);
        person.setName("ValidPerson1");

        String dateFormat = "01.12.2000";
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dateFormat);
        person.setBirthDate(date);

        if (personValidationService.isPersonValid(person, "01.12.2000")) {
            dbService.savePerson(person);
        }

        CarPojo carPojo = new CarPojo();
        carPojo.setId(-160);
        carPojo.setModel("-da-Devyatka");
        carPojo.setHorsepower(50);
        carPojo.setOwnerId(-160);

        Car car = new Car(carPojo);

        if (carValidationService.isCarValid(car)) {
            dbService.saveCar(car);
        }

        Car receivedCar = dbService.getCarById(1);
        Person receivedPerson = dbService.getPersonById(-160);
        assertEquals(0, receivedPerson.getCars().size());
        assertEquals("ValidPerson1", receivedPerson.getName());
    }

    @Test
    public void testAddBadCarNegativeHorsepower() throws ParseException{
        Person person = new Person();
        person.setId(-160);
        person.setName("ValidPerson1");

        String dateFormat = "01.12.2000";
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dateFormat);
        person.setBirthDate(date);

        if (personValidationService.isPersonValid(person, "01.12.2000")) {
            dbService.savePerson(person);
        }

        CarPojo carPojo = new CarPojo();
        carPojo.setId(-160);
        carPojo.setModel("BMW-X5");
        carPojo.setHorsepower(-50);
        carPojo.setOwnerId(-160);

        Car car = new Car(carPojo);

        if (carValidationService.isCarValid(car)) {
            dbService.saveCar(car);
        }

        Car receivedCar = dbService.getCarById(-160);
        assertEquals(null, receivedCar);
    }

    @Test
    public void testAddbadCarLessEighteenYears() throws ParseException{
        Person person = new Person();
        person.setId(-180);
        person.setName("ValidPerson2");

        String dateFormat = "01.12.2017";
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dateFormat);
        person.setBirthDate(date);

        if (personValidationService.isPersonValid(person, "01.12.2017")) {
            dbService.savePerson(person);
        }

        CarPojo carPojo = new CarPojo();
        carPojo.setId(-180);
        carPojo.setModel("A-B");
        carPojo.setHorsepower(50);
        carPojo.setOwnerId(-180);

        Car car = new Car(carPojo);

        if (carValidationService.isCarValid(car)) {
            dbService.saveCar(car);
        }

        Car receivedCar = dbService.getCarById(-180);
        Person receivedPerson = dbService.getPersonById(-180);
        assertEquals(0, receivedPerson.getCars().size());
        assertEquals("ValidPerson2", receivedPerson.getName());
    }

    @Test
    public void testAddbadPersonUnique()  throws  ParseException{
        Person person = new Person();
        person.setId(-250);
        person.setName("ValidPerson1");

        String dateFormat = "01.01.2000";
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dateFormat);
        person.setBirthDate(date);

        if (personValidationService.isPersonValid(person, "01.01.2000")) {
            dbService.savePerson(person);
        }

        person.setId(-250);
        person.setName("ValidPerson1");

        dateFormat = "01.01.2000";
        date = new SimpleDateFormat("dd.MM.yyyy").parse(dateFormat);
        person.setBirthDate(date);

        if (personValidationService.isPersonValid(person, "01.01.2000")) {
            dbService.savePerson(person);
        }

        assertEquals(1, dbService.getPersonsCount());
    }

    @Test
    public void testAddBadCarUnique() throws ParseException{
        Person person = new Person();
        person.setId(-260);
        person.setName("ValidPerson1");

        String dateFormat = "01.12.2000";
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dateFormat);
        person.setBirthDate(date);

        if (personValidationService.isPersonValid(person, "01.12.2000")) {
            dbService.savePerson(person);
        }

        CarPojo carPojo = new CarPojo();
        carPojo.setId(-260);
        carPojo.setModel("BMW-X5");
        carPojo.setHorsepower(100);
        carPojo.setOwnerId(-260);

        Car car = new Car(carPojo);

        if (carValidationService.isCarValid(car)) {
            dbService.saveCar(car);
        }

        carPojo.setId(-260);
        carPojo.setModel("BMW-X5");
        carPojo.setHorsepower(100);
        carPojo.setOwnerId(-260);

        car = new Car(carPojo);

        if (carValidationService.isCarValid(car)) {
            dbService.saveCar(car);
        }

        Person receivedPerson = dbService.getPersonById(-260);
        assertEquals("ValidPerson1", receivedPerson.getName());
        assertEquals(1, receivedPerson.getCars().size());
    }

    @Test
    public void testGetPersonCount(){
        Person person = new Person();
        person.setId(1);
        person.setName("Maks");

        Date currentDate = new Date();
        person.setBirthDate(currentDate);

        dbService.savePerson(person);

        person.setId(2);
        person.setName("Dima");
        dbService.savePerson(person);

        assertEquals(2, dbService.getPersonsCount());
        assertEquals("Maks", dbService.getPersonById(1).getName());

    }
}
