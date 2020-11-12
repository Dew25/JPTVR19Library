/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creators;

import entity.Book;
import entity.controller.BookJpaController;
import factory.JPAControllerFactory;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Melnikov
 */
public class BookManager {
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
        new JPAControllerFactory().getBookController().create(book);
        return book;
    }

    public void addBookToArray(Book book, List<Book> listBooks) {
        listBooks.add(book);
    }

    public void printListBooks() {
        BookJpaController bookJpaController = new JPAControllerFactory().getBookController();
        List<Book> listBooks = bookJpaController.findListEntities();
        
        for (int i = 0; i < listBooks.size(); i++) {
            System.out.println(i+1+". "+listBooks.get(i).toString());
        }   
    }
    
}
