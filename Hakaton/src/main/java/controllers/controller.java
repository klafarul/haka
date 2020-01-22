package controllers;

import models.cars.Car;
import models.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pojo.CarPojo;
import pojo.PersonPojo;
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



    @RequestMapping(value = "/addPerson", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    @ResponseBody
    public void addPerson(@RequestBody PersonPojo personPojo){
        Person person = new Person(personPojo);

        PersonValidation personValidation;

        personValidation = new PersonValidation();
        if (personValidation.isPersonValid(person, dbService)) {
            dbService.savePerson(person);
        }

    }

    @GetMapping(value = "index")
    public ModelAndView toPerson(){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("Person");

        return modelAndView;
    }

    @RequestMapping(value = "/addCar", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public void addCar(@RequestBody CarPojo carPojo){

        Car car = new Car(carPojo);

        CarValidation carValidation = new CarValidation();

        if (carValidation.isCarValid(car, dbService)){
            dbService.saveCar(car);
        }

        int a = dbService.getCountPersonCars(1);

    }

    @RequestMapping(value = "/car")
    public ModelAndView toCar(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Car");

        return modelAndView;
    }


}
