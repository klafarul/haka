package services;


import models.cars.CarEntity;
import models.person.Person;
import models.person.PersonEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonRepository {

    @Autowired
    private SessionFactory sessionFactory;


    public void save(PersonEntity personEntity){
        Session session = sessionFactory.getCurrentSession();
        session.save(personEntity);
    }

    public PersonEntity find(PersonEntity personEntity){
        Session session = sessionFactory.openSession();
        PersonEntity pe = session.find(PersonEntity.class, personEntity);
        session.close();
        return pe;
    }

    public PersonEntity findById(long id) {

        Session session = sessionFactory.getCurrentSession();
        return session.get(PersonEntity.class, id);


    }

    public void update(PersonEntity personEntity) {
    }

//    public PersonEntity getById(int id){
//        Session session = sessionFactory.getCurrentSession();
//
//
//    }
}
