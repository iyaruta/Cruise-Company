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
import java.util.List;

@WebServlet("/excursion")
public class ExcursionServlet extends HttpServlet {

    private ExcursionService excursionService = new ExcursionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long portId = WebUtil.id(req, "portId");
        List<Excursion> excursions = excursionService.findByPort(portId);
        req.setAttribute("portId", portId);
        req.setAttribute("excursions", excursions);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/excursion/excursion.jsp");
        requestDispatcher.forward(req, resp);
    }
}
