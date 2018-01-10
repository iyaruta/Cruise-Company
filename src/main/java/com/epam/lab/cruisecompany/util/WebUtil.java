package com.epam.lab.cruisecompany.util;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class WebUtil extends HttpServlet {

    public static Long id(HttpServletRequest req) {
        return id(req, "id");
    }

    public static Long id(HttpServletRequest req, String idName) {
        String id = req.getParameter(idName);
        return id == null || id.length() == 0 ? null : Long.valueOf(id);
    }
}
