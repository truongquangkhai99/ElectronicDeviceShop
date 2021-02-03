package castudy.contrller;

import castudy.dao.ProductLinesDAO;
import castudy.dao.ProductVendorDAO;
import castudy.dao.ProductsDAO;
import castudy.model.ProductVendor;
import castudy.model.User;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductVendorServlet2", urlPatterns = "/productVendors")
public class ProductVendorServlet extends HttpServlet {
    private ProductService productService =new ProductService();
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
                        insertProductVendor(request, response);
                        break;
                    case "edit":
                        updateProductVendor(request, response);
                        break;
                    case "search":
                        searchProductVendor(request,response);
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
                        deleteProductVendor(request, response);
                        break;
                    default:
                        listProductLines(request, response);
                        break;
                }
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
        }else {
            response.sendRedirect("user/login.jsp");
        }
    }

    private void listProductLines(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            List<ProductVendor> productVendors = productVendorsService.selectAll();
            request.setAttribute("productVendors", productVendors);
            request.setAttribute("account",account);

            RequestDispatcher dispatcher = request.getRequestDispatcher("productVendor/list.jsp");
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
            request.setAttribute("account",account);

            RequestDispatcher dispatcher = request.getRequestDispatcher("productVendor/create.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("/user");
        }

    }


    private void insertProductVendor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            String tenNhaSanXuat = request.getParameter("name");
            String diaChi = request.getParameter("address");
            String email = request.getParameter("email");
            String sđt = request.getParameter("phone");
            String image = request.getParameter("image");
            if (validations.validateProductVendor(tenNhaSanXuat,diaChi,email,sđt,image)){
                ProductVendor productVendor =new ProductVendor(tenNhaSanXuat,diaChi,email,sđt,image);
                productVendorsService.insert(productVendor);
                request.setAttribute("message", "<script>\n" +
                        "        alert(\"ProductVendor was created\")\n" +
                        "    </script>");
            }else {
                request.setAttribute("message", "<script>\n" +
                        "        alert(\"ProductVendor was no created. Not validate\")\n" +
                        "    </script>");
            }
            request.setAttribute("account",account);

            RequestDispatcher dispatcher = request.getRequestDispatcher("productVendor/create.jsp");
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
            ProductVendor productVendor = productVendorsService.selectOne(id);
            request.setAttribute("account",account);
            request.setAttribute("productVendor", productVendor);
            RequestDispatcher dispatcher = request.getRequestDispatcher("productVendor/edit.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("/user");
        }
    }



    private void deleteProductVendor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            int maNhaSanxuat =Integer.parseInt(request.getParameter("id")) ;
            ArrayList<Integer> list= productService.selectAllProductIdVendor(maNhaSanxuat);
            for (Integer id:list){
                productService.delete(id);
            }
            productVendorsService.delete(maNhaSanxuat);
            List<ProductVendor> productVendors = productVendorsService.selectAll();
            request.setAttribute("account",account);

            request.setAttribute("message", "<script>\n" +
                    "        alert(\"ProductVendor deleted\")\n" +
                    "    </script>");
            request.setAttribute("productVendors", productVendors);

            RequestDispatcher dispatcher = request.getRequestDispatcher("productVendor/list.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("/user");
        }

    }

    private void updateProductVendor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            int id = Integer.parseInt(request.getParameter("id"));
            String tenNhaSanXuat = request.getParameter("name");
            String diaChi = request.getParameter("address");
            String email = request.getParameter("email");
            String sđt = request.getParameter("phone");
            String image = request.getParameter("image");
            if (validations.validateProductVendor(tenNhaSanXuat,diaChi,email,sđt,image)){
                ProductVendor productVendor=new ProductVendor(id,tenNhaSanXuat,diaChi,email,sđt,image);
                productVendorsService.update(productVendor);
                request.setAttribute("productVendor",productVendor);
                request.setAttribute("message", "<script>\n" +
                        "        alert(\"ProductVendor was updated\")\n" +
                        "    </script>");
            }else {
                request.setAttribute("productVendor", productVendorsService.selectOne(id));
                request.setAttribute("message", "<script>\n" +
                        "        alert(\"ProductVendor was no updated. Not validate\")\n" +
                        "    </script>");
            }
            request.setAttribute("account",account);

            RequestDispatcher dispatcher = request.getRequestDispatcher("productVendor/edit.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("/user");
        }

    }

    private void searchProductVendor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            String tenNhaSanXuat = request.getParameter("search");
            List<ProductVendor> productVendors= productVendorsService.searchProductVendor(tenNhaSanXuat);
            request.setAttribute("productVendors",productVendors);
            request.setAttribute("account",account);

            RequestDispatcher dispatcher = request.getRequestDispatcher("productVendor/list.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("/user");
        }

    }
}
