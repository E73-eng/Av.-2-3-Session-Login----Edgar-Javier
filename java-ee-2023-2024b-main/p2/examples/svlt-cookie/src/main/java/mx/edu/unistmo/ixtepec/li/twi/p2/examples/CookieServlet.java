package mx.edu.unistmo.ixtepec.li.twi.p2.examples;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "CookieServlet", urlPatterns = "/CookieServlet")
public class CookieServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String COOKIE_NAME = "loginCookie";
    private static final String USERNAME = "edgar";
    private static final String PASSWORD = "123";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        System.out.println("Username: " + username); // Mensaje de depuración

        if (Objects.equals(username, USERNAME) && Objects.equals(password, PASSWORD)) {
            HttpSession session = request.getSession();           

            if ("on".equals(rememberMe)) {
                Cookie cookie = new Cookie(COOKIE_NAME, username);
                cookie.setMaxAge(3600 * 24 * 30); 
                response.addCookie(cookie);
            }

            session.setAttribute("username", username);

            System.out.println("Redireccionando a welcome.html"); 
            response.sendRedirect("welcome.html");
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h3>Usuario o Contraseña Incorrecto</h3>");
            out.println("<a href='index.html'>Volver a intentar</a>");
        }
    }
}