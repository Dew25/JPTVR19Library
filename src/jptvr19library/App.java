/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jptvr19library;

import entity.Book;
import entity.History;
import entity.Reader;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import security.SecureManager;
import tools.savers.SaverInterface;
import tools.savers.SaverToBase;
import tools.savers.SaverToFile;
import ui.ManagerUI;
import ui.ReaderUI;

/**
 *
 * @author Melnikov
 */
public class App {
    private List<Book> listBooks = new ArrayList<>();
    private List<Reader> listReaders = new ArrayList<>();
    private List<History> listHistories = new ArrayList<>();
    private List<User> listUsers = new ArrayList<>();
//    private SaverInterface entitySaver = new SaverToFile();
    private SaverInterface entitySaver = new SaverToBase();
    private SecureManager secureManager = new SecureManager();
    
    public static User loginedUser;
    
    public App() {
        listBooks = entitySaver.load("books");
        listReaders = entitySaver.load("readers");
        listHistories = entitySaver.load("histories");
        listUsers = entitySaver.load("users");
    }
    
    public void run(){
        boolean repeat = true;
        System.out.println("--- Библиотека ---");
        this.loginedUser = secureManager.checkTask(listUsers,listReaders, entitySaver);
        if(SecureManager.role.MANAGER.toString().equals(this.loginedUser.getRole())){
            ManagerUI managerUI = new ManagerUI();
            managerUI.getManagerUI(listReaders, listUsers, listBooks, listHistories, entitySaver);
        }else if(SecureManager.role.READER.toString().equals(this.loginedUser.getRole())){
            ReaderUI readerUI = new ReaderUI();
            readerUI.getReaderUI(listReaders, listUsers, listBooks, listHistories, entitySaver);
        }
    }
}
