package servlet;

import dto.CreateUserDto;
import entity.Gender;
import entity.Role;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;
import utils.JspHelper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        req.setAttribute("roles", Role.values());
        req.setAttribute("genders", Gender.values());

        req.getRequestDispatcher(JspHelper.getPath("registration")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        var userDto = CreateUserDto.builder()
                .name(req.getParameter("name"))
                .birthday(req.getParameter("birthday"))
                .password(req.getParameter("pwd"))
                .role(req.getParameter("role"))
                .gender(req.getParameter("gender"))
                .build();

        try {
            userService.create(userDto);
            resp.sendRedirect(("/login"));
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
        }
    }
}