/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import entity.History;
import entity.Reader;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import jptvr19library.App;
import security.SecureManager;

/**
 *
 * @author jvm
 */
public class HistoryJpaController extends AbstractJpaController<History> {
    
    @Override
    protected EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPTVR19LibraryPU");
        EntityManager em = emf.createEntityManager();
        return em;
    }

    public HistoryJpaController() {
        super(History.class);
    }

    public List<History> findListReadBooks() {
        List<History> listHistoryies = new ArrayList<>();
        Reader reader = App.loginedUser.getReader();
        if(SecureManager.role.MANAGER.toString().equals(App.loginedUser.getRole())){
            listHistoryies = getEntityManager().createQuery("SELECT h FROM History h WHERE h.returnDate = NULL")
            .getResultList();
        }else if(SecureManager.role.READER.toString().equals(App.loginedUser.getRole()))
            listHistoryies = getEntityManager().createQuery("SELECT h FROM History h WHERE h.reader = :reader AND h.returnDate = NULL")
            .setParameter("reader", reader)
            .getResultList();
        return listHistoryies;
    }
    
}
