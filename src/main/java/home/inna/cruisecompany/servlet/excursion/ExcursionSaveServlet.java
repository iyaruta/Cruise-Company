package home.inna.cruisecompany.servlet.excursion;

import home.inna.cruisecompany.data.Excursion;
import home.inna.cruisecompany.service.ExcursionService;
import home.inna.cruisecompany.service.impl.ExcursionServiceImpl;
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

    private ExcursionService excursionService = new ExcursionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        if (id != null) {
            Excursion excursion = excursionService.get(id);
            req.setAttribute("excursion", excursion);
        }
        req.setAttribute("portId", req.getParameter("portId"));
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/excursion/excursionUpdate.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        String name = req.getParameter("name");
        String details = req.getParameter("details");
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        Long portId = WebUtil.id(req, "portId");

        Excursion excursion = new Excursion();
        excursion.setId(id);
        excursion.setName(name);
        excursion.setDetails(details);
        excursion.setPortId(portId);
        excursion.setPrice(price);

        excursionService.saveOrUpdate(excursion);
        resp.sendRedirect("/excursion?portId=" + portId);
    }
}
