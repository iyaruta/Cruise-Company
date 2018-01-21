package home.inna.cruisecompany.servlet.buy;

import home.inna.cruisecompany.data.User;
import home.inna.cruisecompany.service.CruiseTicketService;
import home.inna.cruisecompany.service.impl.CruiseTicketServiceImpl;
import home.inna.cruisecompany.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/buy")
public class BuyServlet extends HttpServlet {

    private CruiseTicketService cruiseTicketService = new CruiseTicketServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = WebUtil.getUser(req);
        if (user != null) {
            Long ticketId = WebUtil.id(req, "ticketId");
            cruiseTicketService.buy(user.getId(), ticketId);
        }
        resp.sendRedirect("/cruise/details?id=" + req.getParameter("cruiseId"));
    }

}
