package castudy.dao;

import castudy.model.ProductVendor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductVendorDAO extends DAOHelper implements BaseDAO<ProductVendor> {
    private static final String INSERT_PRODUCTVENDOR_SQL = "INSERT INTO \"productvendor\" ( \"tenNhaSanXuat\",\"diaChi\",\"email\",\"sđt\",\"image\") VALUES (?,?,?,?,?);";
    private static final String SELECT_PRODUCTVENDOR_BY_ID ="SELECT * FROM \"productvendor\" where \"maNhaSanXuat\"=?;";
    private static final String SELECT_ALL_PRODUCTVENDOR ="SELECT * FROM \"productvendor\";";
    private static final String DELETE_PRODUCTVENDOR_SQL ="DELETE FROM \"productvendor\" WHERE \"maNhaSanXuat\"=?;";
    private static final String UPDATE_PRODUCTVENDOR_SQL ="UPDATE \"productvendor\" SET \"tenNhaSanXuat\" = ? ,\"diaChi\"=? ,\"email\"=? ,\"sđt\"=?,\"image\"=? WHERE \"maNhaSanXuat\"= ?;";
    private static final String SEARCH_PRODUCT_VENDOR="SELECT * FROM \"productvendor\" where \"tenNhaSanXuat\" like concat('%',?,'%');";



    @Override
    public void insert(ProductVendor productVendor) throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTVENDOR_SQL)) {

            preparedStatement.setString(1, productVendor.getTenNhaSanXuat());
            preparedStatement.setString(2, productVendor.getDiaChi());
            preparedStatement.setString(3, productVendor.getEmail());
            preparedStatement.setString(4, productVendor.getSđt());
            preparedStatement.setString(5, productVendor.getImage());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public ProductVendor selectOne(int id) {
        ProductVendor productVendor = null;

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTVENDOR_BY_ID);) {
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String vendorName = rs.getString("tenNhaSanXuat");
                String diaChi = rs.getString("diaChi");
                String email = rs.getString("email");
                String sđt = rs.getString("sđt");
                String image = rs.getString("image");
                productVendor=new ProductVendor(id,vendorName,diaChi,email,sđt,image);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return productVendor;
    }

    @Override
    public List<ProductVendor> selectAll() {
        List<ProductVendor> productVendors = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTVENDOR);) {
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("maNhaSanXuat");
                String vendorName = rs.getString("tenNhaSanXuat");
                String diaChi = rs.getString("diaChi");
                String email = rs.getString("email");
                String sđt = rs.getString("sđt");
                String image = rs.getString("image");
                productVendors.add(new ProductVendor(id,vendorName,diaChi,email,sđt,image));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return productVendors;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCTVENDOR_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean update(ProductVendor productVendor) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCTVENDOR_SQL);) {
            statement.setString(1, productVendor.getTenNhaSanXuat());
            statement.setString(2, productVendor.getDiaChi());
            statement.setString(3, productVendor.getEmail());
            statement.setString(4, productVendor.getSđt());
            statement.setString(5, productVendor.getImage());
            statement.setInt(6, productVendor.getMaNhaSanXuat());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public List<ProductVendor> searchProductVendor(String name) throws SQLException {
        List<ProductVendor> productVendors=new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SEARCH_PRODUCT_VENDOR);) {
            statement.setString(1, name);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int maNhaSanXuat = rs.getInt("maNhaSanXuat");
                String tenNhaSanXuat = rs.getString("tenNhaSanXuat");
                String diaChi = rs.getString("diaChi");
                String email = rs.getString("email");
                String sđt = rs.getString("sđt");
                String image = rs.getString("image");

                productVendors.add(new ProductVendor(maNhaSanXuat,tenNhaSanXuat,diaChi,email,sđt,image));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return productVendors;
    }
}
