package com.example.lesson10.servlet.admin;

import com.example.lesson10.dao.BookDAO;
import com.example.lesson10.dao.UploadDAO;
import com.example.lesson10.entity.Book;
import com.example.lesson10.entity.Upload;
import com.example.lesson10.utils.StringUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@WebServlet(
        name = "com.example.lesson10.servlet.admin.AdminBookCreateServlet",
        value = "/admin/book/create"
)
@MultipartConfig
public class AdminBookCreateServlet extends HttpServlet {
    private static final Path rootPath = Path.of("C:\\Abbos\\Spring Project\\Jakarta EE\\Lesson10\\src\\main\\webapp\\upload\\library");
    private static final BookDAO bookDAO = new BookDAO();
    private static final UploadDAO uploadDAO = new UploadDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/create_book.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Part file = req.getPart("file");
        String originalName = file.getSubmittedFileName();
        String extension = StringUtils.fileExtension(originalName);
        Upload upload = Upload.childBuilder()
                .generated_name(UUID.randomUUID() + "." + extension)
                .original_name(originalName)
                .extension(extension)
                .size(file.getSize())
                .mine_type(file.getContentType())
                .build();
        uploadDAO.save(upload);
        Book buildBook = Book.childBuilder()
                .title(title)
                .description(description)
                .file(upload)
                .build();
        bookDAO.save(buildBook);
        Files.copy(file.getInputStream(), rootPath.resolve(upload.getGenerated_name()), StandardCopyOption.REPLACE_EXISTING);
        resp.sendRedirect("/book/list");
    }
}
