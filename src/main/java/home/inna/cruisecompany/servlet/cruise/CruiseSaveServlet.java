package home.inna.cruisecompany.servlet.cruise;

import home.inna.cruisecompany.dao.CruiseDao;
import home.inna.cruisecompany.dao.ShipDao;
import home.inna.cruisecompany.dao.jdbc.CruiseDaoImpl;
import home.inna.cruisecompany.dao.jdbc.ShipDaoImpl;
import home.inna.cruisecompany.data.Cruise;
import home.inna.cruisecompany.data.Ship;
import home.inna.cruisecompany.util.WebUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/cruise/save")
public class CruiseSaveServlet extends HttpServlet {

    private CruiseDao cruiseDao = new CruiseDaoImpl();
    private ShipDao shipDao = new ShipDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        if (id != null) {
            Cruise cruise = cruiseDao.get(id);
            req.setAttribute("cruise", cruise);
        }
        List<Ship> ships = shipDao.findAll();
        req.setAttribute("ships", ships);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/cruise/cruiseUpdate.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Long shipId = WebUtil.id(req, "shipId");

        Cruise cruise = new Cruise();
        cruise.setName(name);
        cruise.setShipId(shipId);

        Long id = WebUtil.id(req);
        if (id == null) {
            cruiseDao.save(cruise);
        } else {
            cruise.setId(id);
            cruiseDao.update(cruise);
        }
        resp.sendRedirect("/cruise");
    }
}
