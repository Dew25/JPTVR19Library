/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jptvr19library;

import entity.User;
import security.SecureManager;
import ui.ManagerUI;
import ui.ReaderUI;

/**
 *
 * @author Melnikov
 */
public class App {
   
    private SecureManager secureManager = new SecureManager();
    
    public static User loginedUser;
    
    public App() {
               
    }
    
    public void run(){
        boolean repeat = true;
        System.out.println("--- Библиотека ---");
        this.loginedUser = secureManager.checkTask();
        if(SecureManager.role.MANAGER.toString().equals(this.loginedUser.getRole())){
            ManagerUI managerUI = new ManagerUI();
            managerUI.getManagerUI();
        }else if(SecureManager.role.READER.toString().equals(this.loginedUser.getRole())){
            ReaderUI readerUI = new ReaderUI();
            readerUI.getReaderUI();
        }
    }
}
