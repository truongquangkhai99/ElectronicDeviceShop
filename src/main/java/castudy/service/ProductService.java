package castudy.service;

import castudy.dao.ProductsDAO;
import castudy.model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements BaseService<Product>{
    private static ProductsDAO productsDAO=new ProductsDAO();

    @Override
    public void insert(Product product) throws SQLException {
        productsDAO.insert(product);
    }

    @Override
    public Product selectOne(int id) {
        return productsDAO.selectOne(id);
    }

    @Override
    public List<Product> selectAll() {
        return productsDAO.selectAll();
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return productsDAO.delete(id);
    }

    @Override
    public boolean update(Product product) throws SQLException {
        return productsDAO.update(product);
    }

    public ArrayList<Integer> selectAllProductIdType(int maTheLoai) {
        return productsDAO.selectAllProductIdType(maTheLoai);
    }

    public ArrayList<Integer> selectAllProductIdVendor( int maNhaSanxuat) {
        return productsDAO.selectAllProductIdVendor(maNhaSanxuat);
    }
    public List<Product> searchProduct(String name) throws SQLException {
        return productsDAO.searchProduct(name);
    }
//    Ham cho trang chu

    public List<Product> select12ProductHome() {
        return productsDAO.select12ProductHome();
    }

    public List<Product> selectHotProduct() {
        return productsDAO.selectHotProduct();
    }

    public List<Product> selectTenNewProduct() {
        return productsDAO.selectTenNewProduct();
    }

    public List<String> selectAllType() {
        return productsDAO.selectAllType();
    }

    public List<Integer> selectPriceMaxMin() {
        return productsDAO.selectPriceMaxMin();
    }

    public List<Product> selectListProductByType(String tenTheLoai) {
        return productsDAO.selectListProductByType(tenTheLoai);
    }

    public List<Product> searchProductName(String name) throws SQLException {
        return productsDAO.searchProductName(name);
    }

}
