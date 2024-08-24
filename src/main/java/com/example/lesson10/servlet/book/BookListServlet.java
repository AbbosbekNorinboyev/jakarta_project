package com.example.lesson10.servlet.book;

import com.example.lesson10.dao.AuthUserDAO;
import com.example.lesson10.dao.BookDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(
        name = "com.example.lesson10.servlet.BookListServlet",
        value = "/book/list"
)
public class BookListServlet extends HttpServlet {
    private final BookDAO bookDAO = new BookDAO();
    private final AuthUserDAO authUserDAO = new AuthUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = null;
        String id = null;
        for (Cookie cookie : req.getCookies()) {
            name = cookie.getName();
            String cookieName = name;
            id = Arrays.stream(req.getCookies())
                    .filter(c -> c.getName().equals(cookieName))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/book/book_list.jsp");
        req.setAttribute("books", bookDAO.findAll());
        req.setAttribute("authuser", authUserDAO.findById(id));
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(405);
    }
}
