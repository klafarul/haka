package services;

import hibernateUtil.HibernateSessionFactoryUtil;
import models.address.Address;
import models.address.AddressEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import models.person.Person;
import models.person.PersonEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

//ПРЕОБРАЗУЮ ПЕРСОН В ПЕРСОНЭНТИТИ И ОБРАТНО
public class HibService {
    private static HibService hibService;

    private HibService(){
    }

    public static HibService getHibService(){
        if (hibService == null){
            hibService = new HibService();
        }
        return hibService;
    }
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
    public AddressEntity findByAddress(Address address){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        String hql = "FROM AddressEntity AE WHERE (AE.city=:city) AND (AE.house=:house) AND (AE.apartment=:apartment)";
        Query query = session.createQuery(hql);
        query.setParameter("city", (String)address.getCity());
        query.setParameter("house", address.getHouse());
        query.setParameter("apartment", address.getApartment());

        ArrayList<AddressEntity> addressEntities = (ArrayList<AddressEntity>) query.list();
        if (addressEntities.size() == 1){
            return addressEntities.get(0);
        }
        return null;
    }


    public void saveAddress(Address address){
        AddressEntity addressEntity = new AddressEntity(address);

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(addressEntity);
        tx.commit();
        session.close();
    }

    public void updateAddress(Address address){


        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        AddressEntity addressEntity = findByAddress(address);
        PersonEntity personEntity = new PersonEntity(address.getPersons().get(address.getPersons().size()-1));

        addressEntity.addPerson(personEntity);
        Transaction tx = session.beginTransaction();
        session.merge(addressEntity);
        tx.commit();
        session.close();
    }


    public boolean inDB(Address address) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        String hql = "FROM AddressEntity AE WHERE (AE.city=:city) AND (AE.house=:house) AND (AE.apartment=:apartment)";
        Query query = session.createQuery(hql);
        query.setParameter("city", (String)address.getCity());
        query.setParameter("house", address.getHouse());
        query.setParameter("apartment", address.getApartment());

        ArrayList<AddressEntity> addressEntities = (ArrayList<AddressEntity>) query.list();
        if (addressEntities.size() == 0){
            return false;
        }
        return true;

    }
    public List<Address> findAllAddresses(){
        ArrayList<AddressEntity> adressesEntity = (ArrayList<AddressEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM AddressEntity").list();

        List<Address> addresses = new ArrayList<>();

        for (int i = 0; i <adressesEntity.size(); i++){
            addresses.add(adressesEntity.get(i).toAddress());
        }
        return addresses;
    }

}
