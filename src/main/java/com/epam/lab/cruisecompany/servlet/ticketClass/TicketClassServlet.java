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
