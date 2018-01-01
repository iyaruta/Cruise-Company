package com.epam.lab.cruisecompany.servlet.ship;

import com.epam.lab.cruisecompany.dao.ShipDao;
import com.epam.lab.cruisecompany.dao.jdbc.ShipDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ship/delete")
public class ShipDeleteServlet extends HttpServlet {

    private ShipDao shipDao = new ShipDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringId = req.getParameter("id");
        Long id = Long.valueOf(stringId);
        shipDao.delete(id);

        resp.sendRedirect("/ship");
    }
}
