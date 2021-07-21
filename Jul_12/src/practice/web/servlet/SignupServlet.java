package practice.web.servlet;

import org.apache.commons.beanutils.BeanUtils;
import practice.domain.User;
import practice.service.UserService;
import practice.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/signupServlet")
public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String verifycode = request.getParameter("verifycode");
        HttpSession session = request.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");

        if(!checkcode_server.equalsIgnoreCase(verifycode)){
            //wrong
            request.setAttribute("login_msg", "Invalid verification code");
            request.getRequestDispatcher("/signup.jsp").forward(request,response);

            return;
        }
        String name=request.getParameter("name");
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        User user=new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);

        UserService service=new UserServiceImpl();
        service.signup(user);
        response.sendRedirect(request.getContextPath()+"/login.jsp");
//        session.setAttribute("user",user);
//        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }
}
