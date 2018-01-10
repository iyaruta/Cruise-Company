package com.epam.lab.cruisecompany.servlet.excursion;

import com.epam.lab.cruisecompany.dao.ExcursionDao;
import com.epam.lab.cruisecompany.dao.jdbc.ExcursionDaoImpl;
import com.epam.lab.cruisecompany.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/excursion/delete")
public class ExcursionDeleteServlet extends HttpServlet {

    private ExcursionDao excursionDao = new ExcursionDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        excursionDao.delete(id);

        resp.sendRedirect("/excurcion");
    }
}
