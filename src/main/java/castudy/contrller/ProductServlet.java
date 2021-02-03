package castudy.contrller;

import castudy.dao.ProductLinesDAO;
import castudy.dao.ProductVendorDAO;
import castudy.dao.ProductsDAO;
import castudy.model.Product;
import castudy.model.ProductLine;
import castudy.model.ProductVendor;
import castudy.model.User;
import castudy.service.ProductLinesService;
import castudy.service.ProductService;
import castudy.service.ProductVendorsService;
import castudy.service.Validations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet",urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private ProductService productService =new ProductService();
    private ProductLinesService productLinesService =new ProductLinesService();
    private ProductVendorsService productVendorsService =new ProductVendorsService();
    private Validations validations=new Validations();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if (account != null) {
            String action = request.getParameter("action");
            if (action == null) {
                action = "";
            }
            try {
                switch (action) {
                    case "create":
                        insertProduct(request, response);
                        break;
                    case "edit":
                        updateProduct(request, response);
                        break;
                    case "search":
                        search(request,response);
                        break;
                    default:
                        listProduct(request, response);
                        break;
                }
            } catch (SQLException ex) {
            throw new ServletException(ex);
            }
        }else {
            response.sendRedirect("user/login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if (account != null) {
            String action = request.getParameter("action");
            if (action == null) {
                action = "";
            }
            try {
                switch (action) {
                    case "create":
                        showNewForm(request, response);
                        break;
                    case "edit":
                        showEditForm(request, response);
                        break;
                    case "delete":
                        deleteProduct(request, response);
                        break;
                    case "view":
                        viewProduct(request,response);
                        break;
                    default:
                        listProduct(request, response);
                        break;
                }
            } catch (SQLException ex) {
            throw new ServletException(ex);
            }
        }else {
            response.sendRedirect("user/login.jsp");
        }
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            request.setAttribute("account",account);
            List<Product> productList = productService.selectAll();
            request.setAttribute("productList", productList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("/user");
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            List<ProductLine> productLines= productLinesService.selectAll();
            List<ProductVendor> productVendors= productVendorsService.selectAll();
            request.setAttribute("productLines",productLines);
            request.setAttribute("productVendors",productVendors);
            request.setAttribute("account",account);

            RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("/user");
        }

    }


    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            String tenSanPham = request.getParameter("name");

            String gia =request.getParameter("price");

            String image=request.getParameter("image");
            String mauSac=request.getParameter("mauSac");

            String sl = request.getParameter("amount");

            String ngaySanXuat=request.getParameter("begin");
            String hanSudung=request.getParameter("end");
            String moTa=request.getParameter("mota");

            int idType=Integer.parseInt(request.getParameter("type"));
            ProductLine productLine= productLinesService.selectOne(idType);

            int idVendor=Integer.parseInt(request.getParameter("vendor"));
            ProductVendor productVendor= productVendorsService.selectOne(idVendor);

            if (validations.validateProduct(tenSanPham,gia,image,mauSac,sl,ngaySanXuat,hanSudung,moTa)){
                float giaSanPham =Float.parseFloat(gia);
                int amount = Integer.parseInt(sl);

                Product newProduct = new Product(tenSanPham,giaSanPham,image,mauSac,productLine,productVendor,amount,ngaySanXuat,hanSudung,moTa,idType,idVendor);
                productService.insert(newProduct);

                request.setAttribute("message", "<script>\n" +
                        "        alert(\"Product was created\")\n" +
                        "    </script>");
            }else {
                request.setAttribute("message", "<script>\n" +
                        "        alert(\"Product was no created. Not validate\")\n" +
                        "    </script>");
            }
            List<ProductLine> productLines= productLinesService.selectAll();
            request.setAttribute("productLines",productLines);

            List<ProductVendor> productVendors= productVendorsService.selectAll();
            request.setAttribute("productVendors",productVendors);

            request.setAttribute("account",account);

            RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("/user");
        }

    }


    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            int id = Integer.parseInt(request.getParameter("id"));
            Product existingProduct = productService.selectOne(id);
            List<ProductLine> productLines= productLinesService.selectAll();
            List<ProductVendor> productVendors= productVendorsService.selectAll();
            request.setAttribute("productLines",productLines);
            request.setAttribute("productVendors",productVendors);
            request.setAttribute("product", existingProduct);

            request.setAttribute("account",account);

            RequestDispatcher dispatcher = request.getRequestDispatcher("product/edit.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("/user");
        }

    }



    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            int maSanPham =Integer.parseInt(request.getParameter("id")) ;
            productService.delete(maSanPham);
            List<Product> productLists = productService.selectAll();
            request.setAttribute("productList", productLists);
            request.setAttribute("message", "<script>\n" +
                    "        alert(\"Product deleted\")\n" +
                    "    </script>");
            request.setAttribute("account",account);

            RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("/user");
        }

    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            int id = Integer.parseInt(request.getParameter("id"));
            String tenSanPham = request.getParameter("name");

            String gia =request.getParameter("price");

            String image=request.getParameter("image");
            String mauSac=request.getParameter("mauSac");

            String sl = request.getParameter("amount");

            String ngaySanXuat=request.getParameter("begin");
            String hanSudung=request.getParameter("end");
            String moTa=request.getParameter("mota");

            int idType=Integer.parseInt(request.getParameter("type"));
            ProductLine productLine= productLinesService.selectOne(idType);

            int idVendor=Integer.parseInt(request.getParameter("vendor"));
            ProductVendor productVendor= productVendorsService.selectOne(idVendor);

            if (validations.validateProduct(tenSanPham,gia,image,mauSac,sl,ngaySanXuat,hanSudung,moTa)){
                float giaSanPham =Float.parseFloat(gia);
                int amount = Integer.parseInt(sl);

                Product newProduct = new Product(id,tenSanPham,giaSanPham,image,mauSac,productLine,productVendor,amount,ngaySanXuat,hanSudung,moTa,idType,idVendor);
                productService.update(newProduct);

                request.setAttribute("product",newProduct);

                request.setAttribute("message", "<script>\n" +
                        "        alert(\"Product was updated\")\n" +
                        "    </script>");
            }else {
                request.setAttribute("product", productService.selectOne(id));

                request.setAttribute("message", "<script>\n" +
                        "        alert(\"Product was no updated.Not validate\")\n" +
                        "    </script>");
            }

            List<ProductLine> productLines= productLinesService.selectAll();
            request.setAttribute("productLines",productLines);

            List<ProductVendor> productVendors= productVendorsService.selectAll();
            request.setAttribute("productVendors",productVendors);

            request.setAttribute("account",account);

            RequestDispatcher dispatcher = request.getRequestDispatcher("product/edit.jsp");
            dispatcher.forward(request, response);

        }else {
            response.sendRedirect("/user");
        }

    }

    private void search(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            String name=request.getParameter("search");
            List<Product> productList= productService.searchProduct(name);
            request.setAttribute("productList",productList);
            request.setAttribute("account",account);
            RequestDispatcher dispatcher=request.getRequestDispatcher("product/list.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("/user");
        }
    }

    private void viewProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            int id = Integer.parseInt(request.getParameter("id"));
            Product existingProduct = productService.selectOne(id);
            request.setAttribute("product", existingProduct);
            request.setAttribute("account",account);

            RequestDispatcher dispatcher = request.getRequestDispatcher("product/view.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("/user");
        }

    }

}
