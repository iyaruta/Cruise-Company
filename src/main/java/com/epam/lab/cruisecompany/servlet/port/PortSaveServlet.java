package com.epam.lab.cruisecompany.servlet.port;

import com.epam.lab.cruisecompany.dao.PortDao;
import com.epam.lab.cruisecompany.dao.jdbc.PortDaoImpl;
import com.epam.lab.cruisecompany.data.Port;
import com.epam.lab.cruisecompany.util.WebUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet
public class PortSaveServlet extends HttpServlet {

    private PortDao portDao = new PortDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = WebUtil.id(req);
        if (id != null) {
            Port port = portDao.get(id);
            req.setAttribute("port", port);
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/port/portUpdate.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        Port port = new Port();
        port.setName(name);

        Long id = WebUtil.id(req);
        if (id == null) {
            portDao.save(port);
        } else {
            port.setId(id);
            portDao.update(port);
        }
        resp.sendRedirect("/port");
    }
}
