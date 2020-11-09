/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.savers;

import entity.Book;
import entity.History;
import entity.Reader;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Melnikov
 */
public class SaverToFile {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPTVR19LibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    
    public void save(List arrayList, String fileName){
        
        tx.begin();
        for (int i = 0; i < arrayList.size(); i++) {
            if(Reader.class.equals(arrayList.get(i).getClass())){
                List<Reader> listReaders = (List<Reader>)arrayList;
                if(listReaders.get(i).getId()== null){
                    em.persist(listReaders.get(i));
                }
            }
            if(User.class.equals(arrayList.get(i).getClass())){
                List<User> listUsers = (List<User>)arrayList;
                if(listUsers.get(i).getId()== null){
                    em.persist(listUsers.get(i));
                }
            }
            if(Book.class.equals(arrayList.get(i).getClass())){
                List<Book> listBooks = (List<Book>)arrayList;
                if(listBooks.get(i).getId()== null){
                    em.persist(listBooks.get(i));
                }
            }
            if(History.class.equals(arrayList.get(i).getClass())){
                List<History> listHistories = (List<History>)arrayList;
                if(listHistories.get(i).getId()== null){
                    em.persist(listHistories.get(i));
                }else{
                    em.merge(listHistories.get(i));
                }
            }
            
        }
        tx.commit();
//        FileOutputStream fos = null;
//        ObjectOutputStream oos = null;
//        try {
//            fos = new FileOutputStream(fileName);
//            oos = new ObjectOutputStream(fos);
//            oos.writeObject(arrayList);
//            oos.flush();
//        } catch (FileNotFoundException ex) {
//            System.out.println("Не найден файл");
//        } catch (IOException ex) {
//            System.out.println("Ошибка ввода/вывода");
//        }

    }
    public List load(String fileName){
        try {
            
            String className="";
            switch (fileName) {
                case "readers":
                    className = "Reader";
                    break;
                case "books":
                    className = "Book";
                    break;
                case "users":
                    className = "User";
                    break;
                case "histories":
                    className = "History";
                    break;

            }
            return em.createQuery("SELECT entity FROM "+className+" entity")
                    .getResultList();
        } catch (Exception e) {
            System.out.println("Нет такой записи");
            return new ArrayList();
        }
//        List arrayList = new ArrayList();
//        FileInputStream fis = null;
//        ObjectInputStream ois = null;
//        try {
//            fis = new FileInputStream(fileName);
//            ois = new ObjectInputStream(fis);
//            arrayList = (List) ois.readObject();
//        } catch (FileNotFoundException ex) {
//            System.out.println("Не найден файл");
//        } catch (IOException ex) {
//            System.out.println("Ошибка ввода/вывода");
//        } catch (ClassNotFoundException ex) {
//            System.out.println("Не найден класс");
//        }
//        return arrayList;
    } 

    public Book saveBook(Book book) {
        tx.begin();
            if(book.getId() == null){
                em.persist(book);
            }else{
                em.merge(book);
            }
        tx.commit();
        return book;
    }
}
