package home.inna.cruisecompany.servlet.port;

import home.inna.cruisecompany.data.Port;
import home.inna.cruisecompany.service.PortService;
import home.inna.cruisecompany.service.impl.PortServiceImpl;
import home.inna.cruisecompany.util.WebUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/port/save")
public class PortSaveServlet extends HttpServlet {

    private PortService portService = new PortServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        if (id != null) {
            Port port = portService.get(id);
            req.setAttribute("port", port);
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/port/portUpdate.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        String name = req.getParameter("name");

        Port port = new Port();
        port.setId(id);
        port.setName(name);

        portService.saveOrUpdate(port);
        resp.sendRedirect("/port");
    }
}
