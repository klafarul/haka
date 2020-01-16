package services;

import models.person.PersonEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PersonRepository {

    @Autowired
    SessionFactory sessionFactory;

    public void save(PersonEntity personEntity){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(personEntity);
        tx.commit();
        session.close();
    }
    public void update(PersonEntity personEntity){

    }
}
