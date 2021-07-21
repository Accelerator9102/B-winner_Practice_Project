package practice.web.servlet;

import org.apache.commons.beanutils.BeanUtils;
import practice.domain.User;
import practice.service.UserService;
import practice.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> map=request.getParameterMap();

        User user=new User();
        try{
            BeanUtils.populate(user,map);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        UserService service=new UserServiceImpl();
        service.addUser(user);
        response.sendRedirect(request.getContextPath()+"/findUserByPageServlet");
    }
}
