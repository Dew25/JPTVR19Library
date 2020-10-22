/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jptvr19library;

import tools.savers.HistorySaver;
import tools.creators.LibraryManager;
import tools.savers.BookSaver;
import tools.creators.ReaderManager;
import entity.Book;
import entity.History;
import entity.Reader;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import security.SecureManager;
import tools.creators.BookManager;
import tools.savers.ReaderSaver;
import tools.savers.UserSaver;
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

    private BookSaver bookSaver = new BookSaver();
    private ReaderSaver readerSaver = new ReaderSaver();
    private HistorySaver historySaver = new HistorySaver();
    private UserSaver userSaver = new UserSaver();

    private SecureManager secureManager = new SecureManager();
    
    public static User loginedUser;
    
    public App() {
        listBooks = bookSaver.loadBooks();
        listReaders = readerSaver.loadReaders();
        listHistories = historySaver.loadHistories();
        listUsers = userSaver.loadUsers();
    }
    
    public void run(){
        boolean repeat = true;
        System.out.println("--- Библиотека ---");
        this.loginedUser = secureManager.checkTask(listUsers,listReaders);
        if(SecureManager.role.MANAGER.toString().equals(this.loginedUser.getRole())){
            ManagerUI managerUI = new ManagerUI();
            managerUI.getManagerUI(listReaders, listUsers, listBooks, listHistories);
        }else if(SecureManager.role.READER.toString().equals(this.loginedUser.getRole())){
            ReaderUI readerUI = new ReaderUI();
            readerUI.getReaderUI(listReaders, listUsers, listBooks, listHistories);
        }
    }
}
