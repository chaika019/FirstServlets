package Cookies;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getcookiesservlet")
public class GetCookiesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();

        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        for(Cookie cookie : cookies) {
            out.println("<h1>" + cookie.getName() + " : " + cookie.getValue());
        }
        out.println("</body></html>");
    }
}