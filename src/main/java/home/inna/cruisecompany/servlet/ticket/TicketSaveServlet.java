package home.inna.cruisecompany.servlet.ticket;

import home.inna.cruisecompany.dao.TicketDao;
import home.inna.cruisecompany.dao.jdbc.TicketDaoImpl;
import home.inna.cruisecompany.data.Ticket;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/admin/ticket/save")
public class TicketSaveServlet extends HttpServlet {

    private TicketDao ticketDao = new TicketDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("ticketClassId", req.getParameter("ticketClassId"));
        req.setAttribute("cruiseId", req.getParameter("cruiseId"));
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/ticket/ticketUpdate.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long cruiseId = Long.valueOf(req.getParameter("cruiseId"));
        Long ticketClassId = Long.valueOf(req.getParameter("ticketClassId"));
        BigDecimal price = new BigDecimal(req.getParameter("price"));

        Ticket ticket = new Ticket();
        ticket.setCruiseId(cruiseId);
        ticket.setTicketClassId(ticketClassId);
        ticket.setPrice(price);
        ticketDao.update(ticket);

        resp.sendRedirect("/cruise/details?id=" + cruiseId);
    }
}
