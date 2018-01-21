package home.inna.cruisecompany.servlet.ship;

import home.inna.cruisecompany.data.Ship;
import home.inna.cruisecompany.service.ShipService;
import home.inna.cruisecompany.service.impl.ShipServiceImpl;
import home.inna.cruisecompany.util.WebUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/ship/save")
public class ShipSaveServlet extends HttpServlet {

    private ShipService shipService = new ShipServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        if (id != null) {
            Ship ship = shipService.get(id);
            req.setAttribute("ship", ship);
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/ship/shipUpdate.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        String name = req.getParameter("name");
        Integer passengers = Integer.valueOf(req.getParameter("passengers"));
        Integer crew = Integer.valueOf(req.getParameter("crew"));

        Ship ship = new Ship();
        ship.setId(id);
        ship.setName(name);
        ship.setPassengers(passengers);
        ship.setCrew(crew);

        shipService.saveOrUpdate(ship);
        resp.sendRedirect("/ship");
    }
}
