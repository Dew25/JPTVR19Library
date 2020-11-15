/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import entity.User;
import factory.ConnectSingleton;
import javax.persistence.EntityManager;

/**
 *
 * @author jvm
 */
public class UserFacade extends AbstractFcade<User> {
   
    @Override
    protected EntityManager getEntityManager() {
        ConnectSingleton connect = ConnectSingleton.getInstance();
        return connect.getEntityManager();
    }

    public UserFacade() {
        super(User.class);
    }

    public User findByLogin(String login) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            User user = (User)em.createQuery("SELECT u FROM User u WHERE u.login=:login")
                .setParameter("login", login)
                .getSingleResult();
            return user; 
        } catch (Exception e) {
            System.out.println("error: "+e);
            return null;
        }
    }
    
}
