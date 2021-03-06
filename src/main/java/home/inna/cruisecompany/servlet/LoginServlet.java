package home.inna.cruisecompany.servlet;

import home.inna.cruisecompany.data.User;
import home.inna.cruisecompany.service.UserService;
import home.inna.cruisecompany.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        User user = userService.getByName(name);
        if (user == null || !Objects.equals(user.getPassword(), password)) {
            req.setAttribute("error_login", true);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("/");
        }
    }
}
