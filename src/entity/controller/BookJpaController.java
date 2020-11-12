/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import entity.Book;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jvm
 */
public class BookJpaController extends AbstractJpaController<Book> {
    
    private EntityManager em;
   
    @Override
    protected EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPTVR19LibraryPU");
        return em = emf.createEntityManager();
    }

    public BookJpaController() {
        super(Book.class);
    }
    
}
