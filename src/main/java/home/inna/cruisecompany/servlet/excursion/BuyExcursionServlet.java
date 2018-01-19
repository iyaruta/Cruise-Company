package home.inna.cruisecompany.servlet.excursion;

import home.inna.cruisecompany.dao.ExcursionToUserDao;
import home.inna.cruisecompany.dao.jdbc.ExcursionToUserDaoImpl;
import home.inna.cruisecompany.data.Role;
import home.inna.cruisecompany.data.User;
import home.inna.cruisecompany.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/buyExcursion")
public class BuyExcursionServlet extends HttpServlet {

    private ExcursionToUserDao excursionToUserDao = new ExcursionToUserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUser(req);
        if (user != null) {
            Long excursionId = WebUtil.id(req, "excursionId");
            excursionToUserDao.buy(excursionId, user.getId());
        }

        resp.sendRedirect("excursion?portId=" + req.getParameter("portId"));
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
