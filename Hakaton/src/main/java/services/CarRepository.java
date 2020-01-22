package services;


import models.cars.CarEntity;
import models.person.PersonEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarRepository {

    @Autowired
    private SessionFactory sessionFactory;


    public void save(CarEntity carEntity, PersonEntity personEntity){
        Session session = sessionFactory.getCurrentSession();
        session.save(carEntity);

    }


}
