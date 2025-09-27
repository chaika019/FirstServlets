package logic_DontOpen_Trash;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();


//----------------------------Типо авторизация--------------------------
//        String username = (String) session.getAttribute("currentUser");
//
//        if (username == null) {
//            //response для анонимного пользователя
//            //авторизация
//            //регистрация
//            //session.setAttribute("current_user", ID);
//        } else {
//            //response для авторизованного пользователя
//        }
// ---------------------------------------------------------------------


//----------------------------Корзина-----------------------------------
//        Cart cart = (Cart)session.getAttribute("cart");
//
//        String name = request.getParameter("name");
//        int quantity = Integer.parseInt(request.getParameter("quantity"));
//
//        if (cart == null) {
//            cart = new Cart();
//
//            cart.setName(name);
//            cart.setQuantity(quantity);
//        }
//
//        session.setAttribute("cart", cart);
// ---------------------------------------------------------------------




//----------------------------Счетчик-----------------------------------
        Integer count = (Integer)session.getAttribute("count");

        if (count == null) {
            session.setAttribute("count", 1);
            count = 1;
        } else {
            session.setAttribute("count", count + 1);
        }
//----------------------------------------------------------------------




//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        if (name == null) name = "Yagan";
//        if (surname == null) surname = "Don";

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
//        out.println("<h1>" + surname + " " + name + "</h1>");
        out.println("<h2>Ur count is: " + count + "</h2>");
//        out.println("<p>Click <a href=\"" + request.getContextPath() + "/secondpage\">Second Servlet</a></p>");
        out.println("</body></html>");


//        getServletContext().getRequestDispatcher("/showCart.jsp").forward(request, response);
        //        response.sendRedirect(request.getContextPath() + "/secondpage");
        //        response.sendRedirect(request.getContextPath() + "/secondpage");
    }

    public void destroy() {
    }
}