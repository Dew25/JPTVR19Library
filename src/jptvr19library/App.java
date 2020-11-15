/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jptvr19library;

import entity.User;
import factory.ConnectSingleton;
import javax.persistence.EntityManager;
import security.SecureManager;
import ui.ManagerUI;
import ui.ReaderUI;

/**
 *
 * @author Melnikov
 */
public class App {

    public static User loginedUser;
    
    public App() {
    }
    
    public void run(){
        try{
            boolean repeat = true;
            System.out.println("--- Библиотека ---");
            this.loginedUser = new SecureManager().checkTask();
            if(SecureManager.role.MANAGER.toString().equals(this.loginedUser.getRole())){
                new ManagerUI().getManagerUI();
            }else if(SecureManager.role.READER.toString().equals(this.loginedUser.getRole())){
                ReaderUI readerUI = new ReaderUI();
                readerUI.getReaderUI();
            }
        } finally {
            ConnectSingleton connect = ConnectSingleton.getInstance();
            EntityManager em = connect.getEntityManager();
            em.close();
        }
    }
}
