package castudy.dao;

import castudy.model.Blog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlogDAO extends DAOHelper implements BaseDAO<Blog> {
    private static final String INSERT_NEW_BLOG="INSERT INTO \"blog\" (\"title\",\"link\",\"image\") VALUES(?,?,?);";
    private static final String SELECT_BLOG_BY_ID="SELECT * FROM \"blog\" where \"id\"=?;";
    private static final String SELECT_ALL_BLOG="SELECT * FROM \"blog\" ;";
    private static final String DELETE_BLOG_BY_ID="DELETE FROM \"blog\" where \"id\"=? ;";
    private static final String UPDATE_BLOG="UPDATE \"blog\" SET \"title\" = ?,\"link\" = ?,\"image\" = ? WHERE \"id\" = ?;";
    private static final String SEARCH_BLOG="SELECT * FROM \"blog\" where \"title\" like concat('%',?,'%');";
    private static final String SELECT_SO_LUONG_BLOG="SELECT * FROM \"blog\" order by \"id\" limit ?;";



    @Override
    public void insert(Blog blog) throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_BLOG)) {
            preparedStatement.setString(1, blog.getTitle());
            preparedStatement.setString(2, blog.getLink());
            preparedStatement.setString(3, blog.getImage());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Blog selectOne(int id) {
        Blog blog = null;

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BLOG_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                String link=rs.getString("link");
                String image=rs.getString("image");
                blog=new Blog(id,title,link,image);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return blog;
    }

    @Override
    public List<Blog> selectAll() {
        List<Blog> blogList = new ArrayList<>();

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BLOG);) {
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id=rs.getInt("id");
                String title = rs.getString("title");
                String link=rs.getString("link");
                String image=rs.getString("image");
                blogList.add(new Blog(id,title,link,image));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return blogList;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_BLOG_BY_ID);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean update(Blog blog) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_BLOG);) {
            statement.setString(1, blog.getTitle());
            statement.setString(2, blog.getLink());
            statement.setString(3, blog.getImage());
            statement.setInt(4, blog.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public List<Blog> searchBlog(String name) throws SQLException {
        List<Blog> blogList=new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SEARCH_BLOG);) {
            statement.setString(1, name);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int idBlog = rs.getInt("id");
                String title = rs.getString("title");
                String link = rs.getString("link");
                String image = rs.getString("image");
                blogList.add(new Blog(idBlog,title,link,image));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return blogList;
    }
// ham trang chu
    public List<Blog> selectAmountBlog(int soLuong) {
        List<Blog> blogList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SO_LUONG_BLOG)) {
            preparedStatement.setInt(1, soLuong);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String link = rs.getString("link");
                String image=rs.getString("image");

                blogList.add(new Blog(id,title,link,image));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return blogList;
    }
}
