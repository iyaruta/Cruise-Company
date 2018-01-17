package home.inna.cruisecompany.servlet.ticketClass;

import home.inna.cruisecompany.dao.TicketClassDao;
import home.inna.cruisecompany.dao.jdbc.TicketClassDaoImpl;
import home.inna.cruisecompany.data.TicketClass;
import home.inna.cruisecompany.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ticketClass/delete")
public class TicketClassDeleteServlet extends HttpServlet {

    private TicketClassDao ticketClassDao = new TicketClassDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long util = WebUtil.id(req);
        TicketClass ticketClass = ticketClassDao.get(util);
        Long shipId = ticketClass.getShipId();
        ticketClassDao.delete(util);
        resp.sendRedirect("/ticketClass?shipId=" + shipId);
    }
}
