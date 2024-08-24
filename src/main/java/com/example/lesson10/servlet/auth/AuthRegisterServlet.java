package com.example.lesson10.servlet.auth;

import com.example.lesson10.dao.AuthUserDAO;
import com.example.lesson10.entity.AuthUser;
import com.example.lesson10.utils.PasswordUtils;
import com.example.lesson10.utils.StringUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet(
        name = "com.example.lesson10.servlet.auth.AuthRegisterServlet",
        value = "/auth/register"
)
public class AuthRegisterServlet extends HttpServlet {
    private final AuthUserDAO authUserDAO = new AuthUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/authuser/register.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirm_password = req.getParameter("confirm_password");
        Map<String, String> errors = new HashMap<>();
        if (!StringUtils.validEmail(email)) {
            errors.put("email_error", "Email is invalid");
        } else {
            authUserDAO.findByEmail(email)
                    .ifPresent((authUser -> {
                        errors.put("email_error", "Email already taken");
                    }));
        }
        if (email.isBlank()) {
            errors.put("email_error", "Email is null");
        }
        if (password.isBlank() || confirm_password.isBlank()) {
            errors.put("password_error", "Password is null");
        }
        if (!Objects.equals(password, confirm_password)) {
            errors.put("password_error", "Password is invalid");
        }
        if (!errors.isEmpty()) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/authuser/login.jsp");
            errors.forEach(req::setAttribute);
            requestDispatcher.forward(req, resp);
        }
        AuthUser authUser = AuthUser.childBuilder()
                .email(email)
                .password(PasswordUtils.encode(password))
                .role("USER")
                .status(AuthUser.Status.ACTIVE)
                .build();
        authUserDAO.save(authUser);
        resp.sendRedirect("/auth/login");
    }
}
