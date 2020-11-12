/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import entity.User;
import factory.JPAControllerFactory;
import java.util.Scanner;
import tools.creators.UserManager;


/**
 *
 * @author Melnikov
 */
public class SecureManager {
    
private Scanner scanner = new Scanner(System.in);
private User user;
public static enum role {
        READER, 
        MANAGER
};

    public User checkTask() {
        do{
            String task = this.printCheckTasks();
            switch (task) {
                case "0":
                    System.out.println("Выход из программы. Пока.");
                    System.exit(0);
                    break;
                case "1":
                    this.registration();
                    break;
                case "2":
                    User user = this.checkInUser();
                    if(user != null) return user;
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

    private void registration() {
        UserManager userManager = new UserManager();
        this.user = userManager.createUser();
    }

    private User checkInUser() {
        System.out.println("--- Вход в систему ---");
        System.out.println("Введите логин: ");
        String login = scanner.nextLine();
        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();
        User user = new JPAControllerFactory().getUserController().findByLogin(login);
        if(user == null) {
            System.out.println("У вас нет доступа. Зарегистрируйтесь");
            System.exit(0);
        }
        for (int j = 0; j < 2; j++) {
            if(password.equals(user.getPassword())){//Authorization
                return user;
            }else{
                System.out.print("Попробуй еще раз: ");
                password = scanner.nextLine();
            }
        }
        System.out.println("У вас нет доступа. Зарегистрируйтесь.");
        return null;
    }
    
}
