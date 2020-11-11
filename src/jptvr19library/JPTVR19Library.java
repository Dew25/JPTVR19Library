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
 */
package jptvr19library;

/**
 *
 * @author Melnikov
 */
public class JPTVR19Library {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String param = "base";
        if(args.length > 0){
            param = args[0];
        }
        
        App app = new App(param);
        app.run();
    }
    
}
