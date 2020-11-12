/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creators;

import entity.Reader;
import entity.controller.ReaderJpaController;
import factory.JPAControllerFactory;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Melnikov
 */
public class ReaderManager {
    private Scanner scanner = new Scanner(System.in);
    public Reader createReader() {
        Reader reader = new Reader();
        System.out.println("--- Регистрация нового пользователя ---");
        System.out.print("Введите имя: ");
        reader.setFirstname(scanner.nextLine());
        System.out.print("Введите фамилию: ");
        reader.setLastname(scanner.nextLine());
        System.out.print("Введите телефон: ");
        reader.setPhone(scanner.nextLine());
        this.printReader(reader);
        JPAControllerFactory jpaControllerFactory = new JPAControllerFactory();
        jpaControllerFactory.getReaderController().create(reader);
        return reader;
    }

    public void printReader(Reader reader) {
        System.out.println("Имя читателя: "
                +reader.getFirstname()
                +" "
                + reader.getLastname()
        );
    }

    public void printListReaders() {
        ReaderJpaController readerJpaController = new JPAControllerFactory().getReaderController();
        List<Reader> listReaders = readerJpaController.findListEntities();
        
        for (int i = 0; i < listReaders.size(); i++) {
            System.out.println(i+1+". "+listReaders.get(i).toString());
        }   
    }
    
}
