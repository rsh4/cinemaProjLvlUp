package net.nelevelup.proj.service;

import net.nelevelup.proj.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    @Transactional
    public boolean isUserExists(String username){
        return userDAO.isUserExists(username);
    }

    @Transactional
    public void registerUser(String username, String password){
        userDAO.registerUser(username, password);
    }
}
