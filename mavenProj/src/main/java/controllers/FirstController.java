package controllers;

import models.address.Address;
import models.person.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import services.HibService;

import java.util.ArrayList;

@Controller
public class FirstController {



    @GetMapping("/index")
    public String toAddress(){
        return "Address";
    }

    @GetMapping("/address")
    public String toPerson(Model model){

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        HibService hibService = context.getBean("hib", HibService.class);

        ArrayList<Address> list = hibService.findAllAddresses();
        model.addAttribute("list", list);
        System.out.println(list.size() + "SIZEEEEEEEE");
        context.close();
        return "Person";
    }

    @PostMapping("/address")
    public String toAddr(@RequestParam(name="city") String city,
                         @RequestParam(name = "house") String house,
                         @RequestParam(name = "apartment") String apartment){

        Address address = new Address();

        address.setCity(city);
        address.setHouse(Integer.parseInt(house));
        address.setApartment(Integer.parseInt(apartment));


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        HibService hibService = context.getBean("hib", HibService.class);
        hibService.saveAddress(address);
        context.close();
        return "Address";
    }

    @PostMapping("/person")
    public String addPerson(Model model, @RequestParam(name = "name") String name,
                                         @RequestParam(name = "surname") String surname,
                                         @RequestParam(name = "patronymic") String patronymic,
                                         @RequestParam(name = "address") String addressLine){

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        HibService hibService = context.getBean("hib", HibService.class);
        Person person = new Person();
        Address address = new Address();

        person.setName(name);
        person.setSurname(surname);
        person.setPatronymic(patronymic);

        String str = addressLine;

        address.setCity(str.substring(0,str.indexOf(',')));
        address.setHouse(Integer.parseInt(str.substring(str.lastIndexOf(' ')+1, str.indexOf('/'))));
        address.setApartment(Integer.parseInt(str.substring(str.indexOf('/') +1)));

        person.setAddress(address);
        address.addPerson(person);

        hibService.updateAddress(address);


        ArrayList<Address> addresses;
        addresses =  hibService.findAllAddresses();
        model.addAttribute("list", addresses);

        context.close();
        return "Person";
    }


    @PostMapping("/result")
    public String toResult(){
        return "Result";
    }
    @GetMapping("/result")
    public String showResult(Model model){

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        HibService hibService = context.getBean("hib", HibService.class);
        ArrayList<Address> addresses = hibService.findAllAddresses();

        model.addAttribute("addresses", addresses);

        context.close();
        return "Result";
    }





}
