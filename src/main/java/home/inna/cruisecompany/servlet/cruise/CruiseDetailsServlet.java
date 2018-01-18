package home.inna.cruisecompany.servlet.cruise;

import home.inna.cruisecompany.dao.*;
import home.inna.cruisecompany.dao.jdbc.*;
import home.inna.cruisecompany.data.Cruise;
import home.inna.cruisecompany.data.Ship;
import home.inna.cruisecompany.data.TicketClass;
import home.inna.cruisecompany.data.Waypoint;
import home.inna.cruisecompany.util.WebUtil;

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
    private TicketDao ticketDao = new TicketDaoImpl();

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
