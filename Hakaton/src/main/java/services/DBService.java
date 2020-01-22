package services;


import models.cars.Car;
import models.cars.CarEntity;
import models.person.Person;
import models.person.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DBService {


    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CarRepository carRepository;



    public void savePerson(Person person){
        PersonEntity personEntity = new PersonEntity();

        personEntity.setId(person.getId());
        personEntity.setName(person.getName());
        personEntity.setBirthDate(person.getBirthDate());

        personRepository.save(personEntity);
    }

    public boolean isPersonInDb(long id){
        PersonEntity personEntity = personRepository.findById(id);
        if (personEntity != null){
            return true;
        }
        return false;
    }

    public void saveCar(Car car){
        CarEntity carEntity = new CarEntity(car);
        PersonEntity personEntity = personRepository.findById(car.getOwnerId());

        personEntity.getCars().add(carEntity);
        carEntity.setPersonEntity(personEntity);

        personRepository.update(personEntity);
//        carRepository.save(carEntity, personEntity);
    }

    public int getCountPersonCars(long id){
        PersonEntity personEntity = personRepository.findById(id);
        return personEntity.getCars().size();
    }

//    public void saveCar(){
//        CarEntity carEntity = new CarEntity();
//        carEntity.setModel("BMW");
//        carEntity.setHorsePower(200);
//
//        PersonEntity personEntity = new PersonEntity();
//        personEntity.setBirthDate(12);
//        personEntity.setName("Maks");
//        personRepository.saveCar(carEntity, personEntity);
//
//    }

//    public Person getPerson(int id) {
//
//
//    }
}
