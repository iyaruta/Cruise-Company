package home.inna.cruisecompany.servlet.ticketClass;

import home.inna.cruisecompany.dao.TicketClassDao;
import home.inna.cruisecompany.dao.jdbc.TicketClassDaoImpl;
import home.inna.cruisecompany.data.TicketClass;
import home.inna.cruisecompany.util.WebUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ticketClass")
public class TicketClassServlet extends HttpServlet {

    private TicketClassDao ticketClassDao = new TicketClassDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long shipId = WebUtil.id(req, "shipId");
        List<TicketClass> ticketClasses = ticketClassDao.findByShip(shipId);
        req.setAttribute("shipId", req.getParameter("shipId"));
        req.setAttribute("ticketClasses", ticketClasses);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/ticketClass/ticketClass.jsp");
        requestDispatcher.forward(req, resp);
    }
}
