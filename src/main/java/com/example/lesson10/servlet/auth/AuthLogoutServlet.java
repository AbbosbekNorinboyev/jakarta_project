package com.example.lesson10.servlet.auth;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(
        name = "com.example.lesson10.servlet.auth.AuthLogoutServlet",
        value = "/auth/logout"
)
public class AuthLogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/authuser/logout.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        Cookie cookie = new Cookie("rememberMe", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        resp.addCookie(cookie);
        resp.sendRedirect("/auth/login");
    }
}
