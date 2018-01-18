package home.inna.cruisecompany.servlet;

import home.inna.cruisecompany.dao.UserDao;
import home.inna.cruisecompany.dao.jdbc.UserDaoImpl;
import home.inna.cruisecompany.data.Role;
import home.inna.cruisecompany.data.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/registration.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setRole(Role.USER);

        userDao.save(user);
        resp.sendRedirect("/login");
    }
}
