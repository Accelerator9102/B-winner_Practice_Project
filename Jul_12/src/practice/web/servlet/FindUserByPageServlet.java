package practice.web.servlet;

import practice.domain.PageBean;
import practice.domain.User;
import practice.service.UserService;
import practice.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String currentPage=request.getParameter("currentPage");
        String rows = request.getParameter("rows");


        if(currentPage==null || "".equals(currentPage)){
            currentPage="1";
        }
        if(rows==null || "".equals(rows)){
            rows="5";
        }

        Map<String, String[]> condition = request.getParameterMap();


        UserService service=new UserServiceImpl();
        PageBean<User> pb=service.findUserByPage(currentPage,rows, condition);

        System.out.println(pb);

        request.setAttribute("pb",pb);
        request.setAttribute("condition",condition);
        request.getRequestDispatcher("/list.jsp").forward(request,response);


    }
}
