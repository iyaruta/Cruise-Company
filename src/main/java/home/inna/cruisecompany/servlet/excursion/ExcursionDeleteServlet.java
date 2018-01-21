package home.inna.cruisecompany.servlet.excursion;

import home.inna.cruisecompany.service.ExcursionService;
import home.inna.cruisecompany.service.impl.ExcursionServiceImpl;
import home.inna.cruisecompany.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/excursion/delete")
public class ExcursionDeleteServlet extends HttpServlet {

    private ExcursionService excursionService = new ExcursionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        excursionService.delete(id);

        resp.sendRedirect("/excursion?portId=" + req.getParameter("portId"));
    }
}
