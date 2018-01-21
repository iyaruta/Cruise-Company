package home.inna.cruisecompany.servlet.port;

import home.inna.cruisecompany.data.Port;
import home.inna.cruisecompany.service.PortService;
import home.inna.cruisecompany.service.impl.PortServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/port")
public class PortServlet extends HttpServlet {

    private PortService portService = new PortServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Port> ports = portService.findAll();

        req.setAttribute("port", ports);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/port/port.jsp");
        requestDispatcher.forward(req, resp);
    }
}
