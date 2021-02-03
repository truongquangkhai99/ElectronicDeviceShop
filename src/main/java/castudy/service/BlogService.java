package castudy.service;

import castudy.dao.BlogDAO;
import castudy.model.Blog;

import java.sql.SQLException;
import java.util.List;

public class BlogService implements BaseService<Blog> {
    private static BlogDAO blogDAO=new BlogDAO();

    @Override
    public void insert(Blog blog) throws SQLException {
        blogDAO.insert(blog);
    }

    @Override
    public Blog selectOne(int id) {
        return blogDAO.selectOne(id);
    }

    @Override
    public List<Blog> selectAll() {
        return blogDAO.selectAll();
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return blogDAO.delete(id);
    }

    @Override
    public boolean update(Blog blog) throws SQLException {
        return blogDAO.update(blog);
    }

    public List<Blog> searchBlog(String name) throws SQLException {
        return blogDAO.searchBlog(name);
    }
//    Ham cho trang chu
    public List<Blog> selectAmountBlog(int soLuong) {
        return blogDAO.selectAmountBlog(soLuong);
    }
}
