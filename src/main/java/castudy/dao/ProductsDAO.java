package castudy.dao;

import castudy.model.Product;
import castudy.model.ProductLine;
import castudy.model.ProductVendor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsDAO extends DAOHelper implements BaseDAO<Product> {
    private static ProductLinesDAO productLinesDAO =new ProductLinesDAO();
    private static ProductVendorDAO productVendorDAO =new ProductVendorDAO();

    private static final String INSERT_PRODUCT_SQL ="INSERT INTO \"product\" (`\"tenSanPham\"`,\"giaSanPham\",\"image\",\"mauSac\",\"loaiSanPham\",\"nhaSanXuat\",\"soLuongMua\",\"ngaySanXuat\",\"hanSudung\",\"moTa\",\"maTheLoai\",\"maNhaSanXuat\") VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
    private static final String SELECT_PRODUCT_BY_ID ="select * FROM \"product\" WHERE \"maSanPham\" = ?;";
    private static final String SELECT_ALL_PRODUCT="SELECT * FROM \"product\";";
    private static final String DELETE_PRODUCT_SQL ="DELETE FROM \"product\" WHERE \"maSanPham\" = ?;";
    private static final String UPDATE_PRODUCT_SQL ="UPDATE \"product\" SET \"tenSanPham\" = ?,\"giaSanPham\" = ?,\"image\" =?,\"mauSac\"=?,\"loaiSanPham\" = ?,\"nhaSanXuat\" = ?,\"soLuongMua\" = ?,\"ngaySanXuat\" = ?,\"hanSudung\" = ?,\"moTa\" =?,\"maTheLoai\" = ?,\"maNhaSanXuat\" = ? WHERE \"maSanPham\" = ?;";
    private static final String SELECT_ALL_ID_PRODUCT_WHERE_TYPE = "Select \"maSanPham\" FROM \"product\" WHERE \"maTheLoai\"= ?;";
    private static final String SELECT_ALL_ID_PRODUCT_WHERE_VENDOR = "Select \"maSanPham\" FROM \"product\" WHERE \"maNhaSanXuat\"= ?;";
    private static final String SEARCH_PRODUCT= "SELECT * FROM \"product\" where \"tenSanPham\" like concat('%',?,'%');";

//  SQL dung cho trang chu:
    private static final String SELECT_12_PRODUCT_HOME ="SELECT * FROM \"product\" limit 12;";
    private static final String SELECT_THREE_PRODUCT_HOT ="SELECT * FROM \"product\" order by \"soLuongMua\" desc limit 3;";
    private static final String SELECT_10_NEW_PRODUCT ="SELECT * FROM \"product\" order by \"maSanPham\" desc limit 10;";
    private static final String SELECT_ALL_TYPE = "SELECT \"loaiSanPham\" FROM \"product\" group by \"loaiSanPham\";";
    private static final String SELECT_PRICE_MAX_MIN = "SELECT max(\"giaSanPham\") as \"priceMax\", min(\"giaSanPham\") as \"priceMin\" FROM \"product\";";
    private static final String SELECT_PRODUCT_BY_TYPE = "SELECT * FROM \"product\" where \"loaiSanPham\"=? limit 12;";
    private static final String SEARCH_PRODUCT_NAME= "SELECT * FROM \"product\" where \"tenSanPham\" like concat('%',?,'%') limit 12;";



    @Override
    public void insert(Product product) throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {

            preparedStatement.setString(1, product.getTenSanPham());
            preparedStatement.setFloat(2, product.getGiaSanPham());
            preparedStatement.setString(3, product.getImage());
            preparedStatement.setString(4, product.getMauSac());
            preparedStatement.setString(5, product.getLoaiSanPham().getTenTheLoai());
            preparedStatement.setString(6, product.getNhaSanXuat().getTenNhaSanXuat());
            preparedStatement.setInt(7, product.getSoLuong());
            preparedStatement.setString(8, product.getNgaySanXuat());
            preparedStatement.setString(9, product.getHanSudung());
            preparedStatement.setString(10, product.getMoTa());
            preparedStatement.setInt(11, product.getMaTheLoai());
            preparedStatement.setInt(12, product.getMaNhasanXuat());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Product selectOne(int id) {
        Product product = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
                preparedStatement.setInt(1, id);

                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    String tenSanPham = rs.getString("tenSanPham");
                    float giaSanPham  = rs.getFloat("giaSanPham");
                    String image=rs.getString("image");
                    String mauSac=rs.getString("mauSac");
                    int soLuong = rs.getInt("soLuong");
                    String ngaySanXuat=rs.getString("ngaySanXuat");
                    String hanSudung=rs.getString("hanSudung");
                    String moTa=rs.getString("moTa");
                    int soLuongMua=rs.getInt("soLuongMua");
                    int maTheLoai = rs.getInt("maTheLoai");
                    ProductLine productLine= productLinesDAO.selectOne(maTheLoai);
                    int maNhaSanXuat = rs.getInt("maNhaSanXuat");
                    ProductVendor productVendor= productVendorDAO.selectOne(maNhaSanXuat);

                    product =new Product(id, tenSanPham, giaSanPham,image,mauSac,productLine,productVendor,soLuong,ngaySanXuat,hanSudung,moTa,soLuongMua,maTheLoai,maNhaSanXuat);
                }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }

    @Override
    public List<Product> selectAll() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT)) {

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                int maSanPham = rs.getInt("maSanPham");
                String tenSanPham = rs.getString("tenSanPham");
                float giaSanPham  = rs.getFloat("giaSanPham");
                String image=rs.getString("image");
                String mauSac=rs.getString("mauSac");
                int soLuong = rs.getInt("soLuong");
                String ngaySanXuat=rs.getString("ngaySanXuat");
                String hanSudung=rs.getString("hanSudung");
                String moTa=rs.getString("moTa");
                int soLuongMua=rs.getInt("soLuongMua");
                int maTheLoai = rs.getInt("maTheLoai");
                ProductLine productLine= productLinesDAO.selectOne(maTheLoai);
                int maNhaSanXuat = rs.getInt("maNhaSanXuat");
                ProductVendor productVendor= productVendorDAO.selectOne(maNhaSanXuat);

                products.add(new Product(maSanPham, tenSanPham, giaSanPham,image,mauSac,productLine,productVendor,soLuong,ngaySanXuat,hanSudung,moTa,soLuongMua,maTheLoai,maNhaSanXuat));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }


    @Override
    public boolean update(Product product) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL);) {
            statement.setString(1, product.getTenSanPham());
            statement.setFloat(2, product.getGiaSanPham());
            statement.setString(3, product.getImage());
            statement.setString(4, product.getMauSac());
            statement.setString(5, product.getLoaiSanPham().getTenTheLoai());
            statement.setString(6, product.getNhaSanXuat().getTenNhaSanXuat());
            statement.setInt(7, product.getSoLuong());
            statement.setString(8, product.getNgaySanXuat());
            statement.setString(9, product.getHanSudung());
            statement.setString(10, product.getMoTa());
            statement.setInt(11, product.getMaTheLoai());
            statement.setInt(12, product.getMaNhasanXuat());
            statement.setInt(13, product.getMaSanPham());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public ArrayList<Integer> selectAllProductIdType( int maTheLoai) {
        ArrayList<Integer> list=new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ID_PRODUCT_WHERE_TYPE)) {
            preparedStatement.setInt(1, maTheLoai);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int maSanPham = rs.getInt("maSanPham");
                list.add(maSanPham);
                }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return list;
    }

    public ArrayList<Integer> selectAllProductIdVendor( int maNhaSanxuat) {
        ArrayList<Integer> list=new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ID_PRODUCT_WHERE_VENDOR)) {
            preparedStatement.setInt(1, maNhaSanxuat);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int maSanPham = rs.getInt("maSanPham");
                list.add(maSanPham);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return list;
    }

    public List<Product> searchProduct(String name) throws SQLException {
        List<Product> productList=new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SEARCH_PRODUCT);) {
            statement.setString(1, name);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int maSanPham = rs.getInt("maSanPham");
                String tenSanPham = rs.getString("tenSanPham");
                float giaSanPham  = rs.getFloat("giaSanPham");
                String image=rs.getString("image");
                String mauSac=rs.getString("mauSac");
                int soLuong = rs.getInt("soLuong");
                String ngaySanXuat=rs.getString("ngaySanXuat");
                String hanSudung=rs.getString("hanSudung");
                String moTa=rs.getString("moTa");
                int soLuongMua=rs.getInt("soLuongMua");
                int maTheLoai = rs.getInt("maTheLoai");
                ProductLine productLine= productLinesDAO.selectOne(maTheLoai);
                int maNhaSanXuat = rs.getInt("maNhaSanXuat");
                ProductVendor productVendor= productVendorDAO.selectOne(maNhaSanXuat);

                productList.add(new Product(maSanPham, tenSanPham, giaSanPham,image,mauSac,productLine,productVendor,soLuong,ngaySanXuat,hanSudung,moTa,soLuongMua,maTheLoai,maNhaSanXuat));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return productList;
    }
// Ham cho trang chu

    public List<Product> select12ProductHome() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_12_PRODUCT_HOME)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int maSanPham = rs.getInt("maSanPham");
                String tenSanPham = rs.getString("tenSanPham");
                float giaSanPham  = rs.getFloat("giaSanPham");
                String image=rs.getString("image");
                String mauSac=rs.getString("mauSac");
                int soLuong = rs.getInt("soLuong");
                String ngaySanXuat=rs.getString("ngaySanXuat");
                String hanSudung=rs.getString("hanSudung");
                String moTa=rs.getString("moTa");
                int soLuongMua=rs.getInt("soLuongMua");
                int maTheLoai = rs.getInt("maTheLoai");
                ProductLine productLine= productLinesDAO.selectOne(maTheLoai);
                int maNhaSanXuat = rs.getInt("maNhaSanXuat");
                ProductVendor productVendor= productVendorDAO.selectOne(maNhaSanXuat);

                products.add(new Product(maSanPham, tenSanPham, giaSanPham,image,mauSac,productLine,productVendor,soLuong,ngaySanXuat,hanSudung,moTa,soLuongMua,maTheLoai,maNhaSanXuat));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    public List<Product> selectHotProduct() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_THREE_PRODUCT_HOT)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int maSanPham = rs.getInt("maSanPham");
                String tenSanPham = rs.getString("tenSanPham");
                float giaSanPham  = rs.getFloat("giaSanPham");
                String image=rs.getString("image");
                String mauSac=rs.getString("mauSac");
                int soLuong = rs.getInt("soLuong");
                String ngaySanXuat=rs.getString("ngaySanXuat");
                String hanSudung=rs.getString("hanSudung");
                String moTa=rs.getString("moTa");
                int soLuongMua=rs.getInt("soLuongMua");
                int maTheLoai = rs.getInt("maTheLoai");
                ProductLine productLine= productLinesDAO.selectOne(maTheLoai);
                int maNhaSanXuat = rs.getInt("maNhaSanXuat");
                ProductVendor productVendor= productVendorDAO.selectOne(maNhaSanXuat);

                products.add(new Product(maSanPham, tenSanPham, giaSanPham,image,mauSac,productLine,productVendor,soLuong,ngaySanXuat,hanSudung,moTa,soLuongMua,maTheLoai,maNhaSanXuat));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    public List<Product> selectTenNewProduct() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_10_NEW_PRODUCT)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int maSanPham = rs.getInt("maSanPham");
                String tenSanPham = rs.getString("tenSanPham");
                float giaSanPham  = rs.getFloat("giaSanPham");
                String image=rs.getString("image");
                String mauSac=rs.getString("mauSac");
                int soLuong = rs.getInt("soLuong");
                String ngaySanXuat=rs.getString("ngaySanXuat");
                String hanSudung=rs.getString("hanSudung");
                String moTa=rs.getString("moTa");
                int soLuongMua=rs.getInt("soLuongMua");
                int maTheLoai = rs.getInt("maTheLoai");
                ProductLine productLine= productLinesDAO.selectOne(maTheLoai);
                int maNhaSanXuat = rs.getInt("maNhaSanXuat");
                ProductVendor productVendor= productVendorDAO.selectOne(maNhaSanXuat);

                products.add(new Product(maSanPham, tenSanPham, giaSanPham,image,mauSac,productLine,productVendor,soLuong,ngaySanXuat,hanSudung,moTa,soLuongMua,maTheLoai,maNhaSanXuat));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    public List<String> selectAllType() {
        List<String> list = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TYPE)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String tenTheLoai=rs.getString("loaiSanPham");
                list.add(tenTheLoai);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return list;
    }

    public List<Integer> selectPriceMaxMin() {
        List<Integer> list = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRICE_MAX_MIN)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Integer priceMax =rs.getInt("priceMax");
                Integer priceMin =rs.getInt("priceMin");
                list.add(priceMax);
                list.add(priceMin);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return list;
    }

    public List<Product> selectListProductByType(String tenTheLoai) {
        List<Product> products = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_TYPE)) {
            preparedStatement.setString(1, tenTheLoai);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int maSanPham = rs.getInt("maSanPham");
                String tenSanPham = rs.getString("tenSanPham");
                float giaSanPham  = rs.getFloat("giaSanPham");
                String image=rs.getString("image");
                String mauSac=rs.getString("mauSac");
                int soLuong = rs.getInt("soLuong");
                String ngaySanXuat=rs.getString("ngaySanXuat");
                String hanSudung=rs.getString("hanSudung");
                String moTa=rs.getString("moTa");
                int soLuongMua=rs.getInt("soLuongMua");
                int maTheLoai = rs.getInt("maTheLoai");
                ProductLine productLine= productLinesDAO.selectOne(maTheLoai);
                int maNhaSanXuat = rs.getInt("maNhaSanXuat");
                ProductVendor productVendor= productVendorDAO.selectOne(maNhaSanXuat);

                products.add(new Product(maSanPham, tenSanPham, giaSanPham,image,mauSac,productLine,productVendor,soLuong,ngaySanXuat,hanSudung,moTa,soLuongMua,maTheLoai,maNhaSanXuat));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    public List<Product> searchProductName(String name) throws SQLException {
        List<Product> productList=new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SEARCH_PRODUCT_NAME);) {
            statement.setString(1, name);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int maSanPham = rs.getInt("maSanPham");
                String tenSanPham = rs.getString("tenSanPham");
                float giaSanPham  = rs.getFloat("giaSanPham");
                String image=rs.getString("image");
                String mauSac=rs.getString("mauSac");
                int soLuong = rs.getInt("soLuong");
                String ngaySanXuat=rs.getString("ngaySanXuat");
                String hanSudung=rs.getString("hanSudung");
                String moTa=rs.getString("moTa");
                int soLuongMua=rs.getInt("soLuongMua");
                int maTheLoai = rs.getInt("maTheLoai");
                ProductLine productLine= productLinesDAO.selectOne(maTheLoai);
                int maNhaSanXuat = rs.getInt("maNhaSanXuat");
                ProductVendor productVendor= productVendorDAO.selectOne(maNhaSanXuat);

                productList.add(new Product(maSanPham, tenSanPham, giaSanPham,image,mauSac,productLine,productVendor,soLuong,ngaySanXuat,hanSudung,moTa,soLuongMua,maTheLoai,maNhaSanXuat));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return productList;
    }

}
