package castudy.service;

import castudy.dao.ProductVendorDAO;
import castudy.model.ProductVendor;

import java.sql.SQLException;
import java.util.List;

public class ProductVendorsService implements BaseService<ProductVendor> {
    private static ProductVendorDAO productVendorDAO=new ProductVendorDAO();

    @Override
    public void insert(ProductVendor productVendor) throws SQLException {
        productVendorDAO.insert(productVendor);
    }

    @Override
    public ProductVendor selectOne(int id) {
        return productVendorDAO.selectOne(id);
    }

    @Override
    public List<ProductVendor> selectAll() {
        return productVendorDAO.selectAll();
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return productVendorDAO.delete(id);
    }

    @Override
    public boolean update(ProductVendor productVendor) throws SQLException {
        return productVendorDAO.update(productVendor);
    }

    public List<ProductVendor> searchProductVendor(String name) throws SQLException {
        return productVendorDAO.searchProductVendor(name);
    }
}
