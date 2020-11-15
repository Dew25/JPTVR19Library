/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import entity.History;
import entity.Reader;
import factory.ConnectSingleton;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import jptvr19library.App;
import security.SecureManager;

/**
 *
 * @author jvm
 */
public class HistoryFacade extends AbstractFcade<History> {
    
    @Override
    protected EntityManager getEntityManager() {
        ConnectSingleton connect = ConnectSingleton.getInstance();
        return connect.getEntityManager();
    }

    public HistoryFacade() {
        super(History.class);
    }

    public List<History> findListReadBooks() {
        EntityManager em = null;
        em = getEntityManager();
        List<History> listHistoryies = new ArrayList<>();
        Reader reader = App.loginedUser.getReader();
        if(SecureManager.role.MANAGER.toString().equals(App.loginedUser.getRole())){
            listHistoryies = (List<History>)em.createQuery("SELECT h FROM History h WHERE h.returnDate = NULL")
            .getResultList();
        }else if(SecureManager.role.READER.toString().equals(App.loginedUser.getRole()))
            listHistoryies = (List<History>) em.createQuery("SELECT h FROM History h WHERE h.reader = :reader AND h.returnDate = NULL")
            .setParameter("reader", reader)
            .getResultList();
        return listHistoryies;
    }
}
