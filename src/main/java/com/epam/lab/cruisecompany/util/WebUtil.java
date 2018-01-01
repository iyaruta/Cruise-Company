package com.epam.lab.cruisecompany.util;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class WebUtil extends HttpServlet {

    public static Long id(HttpServletRequest req) {
        String id = req.getParameter("id");
        return id == null || id.length() == 0 ? null : Long.valueOf(id);
    }
}
