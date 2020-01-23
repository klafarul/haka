package controllers;

import models.cars.Car;
import models.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pojo.CarPojo;
import pojo.PersonPojo;
import pojo.PersonWithCarsPojo;
import pojo.StatisticsPojo;
import services.DBService;
import validation.CarValidation;
import validation.PersonValidation;

@RestController
public class controller {

    @Autowired
    private DBService dbService;

    @RequestMapping("/")
    public ModelAndView startPage(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("Person");
        return  modelAndView;
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    @ResponseBody
    public void addPerson(@RequestBody PersonPojo personPojo){
        Person person = new Person(personPojo);

        PersonValidation personValidation;

        personValidation = new PersonValidation();
        if (personValidation.isPersonValid(person, dbService)) {
            dbService.savePerson(person);
        }
    }

    @RequestMapping(value = "/car", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public void addCar(@RequestBody CarPojo carPojo){

        Car car = new Car(carPojo);

        CarValidation carValidation = new CarValidation();

        if (carValidation.isCarValid(car, dbService)){
            dbService.saveCar(car);
        }


    }

    @RequestMapping(value = "/personWithCars", method = RequestMethod.GET)
    @ResponseBody
    public PersonWithCarsPojo getPersonInfo(@RequestParam("id") int id){
        Person person = dbService.getPersonById(id);
        PersonWithCarsPojo personWithCarsPojo = person.toPersonWithCars();
        return personWithCarsPojo;
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    @ResponseBody
    public StatisticsPojo getStatistics(){
        StatisticsPojo statisticsPojo = new StatisticsPojo();
        statisticsPojo.setPersonCount(dbService.getPersonsCount());
        statisticsPojo.setCarCount(dbService.getCarsCount());
        statisticsPojo.setUniqueVendorCount(dbService.getUniqueVendorCount());
        return statisticsPojo;
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public void clear(){
        dbService.deleteAllRaws();
    }
}
