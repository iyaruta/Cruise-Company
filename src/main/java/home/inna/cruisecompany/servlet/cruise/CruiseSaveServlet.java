package home.inna.cruisecompany.servlet.cruise;

import home.inna.cruisecompany.data.Cruise;
import home.inna.cruisecompany.data.Port;
import home.inna.cruisecompany.data.Ship;
import home.inna.cruisecompany.data.Waypoint;
import home.inna.cruisecompany.service.CruiseService;
import home.inna.cruisecompany.service.PortService;
import home.inna.cruisecompany.service.ShipService;
import home.inna.cruisecompany.service.impl.CruiseServiceImpl;
import home.inna.cruisecompany.service.impl.PortServiceImpl;
import home.inna.cruisecompany.service.impl.ShipServiceImpl;
import home.inna.cruisecompany.util.WebUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/cruise/save")
public class CruiseSaveServlet extends HttpServlet {


    private CruiseService cruiseService = new CruiseServiceImpl();
    private ShipService shipService = new ShipServiceImpl();
    private PortService portService = new PortServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        if (id == null) {
            List<Port> ports = portService.findAll();
            req.setAttribute("ports", ports);
        } else {
            Cruise cruise = cruiseService.get(id);
            req.setAttribute("cruise", cruise);
        }
        List<Ship> ships = shipService.findAll();
        req.setAttribute("ships", ships);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/cruise/cruiseUpdate.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        Long shipId = WebUtil.id(req, "shipId");
        String name = req.getParameter("name");

        Cruise cruise = new Cruise();
        cruise.setId(id);
        cruise.setName(name);
        cruise.setShipId(shipId);

        applyWaypoints(req, cruise);

        cruiseService.saveOrUpdate(cruise);
        resp.sendRedirect("/cruise");
    }

    private void applyWaypoints(HttpServletRequest req, Cruise cruise) {
        if (cruise.getId() != null) {
            return;
        }

        LocalDateTime departure = LocalDateTime.parse(req.getParameter("departure"));
        Long departurePortId = WebUtil.id(req, "departurePortId");
        Waypoint departurePoint = new Waypoint();
        departurePoint.setDeparture(departure);
        departurePoint.setPortId(departurePortId);

        Long arrivalPortId = WebUtil.id(req, "arrivalPortId");
        LocalDateTime arrival = LocalDateTime.parse(req.getParameter("arrival"));
        Waypoint arrivalPoint = new Waypoint();
        arrivalPoint.setArrival(arrival);
        arrivalPoint.setPortId(arrivalPortId);

        List<Waypoint> waypoints = new ArrayList<>();
        waypoints.add(departurePoint);
        waypoints.add(arrivalPoint);
        cruise.setWaypoints(waypoints);
    }
}
