package com.epam.lab.cruisecompany.servlet.ship;

import com.epam.lab.cruisecompany.dao.ShipDao;
import com.epam.lab.cruisecompany.dao.jdbc.ShipDaoImpl;
import com.epam.lab.cruisecompany.data.Ship;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ship")
public class ShipServlet extends HttpServlet {

    private ShipDao shipDao = new ShipDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Ship> ships = shipDao.findAll();

        req.setAttribute("ships", ships);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/ship/ship.jsp");
        requestDispatcher.forward(req, resp);
    }
}
