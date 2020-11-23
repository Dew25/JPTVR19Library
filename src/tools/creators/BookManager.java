/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creators;

import entity.Book;
import entity.facade.BookFacade;
import factory.FactoryFacade;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Melnikov
 */
public class BookManager {
        private BookFacade bookFacade = FactoryFacade.getBookFacade();
        private Scanner scanner = new Scanner(System.in);

    public Book createBook() {
        Book book = new Book();
        System.out.println("--- Создание книги ---");
        System.out.print("Введите имя книги: ");
        book.setName(scanner.nextLine());
        System.out.print("Введите автора книги: ");
        book.setAuthor(scanner.nextLine());
        System.out.print("Введите год издания книги: ");
        book.setPublishedYear(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Введите ISBN книги: ");
        book.setIsbn(scanner.nextLine());
        System.out.println("Создана книга: "+book.getName());
        bookFacade.create(book);
        return book;
    }

    public void printListBooks() {
        List<Book> listBooks = bookFacade.findAll();
        for (int i = 0; i < listBooks.size(); i++) {
            if(listBooks.get(i) != null){
                System.out.println(listBooks.get(i).getId()+". " + listBooks.get(i).toString());
            }
        }   
    }
    
}
