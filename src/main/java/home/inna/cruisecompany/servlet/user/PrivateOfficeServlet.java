package home.inna.cruisecompany.servlet.user;

import home.inna.cruisecompany.dao.CruiseDao;
import home.inna.cruisecompany.dao.ExcursionDao;
import home.inna.cruisecompany.dao.jdbc.CruiseDaoImpl;
import home.inna.cruisecompany.dao.jdbc.ExcursionDaoImpl;
import home.inna.cruisecompany.data.Cruise;
import home.inna.cruisecompany.data.Excursion;
import home.inna.cruisecompany.data.Role;
import home.inna.cruisecompany.data.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/privateOffice")
public class PrivateOfficeServlet extends HttpServlet {

    private ExcursionDao excursionDao = new ExcursionDaoImpl();
    private CruiseDao cruiseDao = new CruiseDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUser(req);
        if (user != null) {
            List<Excursion> excursions = excursionDao.excursionByUser(user.getId());
            List<Cruise> cruises = cruiseDao.cruiseByUser(user.getId());
            req.setAttribute("excursions", excursions);
            req.setAttribute("cruises", cruises);
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/user/user.jsp");
        requestDispatcher.forward(req, resp);
    }

    private User getUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user.getRole() == Role.USER) {
                return user;
            }
        }
        return null;
    }
}
