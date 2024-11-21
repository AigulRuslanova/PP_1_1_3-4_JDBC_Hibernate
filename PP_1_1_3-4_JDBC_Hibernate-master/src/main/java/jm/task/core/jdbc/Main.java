package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
//    public static void main(String[] args) {
//
//        UserService userService = new UserServiceImpl();
//        userService.createUsersTable();
//        userService.saveUser("Адам", "Саипов", (byte) 10);
//        userService.saveUser("Агата", "Кристи", (byte) 19);
//        userService.saveUser("Руслан", "Павлов", (byte) 22);
//        userService.saveUser("Айгуль", "Русланова", (byte) 22);
//
//        System.out.println(userService.getAllUsers());
//        userService.cleanUsersTable();
//
//        userService.dropUsersTable();
//
//
//    }
public static void main(String[] args) {
    UserService userService = new UserServiceImpl();

    userService.dropUsersTable();

}

    private static void print(String name) {
        System.out.println("User с именем — " + name + " добавлен в базу данных");
    }

}
