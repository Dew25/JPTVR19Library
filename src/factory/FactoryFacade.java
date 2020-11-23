/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import entity.facade.BookFacade;
import entity.facade.HistoryFacade;
import entity.facade.ReaderFacade;
import entity.facade.UserFacade;

/**
 *
 * @author Melnikov
 */
public class FactoryFacade {
    public static BookFacade getBookFacade(){
        return new BookFacade();
    }
    public static ReaderFacade getReaderFacade(){
        return new ReaderFacade();
    }
    public static UserFacade getUserFacade(){
        return new UserFacade();
    }
    public static HistoryFacade getHistoryFacade(){
        return new HistoryFacade();
    }
}
