package net.nelevelup.proj.dao;

import net.nelevelup.proj.entity.Cost;
import net.nelevelup.proj.entity.HallList;
import net.nelevelup.proj.entity.SessionList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExtraDao {

    @Autowired
    SessionFactory sessionFactory;

    public boolean costExistsById(Integer id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Cost.class, id) != null;
    }

    public boolean hallExistsById(Integer id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(HallList.class, id) != null;
    }

    public Cost getCostById(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Cost.class, id);
    }

    public HallList getHallListById(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(HallList.class, id);
    }

    public void saveSessionList(SessionList sessionList){
        Session session = sessionFactory.getCurrentSession();
        session.save(sessionList);
    }

    public List<Cost> getCosts(){
        Session session = sessionFactory.getCurrentSession();
        List<Cost> costs = session.createQuery("from Cost").list();
        return costs;
    }

    public List<HallList> getHallList(){
        Session session = sessionFactory.getCurrentSession();
        List<HallList> hallLists = session.createQuery("from HallList").list();
        return hallLists;
    }
}
