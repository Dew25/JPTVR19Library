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
    private Book[] books = new Book[10];
    private Reader[] readers = new Reader[10];
    private History[] histories = new History[10];
    private User[] users = new User[10];
    private BookManager bookManager = new BookManager();
    private ReaderManager readerManager = new ReaderManager();
    private LibraryManager libraryManager = new LibraryManager();
    private BookSaver bookSaver = new BookSaver();
    private ReaderSaver readerSaver = new ReaderSaver();
    private HistorySaver historySaver = new HistorySaver();
    private SecureManager secureManager = new SecureManager();
    private UserSaver userSaver = new UserSaver();

    public static User loginedUser;
    
    public App() {
        books = bookSaver.loadBooks();
        readers = readerSaver.loadReaders();
        histories = historySaver.loadHistories();
        users = userSaver.loadUsers();
    }
    
    public void run(){
        
        boolean repeat = true;
        System.out.println("--- Библиотека ---");
        this.loginedUser = secureManager.checkTask(users,readers);
        if("MANAGER".equals(this.loginedUser.getRole())){
            ManagerUI managerUI = new ManagerUI();
            managerUI.getManagerUI(readers, users, books, histories);
        }else if("READER".equals(this.loginedUser.getRole())){
            ReaderUI readerUI = new ReaderUI();
            readerUI.getReaderUI(readers, users, books, histories);
        }
    }
}
