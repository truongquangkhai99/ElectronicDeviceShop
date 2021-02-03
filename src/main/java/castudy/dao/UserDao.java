package castudy.dao;

import castudy.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao extends DAOHelper implements BaseDAO<User>  {
    private static final String INSERT_ACCOUNT="INSERT INTO public.user (\"userName\",\"password\",\"chucvu\",\"email\") VALUES(?,?,?,?);";

    private static final String SELECT_USER="SELECT * FROM public.user where \"userName\"=? and \"password\"=?;";

    @Override
    public void insert(User account) throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT)) {
            preparedStatement.setString(1, account.getUserName());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getChucVu());
            preparedStatement.setString(4, account.getEmail());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public User checkLoginAdmin(String userName,String password) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER)) {

            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String chucVu=rs.getString("chucvu");
                String email=rs.getString("email");
                User user = new User(userName, password,email,chucVu);
                if(chucVu.equals("admin")){
                    return user;
                }else return null;
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    public User checkLoginCustomer(String userName,String password){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String chucVu=rs.getString("chucvu");
                String email=rs.getString("email");
                User user = new User(userName,password,email,chucVu);
                return user;
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
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
