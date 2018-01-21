package home.inna.cruisecompany.util;

import home.inna.cruisecompany.data.Role;
import home.inna.cruisecompany.data.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class WebUtil extends HttpServlet {

    public static Long id(HttpServletRequest req) {
        return id(req, "id");
    }

    public static Long id(HttpServletRequest req, String idName) {
        String id = req.getParameter(idName);
        return id == null || id.length() == 0 ? null : Long.valueOf(id);
    }

    public static User getUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user.getRole() == Role.USER) {
                return user;
            }
        }
        return null;
    }
}
