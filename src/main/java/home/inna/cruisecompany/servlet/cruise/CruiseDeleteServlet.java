package home.inna.cruisecompany.servlet.cruise;

import home.inna.cruisecompany.dao.CruiseDao;
import home.inna.cruisecompany.dao.jdbc.CruiseDaoImpl;
import home.inna.cruisecompany.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/cruise/delete")
public class CruiseDeleteServlet extends HttpServlet {

    private CruiseDao cruiseDao = new CruiseDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        cruiseDao.delete(id);

        resp.sendRedirect("/cruise");
    }
}
