/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creators;

import entity.Book;
import entity.History;
import entity.Reader;
import entity.User;
import factory.JPAControllerFactory;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import jptvr19library.App;
import security.SecureManager;

/**
 *
 * @author Melnikov
 */
public class LibraryManager {
    private Scanner scanner = new Scanner(System.in);
    private ReaderManager readerManager = new ReaderManager();
    private BookManager bookManager = new BookManager();
    

    public History takeOnBook() {
        History history = new History();
        // Вывести список читателей
        // Попросить пользователя выбрать номер читателя
        // По номеру читателя взять конкретного читателя из массива
        // Тоже самое проделать для читателя.
        // Инициировать history и отдать его return
        User loggedInUser = App.loginedUser;
        Reader reader = null;
        if("READER".equals(loggedInUser.getRole())){
            reader = loggedInUser.getReader();
        }else if("MANAGER".equals(loggedInUser.getRole())){
            System.out.println("--- Список читателей ---");
            readerManager.printListReaders();
            System.out.print("Выберите номер читателя: ");
            Long readerNumber = scanner.nextLong();
            scanner.nextLine();
            reader = new JPAControllerFactory().getReaderController().findEntity(readerNumber);
        }
        history.setReader(reader);
        bookManager.printListBooks();
        System.out.print("Выберите номер книги: ");
        Long bookNumber = scanner.nextLong();
        scanner.nextLine();
        Book book = new JPAControllerFactory().getBookController().findEntity(bookNumber);
        history.setBook(book);
        Calendar calendar = new GregorianCalendar();
        history.setGiveOutDate(calendar.getTime());
        new JPAControllerFactory().getHistoryController().create(history);
        this.printHistory(history);
        return history;
    }

    public void returnBook() {
        System.out.println("--- Список выданных книг ---");
        if(!printListReadBooks()){
           return;
        }
        System.out.print("Выберите номер возвращаемой книги: ");
        Long historyNumber = scanner.nextLong();
        scanner.nextLine();
        Calendar calendar = new GregorianCalendar();
        History history = new JPAControllerFactory().getHistoryController().findEntity(historyNumber);
        history.setReturnDate(calendar.getTime());
        new JPAControllerFactory().getHistoryController().edit(history);
    }

    

    private void printHistory(History history) {
        System.out.printf("Книга \"%s\" выдана %s %s%n"
                ,history.getBook().getName()
                ,history.getReader().getFirstname()
                ,history.getReader().getLastname()
        );
    }

    public boolean printListReadBooks() {
        List<History> listHistories = null;
        listHistories= new JPAControllerFactory().getHistoryController().findListReadBooks();
        if(listHistories != null && listHistories.size() > 0){
            for (int i = 0; i < listHistories.size(); i++) {
                System.out.printf("%d. Книгу \"%s\" читает %s %s%n" 
                        ,listHistories.get(i).getId()
                        ,listHistories.get(i).getBook().getName()
                        ,listHistories.get(i).getReader().getFirstname()
                        ,listHistories.get(i).getReader().getLastname()
                );
            }
            return true;
        }else{
            System.out.println("Нет читаемых книг.");
            return false;
        }
       
    }
    
}
