package cookie.demo1;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


/**
 * Cookie是在多个客户端请求之间持久存储的一小段信息。
 * Cookie具有名称，单个值和可选属性，例如注释，路径和域限定符，生存周期和版本号。
 * 默认情况下，每个请求都被视为新的请求,也就是两次请求毫无关系。
 * 在cookie技术中，servlet响应可以添加cookie返回给客户端，所以cookie存储在浏览器的缓存中。
 * 之后，如果用户发出请求，默认情况下会带上cookie， server端就可以识别出是同一个用户发出的请求
 */
@WebServlet("/addCookie")//这里的/是应用上下文路径
public class AddCookie extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            String username = request.getParameter("username");
            out.print("Welcome, " + username+"<br/><br/>");

            // creating cookie object
            Cookie ck = new Cookie("uname", username);
            //ck.setPath("/");

            //设置 cookie 过期的时间（以秒为单位）
            //ck.setMaxAge(20);//cookie有效期20s, 即使关闭浏览器20s之内重新打开浏览器重新发送请求，依然可以携带这个cookie
            response.addCookie(ck);//adding cookie in the response

            // creating submit button
            out.print("<form action='getCookie' method='GET'>");
            out.print("<input type='submit' value='Go to GetCookie Servlet'>");
            out.print("</form>");
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


