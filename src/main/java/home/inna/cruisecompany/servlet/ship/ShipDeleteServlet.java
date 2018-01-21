package home.inna.cruisecompany.servlet.ship;

import home.inna.cruisecompany.service.ShipService;
import home.inna.cruisecompany.service.impl.ShipServiceImpl;
import home.inna.cruisecompany.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/ship/delete")
public class ShipDeleteServlet extends HttpServlet {

    private ShipService shipService = new ShipServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long util = WebUtil.id(req);
        shipService.delete(util);

        resp.sendRedirect("/ship");
    }
}
