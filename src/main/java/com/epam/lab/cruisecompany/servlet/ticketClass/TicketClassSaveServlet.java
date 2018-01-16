package com.epam.lab.cruisecompany.servlet.ticketClass;

import com.epam.lab.cruisecompany.dao.TicketClassDao;
import com.epam.lab.cruisecompany.dao.jdbc.TicketClassDaoImpl;
import com.epam.lab.cruisecompany.data.TicketClass;
import com.epam.lab.cruisecompany.util.WebUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ticketClass/save")
public class TicketClassSaveServlet extends HttpServlet {

    private TicketClassDao ticketClassDao = new TicketClassDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        if (id != null) {
            TicketClass ticketClass = ticketClassDao.get(id);
            req.setAttribute("ticketClass", ticketClass);
        }
        req.setAttribute("shipId", req.getParameter("shipId"));
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/ticketClass/ticketClassUpdate.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long shipId = Long.valueOf(req.getParameter("shipId"));
        String type = req.getParameter("type");
        Integer count = Integer.valueOf(req.getParameter("count"));
        String bonus = req.getParameter("bonus");

        TicketClass ticketClass = new TicketClass();
        ticketClass.setShipId(shipId);
        ticketClass.setType(type);
        ticketClass.setCount(count);
        ticketClass.setBonus(bonus);

        Long id = WebUtil.id(req);
        if (id == null) {
            ticketClassDao.save(ticketClass);
        } else {
            ticketClass.setId(id);
            ticketClassDao.update(ticketClass);
        }
        resp.sendRedirect("/ticketClass?shipId=" + shipId);
    }
}
