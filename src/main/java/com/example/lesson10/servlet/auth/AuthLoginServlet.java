package com.example.lesson10.servlet.auth;

import com.example.lesson10.dao.AuthUserDAO;
import com.example.lesson10.entity.AuthUser;
import com.example.lesson10.utils.PasswordUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Objects;

@WebServlet(
        name = "com.example.lesson10.servlet.auth.AuthLoginServlet",
        value = "/auth/login"
)
public class AuthLoginServlet extends HttpServlet {
    private final AuthUserDAO authUserDAO = new AuthUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/authuser/login.jsp");
        String next = req.getParameter("next");
        req.setAttribute("next", next);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = Objects.requireNonNullElse(req.getParameter("email"), "off");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("rememberMe");
        Runnable badCredentialsAction = () -> {
            {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/authuser/login.jsp");
                req.setAttribute("error_message", "Bad Credentials");
                try {
                    requestDispatcher.forward(req, resp);
                } catch (ServletException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        authUserDAO.findByEmail(email).ifPresentOrElse(authUser -> {
            if (!PasswordUtils.check(password, authUser.getPassword())) {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/authuser/login.jsp");
                req.setAttribute("error_message", "Bad Credentials");
                try {
                    requestDispatcher.forward(req, resp);
                } catch (ServletException | IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (!authUser.getStatus().equals(AuthUser.Status.ACTIVE)) {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/authuser/login.jsp");
                req.setAttribute("error_message", "User not active");
                try {
                    requestDispatcher.forward(req, resp);
                } catch (ServletException | IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("email", authUser.getEmail());
                session.setAttribute("role", authUser.getRole());
                session.setAttribute("id", authUser.getId());
                if (rememberMe.equals("on")) {
                    Cookie cookie = new Cookie("rememberMe", authUser.getId());
                    cookie.setMaxAge(60 * 60 * 24 * 10);
                    cookie.setPath("/");
                    resp.addCookie(cookie);
                }
                try {
                    String next = req.getParameter("next");
                    next = next == null || next.length() == 0 ? "/book/list" : next;
                    resp.sendRedirect(next);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, badCredentialsAction);
    }
}
