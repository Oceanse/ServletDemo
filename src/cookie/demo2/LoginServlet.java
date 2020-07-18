package cookie.demo2;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * http://localhost:8080/ServletDemo_war_exploded/logincookie?name=admin&password=123
 * 或者网页登录：http://localhost:8080/ServletDemo_war_exploded/cookie/demo2/index.html
 * 添加缓存
 */
@WebServlet("/logincookie")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        // 用户名和密码分别为：admin , 123456
        if (name.equals("admin") && password.equals("123")) {
            out.print("您已成功登录系统!");
            out.print("<br>欢迎您, " + name);
            Cookie ck = new Cookie("name", name);
            //cookie有效期1天
            ck.setMaxAge(24*60*60);
            response.addCookie(ck);
            request.getRequestDispatcher("/cookie/demo2/index.html").include(request,response);

        } else {
            out.print("<font style='color:red;'>用户名或密码错误!</font>");
            request.getRequestDispatcher("/cookie/demo2/index.html").include(request,response);
        }
        out.close();
    }

}
