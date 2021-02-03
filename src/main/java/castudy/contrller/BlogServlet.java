package castudy.contrller;

import castudy.dao.BlogDAO;
import castudy.dao.ProductLinesDAO;
import castudy.dao.ProductVendorDAO;
import castudy.dao.ProductsDAO;
import castudy.model.Blog;
import castudy.model.User;
import castudy.service.BlogService;
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

@WebServlet(name = "BlogServlet",urlPatterns = "/Blog")
public class BlogServlet extends HttpServlet {
    private BlogService blogService =new BlogService();
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
                        insertBlog(request, response);
                        break;
                    case "edit":
                        updateBlog(request, response);
                        break;
                    case "search":
                        searchBlog(request,response);
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
                        deleteBlog(request, response);
                        break;
                    default:
                        listBlog(request, response);
                        break;
                }
            } catch (SQLException ex) {
            throw new ServletException(ex);
            }
        }else {
            response.sendRedirect("user/login.jsp");
        }
    }

    private void listBlog(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {


        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            List<Blog> blogList = blogService.selectAll();
            request.setAttribute("blogList", blogList);
            request.setAttribute("account",account);

            RequestDispatcher dispatcher = request.getRequestDispatcher("blog/list.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            request.setAttribute("account",account);

            RequestDispatcher dispatcher = request.getRequestDispatcher("blog/create.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("/user");
        }

    }


    private void insertBlog(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            String title = request.getParameter("title");
            String link = request.getParameter("link");
            String image = request.getParameter("image");
            if (validations.validateBlog(title,link,image)){
                Blog blog=new Blog(title,link,image);
                blogService.insert(blog);

                request.setAttribute("message", "<script>\n" +
                        "        alert(\"Blog was created\")\n" +
                        "    </script>");
            }else {
                request.setAttribute("message", "<script>\n" +
                        "        alert(\"Blog was no created.Not validate\")\n" +
                        "    </script>");
            }
            request.setAttribute("account",account);

            RequestDispatcher dispatcher = request.getRequestDispatcher("blog/create.jsp");
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
            Blog blog = blogService.selectOne(id);
            request.setAttribute("blog", blog);
            request.setAttribute("account",account);

            RequestDispatcher dispatcher = request.getRequestDispatcher("blog/edit.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("/user");
        }


    }

    private void deleteBlog(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            int id =Integer.parseInt(request.getParameter("id")) ;
            blogService.delete(id);
            List<Blog> blogList = blogService.selectAll();
            request.setAttribute("blogList", blogList);
            request.setAttribute("account",account);

            request.setAttribute("message", "<script>\n" +
                    "        alert(\"Blog deleted\")\n" +
                    "    </script>");

            RequestDispatcher dispatcher = request.getRequestDispatcher("blog/list.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("/user");
        }

    }

    private void updateBlog(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String link=request.getParameter("link");
            String image=request.getParameter("image");
            if (validations.validateBlog(title,link,image)){
                Blog blog=new Blog(id,title,link,image);
                blogService.update(blog);

                request.setAttribute("blog",blog);

                request.setAttribute("message", "<script>\n" +
                        "        alert(\"Blog was updated\")\n" +
                        "    </script>");
            }else {
                request.setAttribute("blog", blogService.selectOne(id));
                request.setAttribute("message", "<script>\n" +
                        "        alert(\"Blog was no updated.Not validate\")\n" +
                        "    </script>");
            }
            request.setAttribute("account",account);

            RequestDispatcher dispatcher = request.getRequestDispatcher("blog/edit.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("/user");
        }
    }

    private void searchBlog(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if(account.getChucVu().equals("admin")){
            String title = request.getParameter("search");
            List<Blog> blogList= blogService.searchBlog(title);
            request.setAttribute("blogList",blogList);
            request.setAttribute("account",account);

            RequestDispatcher dispatcher = request.getRequestDispatcher("blog/list.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("/user");
        }

    }
}
