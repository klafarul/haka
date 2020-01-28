package services;

import models.car.Car;
import models.car.CarEntity;
import models.person.Person;
import models.person.PersonEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repositories.CarRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class TestCarValidationService {

    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarValidationService carValidationService;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testIsCarValid_returnsFalse() {

        Car car = new Car();

        when(carRepository.findById(23)).thenReturn(new CarEntity());

        assertEquals(false, carValidationService.isCarValid(car));
    }
}