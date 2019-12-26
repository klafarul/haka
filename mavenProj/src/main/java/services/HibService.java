package services;


import models.address.Address;
import models.address.AddressEntity;
import models.person.Person;
import models.person.PersonEntity;
import org.hibernate.service.Service;
import java.util.ArrayList;



public class HibService implements Service {
    private static HibService hibService;

    private HibService(){
    }

    public static HibService getHibService(){
        if (hibService == null){
            hibService = new HibService();
        }
        return hibService;
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
        AddressRepository addressRepository = new AddressRepository();

        if (addressRepository.find(addressEntity) == null) {
            addressRepository.save(addressEntity);
        }
    }

    //++--
    public void updateAddress(Address address){
        AddressRepository addressRepository = new AddressRepository();

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

        AddressRepository addressRepository = new AddressRepository();

        return addressRepository.find(addressEntity);
    }

    //+++++
    public ArrayList<Address> findAllAddresses(){
        AddressRepository addressRepository = new AddressRepository();

        ArrayList<AddressEntity> adressesEntity = addressRepository.findAll();
        ArrayList<Address> addresses= new ArrayList<>();

        for (int i = 0; i <adressesEntity.size(); i++){
            addresses.add(adressesEntity.get(i).toAddress());
        }
        return addresses;
    }

}
