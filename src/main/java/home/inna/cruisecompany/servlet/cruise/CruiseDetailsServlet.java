package home.inna.cruisecompany.servlet.cruise;

import home.inna.cruisecompany.data.Cruise;
import home.inna.cruisecompany.data.CruiseTicket;
import home.inna.cruisecompany.data.Ship;
import home.inna.cruisecompany.data.Waypoint;
import home.inna.cruisecompany.service.CruiseService;
import home.inna.cruisecompany.service.CruiseTicketService;
import home.inna.cruisecompany.service.ShipService;
import home.inna.cruisecompany.service.WaypointService;
import home.inna.cruisecompany.service.impl.CruiseServiceImpl;
import home.inna.cruisecompany.service.impl.CruiseTicketServiceImpl;
import home.inna.cruisecompany.service.impl.ShipServiceImpl;
import home.inna.cruisecompany.service.impl.WaypointServiceImpl;
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

    private CruiseService cruiseService = new CruiseServiceImpl();
    private WaypointService waypointService = new WaypointServiceImpl();
    private ShipService shipService = new ShipServiceImpl();
    private CruiseTicketService cruiseTicketService = new CruiseTicketServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);

        List<Waypoint> waypoints = waypointService.findByCruise(id);
        Cruise cruise = cruiseService.get(id);
        List<CruiseTicket> ticketClasses = cruiseTicketService.find(id);
        Ship ship = shipService.get(cruise.getShipId());
        req.setAttribute("waypoints", waypoints);
        req.setAttribute("cruise", cruise);
        req.setAttribute("ticketClasses", ticketClasses);
        req.setAttribute("ship", ship);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/cruise/cruiseDetails.jsp");
        requestDispatcher.forward(req, resp);
    }
}
