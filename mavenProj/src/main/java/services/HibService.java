package services;

import hibernateUtil.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import person.Person;
import person.PersonEntity;

import java.util.ArrayList;
import java.util.List;

//ПРЕОБРАЗУЮ ПЕРСОН В ПЕРСОНЭНТИТИ И ОБРАТНО
public class HibService {
    public Person findById(int id){
        PersonEntity personEntity = HibernateSessionFactoryUtil.getSessionFactory().openSession().get(PersonEntity.class, id);
        Person person = personEntity.toPerson();
        return person;
    }
    public List<Person> findAllPersons(){
        ArrayList<PersonEntity> personsEntity = (ArrayList<PersonEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM PersonEntity").list();

        List<Person> persons = new ArrayList<>();

        for (int i = 0; i <personsEntity.size(); i++){
            persons.add(personsEntity.get(i).toPerson());
        }

        return persons;
    }
    public void savePerson(Person person){
        PersonEntity personEntity = new PersonEntity(person);

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(personEntity);
        tx.commit();
        session.close();
    }

}
