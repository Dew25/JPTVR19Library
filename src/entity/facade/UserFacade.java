/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.facade;

import entity.User;
import factory.ConnectSingleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Melnikov
 */
public class UserFacade extends AbstractFacade<User>{
    private EntityManager em;

    public UserFacade() {
        super(User.class);
        ConnectSingleton connect = ConnectSingleton.getInstance();
        em = connect.getEntityManager();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
   
}
