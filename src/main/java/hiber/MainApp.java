package hiber;

import hiber.config.AppConfig;
import hiber.dao.UserDaoImp;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      //создаем юзеров с машинами
      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      Car car1 = new Car("lada", 111);
      user1.setCar(car1);

      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      Car car2 = new Car("oka", 222);
      user2.setCar(car2);

      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      Car car3 = new Car("buhanka",333);
      user3.setCar(car3);

      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      Car car4 = new Car("gelik", 444);
      user4.setCar(car4);

      //добавляем в базу
      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      //получаем из базы
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      //полуаем юзера(ов) по модели и серии машины
      List<User> users1 = userService.getUserByCarModelAndSeries(car2.getModel(), car2.getSeries());
      for (User user : users1){
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      context.close();
   }
}
