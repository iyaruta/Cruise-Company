package home.inna.cruisecompany.servlet.waypoint;

import home.inna.cruisecompany.dao.PortDao;
import home.inna.cruisecompany.dao.WaypointDao;
import home.inna.cruisecompany.dao.jdbc.PortDaoImpl;
import home.inna.cruisecompany.dao.jdbc.WaypointDaoImpl;
import home.inna.cruisecompany.data.Port;
import home.inna.cruisecompany.data.Waypoint;
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

    private WaypointDao waypointDao = new WaypointDaoImpl();
    private PortDao portDao = new PortDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        if (id != null) {
            Waypoint waypoint = waypointDao.get(id);
            req.setAttribute("waypoint", waypoint);
        }

        List<Port> ports = portDao.findAll();
        req.setAttribute("ports", ports);
        req.setAttribute("cruiseId", req.getParameter("cruiseId"));
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/waypoint/waypointUpdate.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long cruiseId = Long.valueOf(req.getParameter("cruiseId"));
        Long portId = Long.valueOf(req.getParameter("portId"));
        LocalDateTime arrival = LocalDateTime.parse(req.getParameter("arrival"));
        LocalDateTime departure = LocalDateTime.parse(req.getParameter("departure"));

        Waypoint waypoint = new Waypoint();
        waypoint.setPortId(portId);
        waypoint.setCruiseId(cruiseId);
        waypoint.setArrival(arrival);
        waypoint.setDeparture(departure);

        Long id = WebUtil.id(req);
        if (id == null) {
            waypointDao.save(waypoint);
        } else {
            waypoint.setId(id);
            waypointDao.update(waypoint);
        }
        resp.sendRedirect("/cruise/details?id=" + cruiseId);
    }
}
