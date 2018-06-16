package net.nelevelup.proj.dao;

import net.nelevelup.proj.entity.Cinema;
import net.nelevelup.proj.entity.SessionList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SessionDAO {

    @Autowired
    SessionFactory sessionFactory;

    public List<SessionList> getSessionsByCityAndFilmNameAndCinema(String city,
                                                                   String filmName, String cinema){
        ArrayList<ArrayList<SessionList>> sessions = new ArrayList<ArrayList<SessionList>>();
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from SessionList where " +
                "hallList.cinema.system.city = :currentCity " +
                "and session.product.film.title = :filmname " +
                "and hallList.cinema.name = :currentCinema");
        query.setParameter("currentCity", city);
        query.setParameter("filmname", filmName);
        query.setParameter("currentCinema", cinema);
        return (List<SessionList>)query.list();
    }
    public List<Cinema> getCinemasByCity(String city){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Cinema where system.city = :currentCity");
        query.setParameter("currentCity", city);
        return (List<Cinema>)query.list();
    }

    public List<net.nelevelup.proj.entity.Session> getSessions(){
        Session session = sessionFactory.getCurrentSession();
        List<net.nelevelup.proj.entity.Session> sessions = session.createQuery("from SessionList").list();
        return sessions;
    }

    public void saveSession(net.nelevelup.proj.entity.Session session){
        Session hibSession = sessionFactory.getCurrentSession();
        hibSession.save(session);
    }

    public void deleteSessionById(Integer id){
        Session session = sessionFactory.getCurrentSession();
        net.nelevelup.proj.entity.Session session1 = session.get(net.nelevelup.proj.entity.Session.class, id);
        session.remove(session1);
    }

    public boolean sessionExistsById(Integer id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(net.nelevelup.proj.entity.Session.class, id) != null;
    }
}
