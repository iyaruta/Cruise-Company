package home.inna.cruisecompany.servlet.ship;

import home.inna.cruisecompany.dao.ShipDao;
import home.inna.cruisecompany.dao.jdbc.ShipDaoImpl;
import home.inna.cruisecompany.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ship/delete")
public class ShipDeleteServlet extends HttpServlet {

    private ShipDao shipDao = new ShipDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long util = WebUtil.id(req);
        shipDao.delete(util);

        resp.sendRedirect("/ship");
    }
}
