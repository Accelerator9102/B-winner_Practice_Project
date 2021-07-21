package practice.web.servlet;

import org.apache.commons.beanutils.BeanUtils;
import practice.domain.User;
import practice.service.UserService;
import practice.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String verifycode = req.getParameter("verifycode");
        HttpSession session = req.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");

        if(!checkcode_server.equalsIgnoreCase(verifycode)){
            //wrong
            req.setAttribute("login_msg", "Invalid verification code");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);

            return;
        }

        Map<String, String[]> map =req.getParameterMap();


        User user = new User();
        try{
            BeanUtils.populate(user,map);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        UserService service=new UserServiceImpl();
        User loginUser = service.login(user);

        if(loginUser!=null){
            session.setAttribute("user",loginUser);
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
        }else{
            req.setAttribute("login_msg","Invalid username or password");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }

    }
}
