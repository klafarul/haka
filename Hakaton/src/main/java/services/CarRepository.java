package services;


import models.cars.CarEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarRepository {

    @Autowired
    private SessionFactory sessionFactory;


    public void save(CarEntity carEntity){
        Session session = sessionFactory.getCurrentSession();
        session.save(carEntity);

    }

    public long getCount() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select CE from CarEntity CE", CarEntity.class);
        return query.list().size();
    }
}
