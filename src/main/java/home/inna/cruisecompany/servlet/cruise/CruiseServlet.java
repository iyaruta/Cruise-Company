package home.inna.cruisecompany.servlet.cruise;

import home.inna.cruisecompany.data.Cruise;
import home.inna.cruisecompany.service.CruiseService;
import home.inna.cruisecompany.service.impl.CruiseServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cruise")
public class CruiseServlet extends HttpServlet {

    private CruiseService cruiseService = new CruiseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cruise> cruises = cruiseService.findAll();

        req.setAttribute("cruises", cruises);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/cruise/cruise.jsp");
        requestDispatcher.forward(req, resp);
    }
}
