/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jvm
 */
public class UserJpaController extends AbstractJpaController<User> {
    
    private EntityManager em;
   
    @Override
    protected EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPTVR19LibraryPU");
        this.em = emf.createEntityManager();
        return em;
    }

    public UserJpaController() {
        super(User.class);
    }

    public User findByLogin(String login) {
        try {
            User user = (User)getEntityManager().createQuery("SELECT u FROM User u WHERE u.login=:login")
                .setParameter("login", login)
                .getSingleResult();
            return user; 
        } catch (Exception e) {
            System.out.println("error: "+e.getMessage());
            return null;
        }
    }
    
}
