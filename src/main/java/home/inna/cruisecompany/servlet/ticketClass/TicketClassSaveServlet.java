package home.inna.cruisecompany.servlet.ticketClass;

import home.inna.cruisecompany.data.TicketClass;
import home.inna.cruisecompany.service.TicketClassService;
import home.inna.cruisecompany.service.impl.TicketClassServiceImpl;
import home.inna.cruisecompany.util.WebUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/ticketClass/save")
public class TicketClassSaveServlet extends HttpServlet {

    private TicketClassService ticketClassService = new TicketClassServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        if (id != null) {
            TicketClass ticketClass = ticketClassService.get(id);
            req.setAttribute("ticketClass", ticketClass);
        }
        req.setAttribute("shipId", req.getParameter("shipId"));
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/ticketClass/ticketClassUpdate.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        Long shipId = Long.valueOf(req.getParameter("shipId"));
        String type = req.getParameter("type");
        Integer count = Integer.valueOf(req.getParameter("count"));
        String bonus = req.getParameter("bonus");

        TicketClass ticketClass = new TicketClass();
        ticketClass.setId(id);
        ticketClass.setShipId(shipId);
        ticketClass.setType(type);
        ticketClass.setCount(count);
        ticketClass.setBonus(bonus);

        ticketClassService.saveOrUpdate(ticketClass);
        resp.sendRedirect("/admin/ticketClass?shipId=" + shipId);
    }
}
