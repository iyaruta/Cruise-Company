package com.epam.lab.cruisecompany.servlet.cruise;

import com.epam.lab.cruisecompany.dao.CruiseDao;
import com.epam.lab.cruisecompany.dao.ShipDao;
import com.epam.lab.cruisecompany.dao.TicketClassDao;
import com.epam.lab.cruisecompany.dao.WaypointDao;
import com.epam.lab.cruisecompany.dao.jdbc.CruiseDaoImpl;
import com.epam.lab.cruisecompany.dao.jdbc.ShipDaoImpl;
import com.epam.lab.cruisecompany.dao.jdbc.TicketClassDaoImpl;
import com.epam.lab.cruisecompany.dao.jdbc.WaypointDaoImpl;
import com.epam.lab.cruisecompany.data.Cruise;
import com.epam.lab.cruisecompany.data.Ship;
import com.epam.lab.cruisecompany.data.TicketClass;
import com.epam.lab.cruisecompany.data.Waypoint;
import com.epam.lab.cruisecompany.util.WebUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cruise/details")
public class CruiseDetailsServlet extends HttpServlet {

    private WaypointDao waypointDao = new WaypointDaoImpl();
    private CruiseDao cruiseDao = new CruiseDaoImpl();
    private TicketClassDao ticketClassDao = new TicketClassDaoImpl();
    private ShipDao shipDao = new ShipDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);

        List<Waypoint> waypoints = waypointDao.findByCruise(id);
        Cruise cruise = cruiseDao.get(id);
        List<TicketClass> ticketClasses = ticketClassDao.findByCruise(id);
        Ship ship = shipDao.get(cruise.getShipId());
        req.setAttribute("waypoints", waypoints);
        req.setAttribute("cruise", cruise);
        req.setAttribute("ticketClasses", ticketClasses);
        req.setAttribute("ship", ship);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/cruise/cruiseDetails.jsp");
        requestDispatcher.forward(req, resp);
    }
}
