/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jvm
 */
public class ConnectSingleton {
    private static ConnectSingleton instance;
    private EntityManager em;
    private  ConnectSingleton() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPTVR19LibraryPU");
        this.em = emf.createEntityManager();
        
    }
    public EntityManager getEntityManager(){
        return em;
    }
    public static ConnectSingleton getInstance(){
        if(instance == null){
            instance = new ConnectSingleton();
        }
        return instance;
    }
}
