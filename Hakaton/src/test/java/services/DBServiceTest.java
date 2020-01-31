package services;

import models.car.Car;
import models.car.CarEntity;
import models.person.Person;
import models.person.PersonEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import repositories.CarRepository;
import repositories.PersonRepository;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DBServiceTest {

    @Mock
    PersonRepository personRepository;

    @Mock
    CarRepository carRepository;

    @InjectMocks
    DBService dbService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void TestIsPersonInDb_returnTrue() {
        when(personRepository.findById(1)).thenReturn(new PersonEntity());
        assertTrue(dbService.isPersonInDb(1));
        verify(personRepository, times(1)).findById(1);
    }

    @Test
    public void TestIsPersonInDb_returnFalse_BadId(){
        when(personRepository.findById(3)).thenReturn(new PersonEntity());
        assertFalse(dbService.isPersonInDb(1));
        verify(personRepository).findById(any(Long.class));
    }

    @Test
    public void TestSaveCar(){
        Car car = new Car();
        car.setId(1L);
        car.setVendor("BMW");
        car.setModel("X5");
        car.setHorsePower(300);
        car.setOwnerId(1L);

        PersonEntity personEntity1 = new PersonEntity();
        personEntity1.setCars(new ArrayList<CarEntity>());

        when(personRepository.findById(any(Long.class))).thenReturn(personEntity1);
        doNothing().when(carRepository).save(any(CarEntity.class));

        dbService.saveCar(car);

        verify(personRepository).findById(any(Long.class));
        verify(carRepository).save(any(CarEntity.class));

    }
}
