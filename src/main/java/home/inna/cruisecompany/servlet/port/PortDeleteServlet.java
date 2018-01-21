package home.inna.cruisecompany.servlet.port;

import home.inna.cruisecompany.service.PortService;
import home.inna.cruisecompany.service.impl.PortServiceImpl;
import home.inna.cruisecompany.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/port/delete")
public class PortDeleteServlet extends HttpServlet {

    private PortService portService = new PortServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        portService.delete(id);
        resp.sendRedirect("/port");
    }
}
