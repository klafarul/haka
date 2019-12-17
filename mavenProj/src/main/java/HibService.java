import hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import person.Pers;

import java.util.List;

public class HibService {
    public Pers findById(int id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Pers.class, id);
    }
    public List<Pers> findAllPersons(){
        List<Pers> pers = (List<Pers>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM Pers", Pers.class).list();
        return pers;
    }
    public void savePerson(Pers pers){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(pers);
        tx.commit();
        session.close();
    }

}
