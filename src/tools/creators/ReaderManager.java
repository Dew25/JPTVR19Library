/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creators;

import entity.Reader;
import entity.controller.ReaderFacade;
import factory.FacadeFactory;
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
        new FacadeFactory().getReaderFacade().create(reader);
        this.printReader(reader);
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
        List<Reader> listReaders = new FacadeFactory().getReaderFacade().findListEntities();
        for (int i = 0; i < listReaders.size(); i++) {
            System.out.println(listReaders.get(i).getId()+". "+listReaders.get(i).toString());
        }   
    }
    
}
