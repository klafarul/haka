package controllers;

import models.address.Address;
import models.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import services.HibService;

import java.util.ArrayList;

@Controller
@Transactional
public class MainController {

    private HibService hibService;

    @Autowired
    public MainController(HibService hibService){
        this.hibService = hibService;
    }

    @GetMapping("/index")
    public String toAddress(){
        return "Address";
    }

    @GetMapping("/address")
    public String toPerson(Model model){

        ArrayList<Address> list = hibService.findAllAddresses();
        model.addAttribute("list", list);

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

        hibService.saveAddress(address);

        return "Address";
    }

    @PostMapping("/person")
    public String addPerson(Model model, @RequestParam(name = "name") String name,
                            @RequestParam(name = "surname") String surname,
                            @RequestParam(name = "patronymic") String patronymic,
                            @RequestParam(name = "address") String addressLine){

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

        return "Person";
    }


    @PostMapping("/result")
    public String toResult(){
        return "Result";
    }
    @GetMapping("/result")
    public String showResult(Model model){

        ArrayList<Address> addresses = hibService.findAllAddresses();

        model.addAttribute("addresses", addresses);

        return "Result";
    }





}