package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/dispatcher")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        var dispatcher = req.getRequestDispatcher("/flights");
        dispatcher.include(req, resp);


//        НЕ будет выводить тег <h1>, т.к. в lightServlet мы установили try with resources
//        var write = resp.getWriter();
//        write.write("<h1>DISPATCHER</h1>");
    }
}