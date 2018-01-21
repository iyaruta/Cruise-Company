package home.inna.cruisecompany.servlet.cruise;

import home.inna.cruisecompany.service.CruiseService;
import home.inna.cruisecompany.service.impl.CruiseServiceImpl;
import home.inna.cruisecompany.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/cruise/delete")
public class CruiseDeleteServlet extends HttpServlet {

    private CruiseService cruiseService = new CruiseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        cruiseService.delete(id);

        resp.sendRedirect("/cruise");
    }
}
