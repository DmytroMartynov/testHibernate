package dmytro.martynov;

import dmytro.martynov.dao.UsersDao;
import dmytro.martynov.model.Users;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UsersDao usersDao = new UsersDao();

        Users user1 = new Users("Maxim", 10);
        Users user2 = new Users("Sasha", 15);
        Users user3 = new Users("Vasyok", 26);

        usersDao.removeAll();
        usersDao.addUser(user1);
        usersDao.addUser(user2);
        usersDao.addUser(user3);
        usersDao.removeUser(1);
        usersDao.removeUserByName("Maxim");
        user3.setName("igor");
        usersDao.updateUser(user3);

        List< Users > usersList = usersDao.getAllUsers();
        System.out.println(usersList);
        usersDao.close();


    }
}
