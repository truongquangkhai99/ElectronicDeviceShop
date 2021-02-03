package castudy.service;

import castudy.dao.UserDao;
import castudy.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService implements BaseService<User> {
    private static UserDao userDao=new UserDao();

    @Override
    public void insert(User user) throws SQLException {
        userDao.insert(user);
    }

    public User checkLoginAdmin(String userName,String password) {
        return userDao.checkLoginAdmin(userName,password);
    }

    public User checkLoginCustomer(String userName,String password){
        return userDao.checkLoginCustomer(userName,password);
    }

    @Override
    public User selectOne(int id) {
        return null;
    }

    @Override
    public List<User> selectAll() {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(User element) throws SQLException {
        return false;
    }
}
