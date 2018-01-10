package com.epam.lab.cruisecompany.servlet.excursion;

import com.epam.lab.cruisecompany.dao.ExcursionDao;
import com.epam.lab.cruisecompany.dao.jdbc.ExcursionDaoImpl;
import com.epam.lab.cruisecompany.data.Excursion;
import com.epam.lab.cruisecompany.util.WebUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/excursion/save")
public class ExcursionSaveServlet extends HttpServlet {

    private ExcursionDao excursionDao = new ExcursionDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        if (id != null) {
            Excursion excursion = excursionDao.get(id);
            req.setAttribute("excursion", excursion);
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/excursion/excursionUpdate.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String details = req.getParameter("details");
        Long portId = WebUtil.id(req, "portId");

        Excursion excursion = new Excursion();
        excursion.setName(name);
        excursion.setDetails(details);
        excursion.setPortId(portId);

        Long id = WebUtil.id(req);
        if (id == null) {
            excursionDao.save(excursion);
        } else {
            excursion.setId(id);
            excursionDao.update(excursion);
        }
        resp.sendRedirect("/excursion");
    }
}
