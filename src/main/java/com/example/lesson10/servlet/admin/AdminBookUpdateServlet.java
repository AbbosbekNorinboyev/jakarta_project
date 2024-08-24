package com.example.lesson10.servlet.admin;

import com.example.lesson10.dao.BookDAO;
import com.example.lesson10.entity.Book;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(
        name = "com.example.lesson10.servlet.admin.AdminBookUpdateServlet",
        urlPatterns = "/admin/book/update/*"
)
public class AdminBookUpdateServlet extends HttpServlet {
    private static final BookDAO bookDAO = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String id = pathInfo.substring(1);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/update_book.jsp");
        req.setAttribute("book", bookDAO.findById(id));
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String id = pathInfo.substring(1);
        Book book = bookDAO.findById(id);
        String title = req.getParameter("title");
        if (Objects.isNull(book)) {
            resp.sendError(404, "Book '%s' not found".formatted(id));
        } else {
            book.setTitle(Objects.requireNonNullElse(title, book.getTitle()));
            bookDAO.update(book);
        }
        resp.sendRedirect("/book/list");
    }
}
