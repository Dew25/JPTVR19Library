/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import entity.Reader;
import entity.User;
import java.util.Scanner;
import tools.creators.ReaderManager;
import tools.creators.UserManager;
import tools.savers.ReaderSaver;
import tools.savers.UserSaver;

/**
 *
 * @author Melnikov
 */
public class SecureManager {
    
private Scanner scanner = new Scanner(System.in);
public static enum role {
        READER, 
        MANAGER
};

    public User checkTask(User[] users, Reader[] readers) {
        // Предоставим выбор пользователю:
        //  0. Выход из программы
        //  1. Регистрация
        //  2. Вход в систему
        //спросить у польльзователя логин и пароль.
        // пройти по массиву пользователей и найти объект User 
        // у которого совпадают логины (Authentication)
        // - если user не найден -> дадим возможность зарегистрироваться.
        //сравнить пароли у user.getPassword() и password
        // -- если совпадают -> возвращаем объект пользователя. (Authorization)
        // -- иначе дадим еще две попытки ввести пароль, после чего
        // -- выход из программы System.exit(0);
        
        do{
            String task = this.printCheckTasks();
            switch (task) {
                case "0":
                    System.out.println("Выход из программы. Пока.");
                    System.exit(0);
                    break;
                case "1":
                    this.registration(users,readers);
                    break;
                case "2":
                    return this.checkInUser(users);
                    
                default:
                    System.out.println("Выберите указанные задачи.");;
            }
        }while(true);
        
    }
    private String printCheckTasks(){
        System.out.println("--- Вход в систему ---");
        System.out.println("0. Выйти из программы");
        System.out.println("1. Регистрация");
        System.out.println("2. Вход в систему");
        System.out.print("Выберите номер задачи: ");
        String numTask = scanner.nextLine();
        return numTask;
    }

    private void registration(User[] users, Reader[] readers) {
        UserManager userManager = new UserManager();
        User user = userManager.createUser();
        userManager.addUserToArray(user, users);
        ReaderManager readerManager = new ReaderManager();
        readerManager.addReaderToArray(user.getReader(), readers);
        ReaderSaver readerSaver = new ReaderSaver();
        readerSaver.saveReaders(readers);
        UserSaver userSaver = new UserSaver();
        userSaver.saveUsers(users);
    }

    private User checkInUser(User[] users) {
        System.out.println("--- Вход в систему ---");
        System.out.println("Введите логин: ");
        String login = scanner.nextLine();
        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();
        for (int i = 0; i < users.length; i++) {
            User user = users[i];
            if(user == null) continue;
            if(login.equals(user.getLogin())){//Authetication
                for (int j = 0; j < 2; j++) {
                    if(password.equals(user.getPassword())){//Authorization
                        return user;
                    }else{
                        System.out.print("Попробуй еще раз: ");
                        password = scanner.nextLine();
                    }
                }
                System.out.println("У вас нет доступа.");
                System.exit(0);
            }
        }
        System.out.println("У вас нет доступа. Зарегистрируйтесь.");
        System.exit(0);
        return null;
    }
    
}
