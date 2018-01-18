package home.inna.cruisecompany.servlet.port;

import home.inna.cruisecompany.dao.PortDao;
import home.inna.cruisecompany.dao.jdbc.PortDaoImpl;
import home.inna.cruisecompany.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/port/delete")
public class PortDeleteServlet extends HttpServlet {

    private PortDao portDao = new PortDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        portDao.delete(id);
        resp.sendRedirect("/port");
    }
}
