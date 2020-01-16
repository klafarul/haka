package services;


import models.address.AddressEntity;
import models.person.PersonEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import java.util.ArrayList;

@Repository
public class AddressRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(AddressEntity addressEntity){
        Session session = sessionFactory.getCurrentSession();

        session.save(addressEntity);
    }

    public void update(AddressEntity addressEntity, PersonEntity personEntity){

        Session session = sessionFactory.getCurrentSession();

        addressEntity = session.get(AddressEntity.class,addressEntity.getId());
        addressEntity.addPerson(personEntity);
    }

    public AddressEntity find(AddressEntity addressEntity){

        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM AddressEntity AE WHERE (AE.city=:city) AND (AE.house=:house) AND (AE.apartment=:apartment)";
        Query query = session.createQuery(hql);
        query.setParameter("city", addressEntity.getCity());
        query.setParameter("house", addressEntity.getHouse());
        query.setParameter("apartment", addressEntity.getApartment());

        ArrayList<AddressEntity> addressEntities = (ArrayList<AddressEntity>) query.list();

        if (addressEntities.size() > 0){
            return addressEntities.get(0);
        }
        return null;
    }


    public ArrayList<AddressEntity> findAll(){

        Session session = sessionFactory.getCurrentSession();

        ArrayList<AddressEntity> addressesEntity = (ArrayList<AddressEntity>) session.createQuery("select distinct  AE FROM AddressEntity AE left JOIN fetch AE.persons order by AE.city", AddressEntity.class).list();
        System.out.println("LOOOOOK HERE: " + addressesEntity.size());

        return addressesEntity;
    }

    public int getId(AddressEntity address){

        Session session = sessionFactory.openSession();
        AddressEntity addressEntity = find(address);
        session.close();
        return addressEntity.getId();
    }
}