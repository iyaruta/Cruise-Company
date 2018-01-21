package home.inna.cruisecompany.servlet.waypoint;

import home.inna.cruisecompany.data.Port;
import home.inna.cruisecompany.data.Waypoint;
import home.inna.cruisecompany.service.PortService;
import home.inna.cruisecompany.service.WaypointService;
import home.inna.cruisecompany.service.impl.PortServiceImpl;
import home.inna.cruisecompany.service.impl.WaypointServiceImpl;
import home.inna.cruisecompany.util.WebUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/admin/waypoint/save")
public class WaypointSaveServlet extends HttpServlet {

    private WaypointService waypointService = new WaypointServiceImpl();
    private PortService portService = new PortServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        if (id != null) {
            Waypoint waypoint = waypointService.get(id);
            req.setAttribute("waypoint", waypoint);
        }

        List<Port> ports = portService.findAll();
        req.setAttribute("ports", ports);
        req.setAttribute("cruiseId", req.getParameter("cruiseId"));
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/waypoint/waypointUpdate.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        Long cruiseId = Long.valueOf(req.getParameter("cruiseId"));
        Long portId = Long.valueOf(req.getParameter("portId"));

        String arrivalStr = req.getParameter("arrival");
        LocalDateTime arrival = null;
        if (arrivalStr != null && !arrivalStr.isEmpty()) {
            arrival = LocalDateTime.parse(arrivalStr);
        }

        String departureStr = req.getParameter("departure");
        LocalDateTime departure = null;
        if (departureStr != null && !departureStr.isEmpty()) {
            departure = LocalDateTime.parse(departureStr);
        }

        Waypoint waypoint = new Waypoint();
        waypoint.setId(id);
        waypoint.setPortId(portId);
        waypoint.setCruiseId(cruiseId);
        waypoint.setArrival(arrival);
        waypoint.setDeparture(departure);

        waypointService.saveOrUpdate(waypoint);
        resp.sendRedirect("/cruise/details?id=" + cruiseId);
    }
}
