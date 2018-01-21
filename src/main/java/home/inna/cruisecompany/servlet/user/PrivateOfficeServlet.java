package home.inna.cruisecompany.servlet.user;

import home.inna.cruisecompany.data.Cruise;
import home.inna.cruisecompany.data.Excursion;
import home.inna.cruisecompany.data.User;
import home.inna.cruisecompany.service.CruiseService;
import home.inna.cruisecompany.service.ExcursionService;
import home.inna.cruisecompany.service.impl.CruiseServiceImpl;
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

@WebServlet("/privateOffice")
public class PrivateOfficeServlet extends HttpServlet {

    private ExcursionService excursionService = new ExcursionServiceImpl();
    private CruiseService cruiseService = new CruiseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = WebUtil.getUser(req);
        if (user != null) {
            List<Excursion> excursions = excursionService.excursionByUser(user.getId());
            List<Cruise> cruises = cruiseService.cruiseByUser(user.getId());
            req.setAttribute("excursions", excursions);
            req.setAttribute("cruises", cruises);
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/user/user.jsp");
        requestDispatcher.forward(req, resp);
    }

}
