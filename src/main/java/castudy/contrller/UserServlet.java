package castudy.contrller;

import castudy.dao.*;
import castudy.model.*;
import castudy.service.*;

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

@WebServlet(name = "UserServlet",urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    UserService userService=new UserService();
    ProductService productService=new ProductService();
    BlogService blogService=new BlogService();
    ProductVendorsService productVendorsService =new ProductVendorsService();
    Validations validations=new Validations();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "customer":
                    loginCustomer(request,response);
                    break;
                case "loginAdmin":
                    loginAdmin(request,response);
                    break;
                default:
                    createAccount(request,response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "loginOut":
                    loginOutCustomer(request,response);
                    break;
                case "out":
                    loginOut(request,response);
                    break;
                default:
                    showFormLogin(request, response);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void createAccount(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

            String userName = request.getParameter("name");
            String password=request.getParameter("pass");
            String email=request.getParameter("email");
            if (validations.validateUser(userName,password,email)){
                User account=new User(userName,password,email,"customer");
                HttpSession session=request.getSession();
                userService.insert(account);
                request.setAttribute("account",account);
                session.setAttribute("user",account);
                request.setAttribute("message", "<script>\n" +
                        "        alert(\"Created\")\n" +
                        "    </script>");
            }else {
                request.setAttribute("message", "<script>\n" +
                        "        alert(\"No created.Not validate\")\n" +
                        "    </script>");
            }
            List<Product> product12ProductHome = productService.select12ProductHome();
            request.setAttribute("product12ProductHome", product12ProductHome);

            List<Product> productHot = productService.selectHotProduct();
            request.setAttribute("productHot",productHot);

            List<ProductVendor> productVendors= productVendorsService.selectAll();
            request.setAttribute("productVendors",productVendors);

            List<String> list=productService.selectAllType();
            request.setAttribute("tenTheLoai",list);

            List<Blog> blog3List=blogService.selectAmountBlog(3);
            request.setAttribute("blog3List",blog3List);

            RequestDispatcher dispatcher = request.getRequestDispatcher("trangchu/home.jsp");
            dispatcher.forward(request, response);
    }

    private void showFormLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        RequestDispatcher dispatcher=request.getRequestDispatcher("user/login.jsp");
        dispatcher.forward(request,response);
    }

    private void loginOut(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        HttpSession session = request.getSession();
        session.setAttribute("user",null);
        request.setAttribute("account",null);
        RequestDispatcher dispatcher=request.getRequestDispatcher("user/login.jsp");
        dispatcher.forward(request,response);
    }

    private void loginAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        HttpSession session=request.getSession();
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        User user=userService.checkLoginAdmin(userName,password);
        session.setAttribute("user",user);

        if(user==null){
            request.setAttribute("message", "<script>\n" +
                    "        alert(\"Login fail \")\n" +
                    "    </script>");
            RequestDispatcher dispatcher=request.getRequestDispatcher("user/login.jsp");
            dispatcher.forward(request,response);
        }else {
            response.sendRedirect("/products");
        }
    }
    private void loginCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        HttpSession session=request.getSession();
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        User userCustomer=userService.checkLoginCustomer(userName,password);
        session.setAttribute("user",userCustomer);

        List<Product> product12ProductHome = productService.select12ProductHome();
        request.setAttribute("product12ProductHome", product12ProductHome);

        List<Product> productHot = productService.selectHotProduct();
        request.setAttribute("productHot",productHot);

        List<ProductVendor> productVendors= productVendorsService.selectAll();
        request.setAttribute("productVendors",productVendors);

        List<String> list=productService.selectAllType();
        request.setAttribute("tenTheLoai",list);

        List<Blog> blog3List=blogService.selectAmountBlog(3);
        request.setAttribute("blog3List",blog3List);

        if(userCustomer==null){
            request.setAttribute("message", "<script>\n" +
                    "        alert(\"Login fail \")\n" +
                    "    </script>");
        }else {
            request.setAttribute("message", "<script>\n" +
                    "        alert(\"Logged in successfully  \")\n" +
                    "    </script>");
        }

        RequestDispatcher dispatcher=request.getRequestDispatcher("trangchu/home.jsp");
        dispatcher.forward(request,response);
    }

    private void loginOutCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{

        HttpSession session = request.getSession();
        session.setAttribute("user",null);
        request.setAttribute("user",null);

        List<Product> product12ProductHome = productService.select12ProductHome();
        request.setAttribute("product12ProductHome", product12ProductHome);

        List<Product> productHot = productService.selectHotProduct();
        request.setAttribute("productHot",productHot);

        List<ProductVendor> productVendors= productVendorsService.selectAll();
        request.setAttribute("productVendors",productVendors);

        List<String> list=productService.selectAllType();
        request.setAttribute("tenTheLoai",list);

        List<Blog> blog3List=blogService.selectAmountBlog(3);
        request.setAttribute("blog3List",blog3List);

        RequestDispatcher dispatcher=request.getRequestDispatcher("trangchu/home.jsp");
        dispatcher.forward(request,response);
    }
}
