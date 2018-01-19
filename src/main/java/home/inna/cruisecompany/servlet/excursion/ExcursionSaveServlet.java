package home.inna.cruisecompany.servlet.excursion;

import home.inna.cruisecompany.dao.ExcursionDao;
import home.inna.cruisecompany.dao.jdbc.ExcursionDaoImpl;
import home.inna.cruisecompany.data.Excursion;
import home.inna.cruisecompany.util.WebUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/admin/excursion/save")
public class ExcursionSaveServlet extends HttpServlet {

    private ExcursionDao excursionDao = new ExcursionDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        if (id != null) {
            Excursion excursion = excursionDao.get(id);
            req.setAttribute("excursion", excursion);
        }
        req.setAttribute("portId", req.getParameter("portId"));
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/excursion/excursionUpdate.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String details = req.getParameter("details");
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        Long portId = WebUtil.id(req, "portId");

        Excursion excursion = new Excursion();
        excursion.setName(name);
        excursion.setDetails(details);
        excursion.setPortId(portId);
        excursion.setPrice(price);

        Long id = WebUtil.id(req);
        if (id == null) {
            excursionDao.save(excursion);
        } else {
            excursion.setId(id);
            excursionDao.update(excursion);
        }
        resp.sendRedirect("/excursion?portId=" + portId);
    }
}
