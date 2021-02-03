package castudy.service;

import castudy.dao.ProductLinesDAO;
import castudy.model.ProductLine;

import java.sql.SQLException;
import java.util.List;

public class ProductLinesService implements BaseService<ProductLine>{
    private static ProductLinesDAO productLinesDAO=new ProductLinesDAO();


    @Override
    public void insert(ProductLine productLine) throws SQLException {
        productLinesDAO.insert(productLine);
    }

    @Override
    public ProductLine selectOne(int id) {
        return productLinesDAO.selectOne(id);
    }

    @Override
    public List<ProductLine> selectAll() {
        return productLinesDAO.selectAll();
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return productLinesDAO.delete(id);
    }

    @Override
    public boolean update(ProductLine productLine) throws SQLException {
        return productLinesDAO.update(productLine);
    }

    public List<ProductLine> searchProductLine(String name) throws SQLException {
        return productLinesDAO.searchProductLine(name);
    }
}
