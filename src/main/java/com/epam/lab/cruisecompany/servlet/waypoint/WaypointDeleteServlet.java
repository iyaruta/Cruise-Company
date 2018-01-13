package com.epam.lab.cruisecompany.servlet.waypoint;

import com.epam.lab.cruisecompany.dao.WaypointDao;
import com.epam.lab.cruisecompany.dao.jdbc.WaypointDaoImpl;
import com.epam.lab.cruisecompany.data.Waypoint;
import com.epam.lab.cruisecompany.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/waypoint/delete")
public class WaypointDeleteServlet extends HttpServlet {

    private WaypointDao waypointDao = new WaypointDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        Waypoint waypoint = waypointDao.get(id);
        Long cruiseId = waypoint.getCruiseId();
        waypointDao.delete(id);
        resp.sendRedirect("/cruise/details?id=" + cruiseId);
    }
}
