package services;


import services.repositories.AddressRepository;
import services.repositories.PersonRepository;
import models.address.Address;
import models.address.AddressEntity;
import models.person.Person;
import models.person.PersonEntity;
import org.hibernate.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("hib")
public class HibService implements Service {
    private AddressRepository addressRepository;



    @Autowired
    public HibService(AddressRepository addressRepository){
        this.addressRepository  = addressRepository;
    }
    //+++++
    public void savePerson(Person person){
        PersonEntity personEntity = new PersonEntity(person);
        PersonRepository personRepository = new PersonRepository();
        personRepository.save(personEntity);
    }

    //+++++
    public void saveAddress(Address address){
        AddressEntity addressEntity = new AddressEntity(address);


        if (addressRepository.find(addressEntity) == null) {
            addressRepository.save(addressEntity);
        }
    }

    //++--
    public void updateAddress(Address address){

        AddressEntity addressEntity = findByAddress(address);
        PersonEntity personEntity = new PersonEntity(address.getPersons().get(address.getPersons().size()-1));
        addressRepository.update(addressEntity, personEntity);

    }
    //+++++
    public AddressEntity findByAddress(Address address) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(address.getCity());
        addressEntity.setHouse(address.getHouse());
        addressEntity.setApartment(address.getApartment());


        return addressRepository.find(addressEntity);
    }

    //+++++
    public ArrayList<Address> findAllAddresses(){


        ArrayList<AddressEntity> adressesEntity = addressRepository.findAll();
        ArrayList<Address> addresses= new ArrayList<>();

        for (int i = 0; i <adressesEntity.size(); i++){
            addresses.add(adressesEntity.get(i).toAddress());
        }
        System.out.println(addresses.size() + "   FROM HIBSERVICE" );
        return addresses;
    }

}
