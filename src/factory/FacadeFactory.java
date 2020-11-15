/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import entity.controller.BookFacade;
import entity.controller.HistoryFacade;
import entity.controller.ReaderFacade;
import entity.controller.UserFacade;

/**
 *
 * @author jvm
 */
public class FacadeFactory {
    public BookFacade getBookFacade(){
        return new BookFacade();
    }
    public ReaderFacade getReaderFacade(){
        return new ReaderFacade();
    }
    public UserFacade getUserFacade(){
        return new UserFacade();
    }
    public HistoryFacade getHistoryFacade(){
        return new HistoryFacade();
    }
    
}
