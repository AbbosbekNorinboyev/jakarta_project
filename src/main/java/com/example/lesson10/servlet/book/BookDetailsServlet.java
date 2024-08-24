package com.example.lesson10.servlet.book;

import com.example.lesson10.dao.BookDAO;
import com.example.lesson10.entity.Book;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
        name = "com.example.lesson10.servlet.book.BookDetailsServlet",
        urlPatterns = "/book/detail/*"
)
public class BookDetailsServlet extends HttpServlet {
    private static final BookDAO bookDAO = new BookDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String id = pathInfo.substring(1);
        Book book = bookDAO.findById(id);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/book/book_details.jsp");
        req.setAttribute("book", book);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
