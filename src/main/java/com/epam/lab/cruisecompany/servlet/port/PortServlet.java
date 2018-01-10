package com.epam.lab.cruisecompany.servlet.port;

import com.epam.lab.cruisecompany.dao.PortDao;
import com.epam.lab.cruisecompany.dao.jdbc.PortDaoImpl;
import com.epam.lab.cruisecompany.data.Port;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/port")
public class PortServlet extends HttpServlet {

    private PortDao portDao = new PortDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Port> ports = portDao.findAll();

        req.setAttribute("port", ports);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/port/port.jsp");
        requestDispatcher.forward(req, resp);
    }
}
