package controllers;

import models.car.Car;
import models.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pojo.CarPojo;
import pojo.PersonPojo;
import pojo.PersonWithCarsPojo;
import pojo.StatisticsPojo;
import services.DBService;
import services.CarValidationService;
import services.PersonValidationService;

@RestController
public class controller {

    @Autowired
    private DBService dbService;

    @Autowired
    private CarValidationService carValidation;

    @Autowired
    private PersonValidationService personValidation;

    @RequestMapping("/")
    public ModelAndView startPage(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("Person");
        return  modelAndView;
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    @ResponseBody
    public ResponseEntity<?> addPerson(@RequestBody PersonPojo personPojo){
        Person person = new Person(personPojo);


        if (personValidation.isPersonValid(person)) {
            dbService.savePerson(person);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/car", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public ResponseEntity<?> addCar(@RequestBody CarPojo carPojo){

        Car car = new Car(carPojo);


        if (carValidation.isCarValid(car)){
            dbService.saveCar(car);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/personwithcars", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PersonWithCarsPojo> getPersonInfo(@RequestParam("personid") int id){

            Person person = dbService.getPersonById(id);
            if (person!= null) {

                PersonWithCarsPojo personWithCarsPojo = person.toPersonWithCars();

                return new ResponseEntity<>(personWithCarsPojo, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    @ResponseBody
    public StatisticsPojo getStatistics(){
        StatisticsPojo statisticsPojo = new StatisticsPojo();
        statisticsPojo.setPersoncount(dbService.getPersonsCount());
        statisticsPojo.setCarcount(dbService.getCarsCount());
        statisticsPojo.setUniquevendorcount(dbService.getUniqueVendorCount());
        return statisticsPojo;
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public void clear(){
        dbService.deleteAllRaws();
    }
}
