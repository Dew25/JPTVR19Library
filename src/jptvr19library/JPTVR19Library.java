/**
 * Шаги подключения базы к приложению.
 * 1. Запустить базу данных ("jptvr19library")
 * 2. Создать пользователя базы (user = "jptvr19library") и пароль (password = "jptvr19library")
 * 3. Подключить поставщика постоянства (persistence provider) EclipseLink(JPA 2.1)
 * 4. Подлючить драйвер базы даннх (mysql-connector)
 * 5. Сущностные классы пометить аннотациями (@Entity, @Id, @GenerationValue и др.  (см. Изучаем Java EE 7))
 * 6. Создать persistence unit в файле persistence.xml
 * 7. Добавить сущности в файл persistence.xml
 * 8. Создать класс, который отвечает за работу с базой данных (SaveToBase)
 * 9. В бизнес-логике приложения использовать объект этого класса для сохранения данных в базу
 * 
 * Следующий шаг - Работа только с базой данных, минуя хранилище в памяти (массивы в классе App)
 * Итак, программа может работать только с базой.
 * В памяти не хранятся списки книг, читателей, ипользователей и истории
 * Если нужен список, он считывается из базы, испольуется и освобождается (сборщиком мусора)
 * Так же и отдельные сущности считываются из базы по одной.
 * Для того, чтобы в любом месте программы можно было воспользоваться фасадом сущности (патерт "Фасад"
 * используется фабрика FacadeFactory (паттерн "Фабричный метод")
 * Недостаток, который требуется устранить:
 * Каждый фасад создает свое подключение к базе. Это трудоемкая и долгая операция.
 * Решение: 
 * Создать статический класс - одиночку (singleton), в котором будет создаваться подключение и оно будет доступно 
 * во всех фасадах создаваемых фабрикой
 */
package jptvr19library;

import factory.ConnectSingleton;

/**
 *
 * @author Melnikov
 */
public class JPTVR19Library {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        App app = new App();
        try {
            app.run();
        } finally {
            ConnectSingleton connect = ConnectSingleton.getInstance();
            if(connect.getEntityManager() != null){
                connect.getEntityManager().close();
            }
            if(connect.getEntityManagerFactory() != null){
                connect.getEntityManagerFactory().close();
            }
        }
    }
    
}
