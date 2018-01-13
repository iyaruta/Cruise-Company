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
import java.util.List;

@WebServlet("/excursion")
public class ExcursionServlet extends HttpServlet {

    private ExcursionDao excursionDao = new ExcursionDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long portId = WebUtil.id(req, "portId");
        List<Excursion> excursions = excursionDao.findByPort(portId);
        req.setAttribute("excursions", excursions);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/excursion/excursion.jsp");
        requestDispatcher.forward(req, resp);
    }
}
