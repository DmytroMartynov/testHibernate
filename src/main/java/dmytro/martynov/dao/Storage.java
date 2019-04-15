package dmytro.martynov.dao;

import dmytro.martynov.model.Users;

import java.util.List;

public interface Storage {

    void removeAll();


    void removeUser(int id);


    void removeUserByName(String name);


    void addUser(Users user);


    void updateUser(Users user);


    Users getUsers(int id);


    List< Users > getAllUsers();
}
