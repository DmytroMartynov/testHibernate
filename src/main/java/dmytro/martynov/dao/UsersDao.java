package dmytro.martynov.dao;

import dmytro.martynov.model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class UsersDao implements Storage {

    private SessionFactory sessionFactory;
    private int id = 0;

    public UsersDao() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void close() {
        sessionFactory.close();
    }

    @Override
    public void removeAll() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = String.format("DELETE FROM %s", Users.class.getSimpleName());
            Query query = session.createQuery(hql);
            query.executeUpdate();
            transaction.commit();
        }
    }


    @Override
    public void removeUser(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Object persistentInstance = session.load(Users.class, id);
            if (persistentInstance != null) {
                session.delete(persistentInstance);
                transaction.commit();
            }

        }

    }

    @Override
    public void removeUserByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Users users = new Users();
            users.setName(name);
            session.delete(users);
            transaction.commit();
        }
    }


    @Override
    public void addUser(Users user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            user.setId(id++);
            session.save(user);
            transaction.commit();
        }
    }

    @Override
    public void updateUser(Users user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }
    }

    @Override
    public Users getUsers(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Users WHERE _id = '" + id + "'", Users.class).getSingleResult();
        }
    }

    @Override
    public List< Users > getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Users", Users.class).list();
        }
    }

}
