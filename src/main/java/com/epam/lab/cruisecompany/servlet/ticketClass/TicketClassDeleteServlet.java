package com.epam.lab.cruisecompany.servlet.ticketClass;

import com.epam.lab.cruisecompany.dao.TicketClassDao;
import com.epam.lab.cruisecompany.dao.jdbc.TicketClassDaoImpl;
import com.epam.lab.cruisecompany.data.TicketClass;
import com.epam.lab.cruisecompany.util.WebUtil;

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
