package Cookies;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/setcookiesservlet")
public class SetCookiesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//------------------Example-------------------------------------------------------------
        Cookie cookie1 = new Cookie("some_id", "52");
        Cookie cookie2 = new Cookie("some_name", "Chaika");

        cookie1.setMaxAge(3600);
        cookie2.setMaxAge(3600);

        response.addCookie(cookie1);
        response.addCookie(cookie2);
//---------------------------------------------------------------------------------------

    }
}