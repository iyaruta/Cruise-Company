package home.inna.cruisecompany.servlet.ticketClass;

import home.inna.cruisecompany.data.TicketClass;
import home.inna.cruisecompany.service.TicketClassService;
import home.inna.cruisecompany.service.impl.TicketClassServiceImpl;
import home.inna.cruisecompany.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/ticketClass/delete")
public class TicketClassDeleteServlet extends HttpServlet {

    private TicketClassService ticketClassService = new TicketClassServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long util = WebUtil.id(req);
        TicketClass ticketClass = ticketClassService.get(util);
        Long shipId = ticketClass.getShipId();
        ticketClassService.delete(util);
        resp.sendRedirect("/admin/ticketClass?shipId=" + shipId);
    }
}
