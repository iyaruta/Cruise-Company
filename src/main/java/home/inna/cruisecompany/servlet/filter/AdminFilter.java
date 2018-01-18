package home.inna.cruisecompany.servlet.filter;

import home.inna.cruisecompany.data.Role;
import home.inna.cruisecompany.data.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        if (isAdmin(session)) {
            chain.doFilter(req, resp);
        } else {
            response.sendError(403);
        }
    }

    @Override
    public void destroy() {

    }

    private Boolean isAdmin(HttpSession session) {
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user.getRole() == Role.ADMIN) {
                return true;
            }
        }
        return false;
    }
}
