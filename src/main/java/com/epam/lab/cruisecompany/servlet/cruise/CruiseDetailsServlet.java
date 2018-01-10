package com.epam.lab.cruisecompany.servlet.cruise;

import com.epam.lab.cruisecompany.dao.CruiseDao;
import com.epam.lab.cruisecompany.dao.WaypointDao;
import com.epam.lab.cruisecompany.dao.jdbc.CruiseDaoImpl;
import com.epam.lab.cruisecompany.dao.jdbc.WaypointDaoImpl;
import com.epam.lab.cruisecompany.data.Cruise;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);

        List<Waypoint> waypoints = waypointDao.findByCruise(id);
        Cruise cruise = cruiseDao.get(id);
        req.setAttribute("waypoints", waypoints);
        req.setAttribute("cruise", cruise);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/cruise/cruiseDetails.jsp");
        requestDispatcher.forward(req, resp);
    }
}
