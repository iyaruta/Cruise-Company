package com.epam.lab.cruisecompany.servlet.cruise;

import com.epam.lab.cruisecompany.dao.CruiseDao;
import com.epam.lab.cruisecompany.dao.jdbc.CruiseDaoImpl;
import com.epam.lab.cruisecompany.data.Cruise;

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

    private CruiseDao cruiseDao = new CruiseDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cruise> cruises = cruiseDao.findAll();

        req.setAttribute("cruises", cruises);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/cruise/cruise.jsp");
        requestDispatcher.forward(req, resp);
    }
}
