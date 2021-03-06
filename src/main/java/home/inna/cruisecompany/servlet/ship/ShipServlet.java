package home.inna.cruisecompany.servlet.ship;

import home.inna.cruisecompany.data.Ship;
import home.inna.cruisecompany.service.ShipService;
import home.inna.cruisecompany.service.impl.ShipServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ship")
public class ShipServlet extends HttpServlet {

    private ShipService shipService = new ShipServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Ship> ships = shipService.findAll();

        req.setAttribute("ships", ships);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/ship/ship.jsp");
        requestDispatcher.forward(req, resp);
    }
}
