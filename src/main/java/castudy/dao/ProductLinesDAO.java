package castudy.dao;

import castudy.model.ProductLine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductLinesDAO extends DAOHelper implements BaseDAO<ProductLine> {

        private static final String INSERT_PRODUCTlINE_SQL= "INSERT INTO \"productline\" (\"tenTheLoai\",\"moTa\") VALUES (?,?);";
        private static final String SELECT_PRODUCTlINE_BY_ID ="SELECT * FROM \"productline\" where \"maTheLoai\"=?;";
        private static final String SELECT_ALL_PRODUCTlINES ="SELECT * FROM \"productline\";";
        private static final String DELETE_PRODUCTlINE_SQL ="Delete FROM \"productline\" where \"maTheLoai\"=?;";
        private static final String UPDATE_PRODUCTlINE_SQL ="UPDATE \"productline\" SET \"tenTheLoai\" = ? ,\"moTa\"= ? WHERE \"maTheLoai\" = ?;";
        private static final String SEARCH_PRODUCT_LINE="SELECT * FROM \"productline\" where \"tenTheLoai\" like concat('%',?,'%');";


        @Override
        public void insert(ProductLine productLine) throws SQLException {
            try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTlINE_SQL)) {
                preparedStatement.setString(1, productLine.getTenTheLoai());
                preparedStatement.setString(2, productLine.getMoTa());
                System.out.println(preparedStatement);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                printSQLException(e);
            }
        }

        @Override
        public ProductLine selectOne(int id) {
            ProductLine productLine = null;

            try (Connection connection = getConnection();

                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTlINE_BY_ID);) {
                preparedStatement.setInt(1, id);
                System.out.println(preparedStatement);

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    String productLineName = rs.getString("tenTheLoai");
                    String moTa=rs.getString("moTa");
                    productLine=new ProductLine(id,productLineName,moTa);
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return productLine;
        }

        @Override
        public List<ProductLine> selectAll() {
            List<ProductLine> productLines = new ArrayList<>();

            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTlINES);) {
                System.out.println(preparedStatement);

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("maTheLoai");
                    String productLineName = rs.getString("tenTheLoai");
                    String moTa=rs.getString("moTa");
                    productLines.add(new ProductLine(id,productLineName,moTa));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return productLines;
        }

        @Override
        public boolean delete(int id) throws SQLException {
            boolean rowDeleted;
            try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCTlINE_SQL);) {
                statement.setInt(1, id);
                rowDeleted = statement.executeUpdate() > 0;
            }
            return rowDeleted;
        }

        @Override
        public boolean update(ProductLine productLine) throws SQLException {
            boolean rowUpdated;
            try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCTlINE_SQL);) {
                statement.setString(1, productLine.getTenTheLoai());
                statement.setString(2, productLine.getMoTa());
                statement.setInt(3, productLine.getMaTheLoai());
                rowUpdated = statement.executeUpdate() > 0;
            }
            return rowUpdated;
        }


    public List<ProductLine> searchProductLine(String name) throws SQLException {
        List<ProductLine> productLines=new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SEARCH_PRODUCT_LINE);) {
            statement.setString(1, name);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int maTheLoai = rs.getInt("maTheLoai");
                String tenTheLoai = rs.getString("tenTheLoai");
                String moTa=rs.getString("moTa");
                productLines.add(new ProductLine(maTheLoai,tenTheLoai,moTa));

                }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return productLines;
    }
}
