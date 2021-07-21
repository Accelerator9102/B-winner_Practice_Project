package practice.web.filter;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();

        if (uri.contains("/login.jsp") || uri.contains("/signup.jsp") ||uri.contains("/signupServlet") ||uri.contains("/loginServlet") || uri.contains("/css/") || uri.contains("/js/") || uri.contains("/fonts/") || uri.contains("/verificationServlet")) {
            chain.doFilter(request, response);
        } else {
            Object user = req.getSession().getAttribute("user");
            if (user != null) {
                chain.doFilter(request, response);
            } else {
                request.setAttribute("login_msg", "Please log in first");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }

        }
    }
}
