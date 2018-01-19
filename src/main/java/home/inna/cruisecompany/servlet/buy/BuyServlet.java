package home.inna.cruisecompany.servlet.buy;

import home.inna.cruisecompany.dao.CruiseTicketDao;
import home.inna.cruisecompany.dao.jdbc.CruiseTicketDaoImpl;
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

@WebServlet("/buy")
public class BuyServlet extends HttpServlet {

    private CruiseTicketDao cruiseTicketDao = new CruiseTicketDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUser(req);
        if (user != null) {
            Long ticketId = WebUtil.id(req, "ticketId");
            cruiseTicketDao.buy(user.getId(), ticketId);
        }

        resp.sendRedirect("/cruise/details?id=" + req.getParameter("cruiseId"));
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
