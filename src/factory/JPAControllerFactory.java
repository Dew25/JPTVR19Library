/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import entity.Book;
import entity.controller.BookJpaController;
import entity.controller.HistoryJpaController;
import entity.controller.ReaderJpaController;
import entity.controller.UserJpaController;

/**
 *
 * @author jvm
 */
public class JPAControllerFactory {
    public BookJpaController getBookController(){
        return new BookJpaController();
    }
    public ReaderJpaController getReaderController(){
        return new ReaderJpaController();
    }
    public UserJpaController getUserController(){
        return new UserJpaController();
    }
    public HistoryJpaController getHistoryController(){
        return new HistoryJpaController();
    }
    
}
