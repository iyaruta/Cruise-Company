package home.inna.cruisecompany.servlet.excursion;

import home.inna.cruisecompany.data.User;
import home.inna.cruisecompany.service.ExcursionToUserService;
import home.inna.cruisecompany.service.impl.ExcursionToUserServiceImpl;
import home.inna.cruisecompany.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/buyExcursion")
public class BuyExcursionServlet extends HttpServlet {

    private ExcursionToUserService excursionToUserService = new ExcursionToUserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = WebUtil.getUser(req);
        if (user != null) {
            Long excursionId = WebUtil.id(req, "excursionId");
            excursionToUserService.buy(excursionId, user.getId());
        }
        resp.sendRedirect("excursion?portId=" + req.getParameter("portId"));
    }
}
