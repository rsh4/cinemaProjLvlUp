package net.nelevelup.proj.dao;

import net.nelevelup.proj.entity.users.Role;
import net.nelevelup.proj.entity.users.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserDAO {

    @Autowired
    SessionFactory sessionFactory;

    public User findByUsername(String username){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where username = :currentUsername");
        query.setParameter("currentUsername", username);
        return (User)query.uniqueResult();
    }

    public boolean isUserExists(String username){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where username = :currentUser");
        query.setParameter("currentUser", username);
        return query.uniqueResult() != null;
    }

    public void registerUser(String username, String password){
        Session session = sessionFactory.getCurrentSession();
        User user = new User(username, password);
        ArrayList<Role> roles = new ArrayList<Role>();
        Query query = session.createQuery("from Role where id = :id");
        query.setParameter("id", 1);
        roles.add((Role)query.uniqueResult());
        user.setRoles(roles);
        session.save(user);
    }
}
