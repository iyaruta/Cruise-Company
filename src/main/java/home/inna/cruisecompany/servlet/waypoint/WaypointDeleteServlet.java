package home.inna.cruisecompany.servlet.waypoint;

import home.inna.cruisecompany.data.Waypoint;
import home.inna.cruisecompany.service.WaypointService;
import home.inna.cruisecompany.service.impl.WaypointServiceImpl;
import home.inna.cruisecompany.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/waypoint/delete")
public class WaypointDeleteServlet extends HttpServlet {

    private WaypointService waypointService = new WaypointServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        Waypoint waypoint = waypointService.get(id);
        Long cruiseId = waypoint.getCruiseId();
        waypointService.delete(id);
        resp.sendRedirect("/cruise/details?id=" + cruiseId);
    }
}
