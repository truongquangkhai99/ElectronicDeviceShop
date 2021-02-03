package castudy.contrller;

import castudy.dao.ProductLinesDAO;
import castudy.dao.ProductVendorDAO;
import castudy.dao.ProductsDAO;
import castudy.model.ProductLine;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductLineServlet",urlPatterns = "/productLines")
public class ProductLineServlet extends HttpServlet {
    private ProductService productService =new ProductService();
    private ProductLinesService productLinesService =new ProductLinesService();
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
                        insertProductLine(request, response);
                        break;
                    case "edit":
                        updateProductLine(request, response);
                        break;
                    case "search":
                        searchProductLine(request,response);
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
                        deleteProductLine(request, response);
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
            List<ProductLine> productLines = productLinesService.selectAll();
            request.setAttribute("productLines", productLines);
            request.setAttribute("account",account);

            RequestDispatcher dispatcher = request.getRequestDispatcher("productLine/list.jsp");
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

            RequestDispatcher dispatcher = request.getRequestDispatcher("productLine/create.jsp");
            dispatcher.forward(request, response);
        }
    }


    private void insertProductLine(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            String tenTheLoai = request.getParameter("name");
            String moTa = request.getParameter("moTa");
            if (validations.validateProductLine(tenTheLoai,moTa)){
                ProductLine productLine=new ProductLine(tenTheLoai,moTa);
                productLinesService.insert(productLine);

                request.setAttribute("message", "<script>\n" +
                        "        alert(\"ProductLine was created\")\n" +
                        "    </script>");

            }else {
                request.setAttribute("message", "<script>\n" +
                        "        alert(\"ProductLine was no created. Not validate\")\n" +
                        "    </script>");
            }
            request.setAttribute("account",account);

            RequestDispatcher dispatcher = request.getRequestDispatcher("productLine/create.jsp");
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
            ProductLine productLine = productLinesService.selectOne(id);
            request.setAttribute("productLine", productLine);
            request.setAttribute("account",account);

            RequestDispatcher dispatcher = request.getRequestDispatcher("productLine/edit.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("/user");
        }
    }



    private void deleteProductLine(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {


        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            int maTheLoai =Integer.parseInt(request.getParameter("id")) ;
            ArrayList<Integer> list= productService.selectAllProductIdType(maTheLoai);
            for (Integer id:list){
                productService.delete(id);
            }
            productLinesService.delete(maTheLoai);
            List<ProductLine> productLines = productLinesService.selectAll();
            request.setAttribute("productLines", productLines);
            request.setAttribute("account",account);

            request.setAttribute("message", "<script>\n" +
                    "        alert(\"ProductLine deleted\")\n" +
                    "    </script>");

            RequestDispatcher dispatcher = request.getRequestDispatcher("productLine/list.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("/user");
        }

    }

    private void updateProductLine(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            int id = Integer.parseInt(request.getParameter("id"));
            String tenTheLoai = request.getParameter("name");
            String moTa=request.getParameter("moTa");
            if (validations.validateProductLine(tenTheLoai,moTa)){
                ProductLine productLine=new ProductLine(id,tenTheLoai,moTa);
                productLinesService.update(productLine);

                request.setAttribute("productLine",productLine);

                request.setAttribute("message", "<script>\n" +
                        "        alert(\"ProductLine was updated\")\n" +
                        "    </script>");
            }else {
                request.setAttribute("productLine", productLinesService.selectOne(id));
                request.setAttribute("message", "<script>\n" +
                        "        alert(\"ProductLine was no updated. Not validate\")\n" +
                        "    </script>");
            }
            request.setAttribute("account",account);

            RequestDispatcher dispatcher = request.getRequestDispatcher("productLine/edit.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("/user");
        }

    }

    private void searchProductLine(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            String tenTheLoai = request.getParameter("search");
            List<ProductLine> productLines= productLinesService.searchProductLine(tenTheLoai);
            request.setAttribute("productLines",productLines);
            request.setAttribute("account",account);

            RequestDispatcher dispatcher = request.getRequestDispatcher("productLine/list.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("/user");
        }

    }
}
