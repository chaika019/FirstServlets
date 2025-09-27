package logic_DontOpen_Trash;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/carsservlet")
public class CarsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println("Hello from CarsServlet!");

         try {
             Class.forName("org.postgresql.Driver");
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cars");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}