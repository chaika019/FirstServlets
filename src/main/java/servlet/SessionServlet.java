package servlet;

import dto.UserDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {
    private static final String USER = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var session = req.getSession();
        var user = session.getAttribute(USER);
        if(user == null) {
            user = UserDto.builder()
                    .id(5L)
                    .email("chaika@19.com")
                    .build();
        }
        session.setAttribute(USER, user);
    }
}