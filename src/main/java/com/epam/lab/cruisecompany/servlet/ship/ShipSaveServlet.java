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

@WebServlet("/ship/save")
public class ShipSaveServlet extends HttpServlet {

    private ShipDao shipDao = new ShipDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringId = req.getParameter("id");
        if (stringId != null) {
            Long id = Long.valueOf(stringId);
            Ship ship = shipDao.get(id);
            req.setAttribute("ship", ship);
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/ship/shipUpdate.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Integer passengers = Integer.valueOf(req.getParameter("passengers"));
        Integer crew = Integer.valueOf(req.getParameter("crew"));

        Ship ship = new Ship();
        ship.setName(name);
        ship.setPassengers(passengers);
        ship.setCrew(crew);


        String stringId = req.getParameter("id");
        if (stringId == null || stringId.length() == 0) {
            shipDao.save(ship);
        } else {
            Long id = Long.valueOf(stringId);
            ship.setId(id);
            shipDao.update(ship);
        }

        resp.sendRedirect("/ship");
    }
}
