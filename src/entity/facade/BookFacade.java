/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.facade;

import entity.Book;
import factory.ConnectSingleton;

import javax.persistence.EntityManager;


/**
 *
 * @author Melnikov
 */
public class BookFacade extends AbstractFacade<Book>{

    private EntityManager em;

    public BookFacade() {
        super(Book.class);
        ConnectSingleton connect = ConnectSingleton.getInstance();
        em = connect.getEntityManager();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
}
