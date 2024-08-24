package com.example.lesson10.servlet;

import com.example.lesson10.dao.UploadDAO;
import com.example.lesson10.entity.Upload;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet(
        name = "com.example.lesson10.servlet.FileStorageDownloadServlet",
        urlPatterns = "/file/download/*"
)
public class FileStorageDownloadServlet extends HttpServlet {
    private static final Path rootPath = Path.of("C:\\Abbos\\Spring Project\\Jakarta EE\\Lesson10\\src\\main\\webapp\\upload\\library");
    private static final UploadDAO uploadDAO = new UploadDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String fileId = pathInfo.substring(1);
        Upload upload = uploadDAO.findById(fileId);
        Path path = rootPath.resolve(upload.getGenerated_name());
        byte[] bytes = Files.readAllBytes(path);
        resp.addHeader("Content-Type", upload.getMine_type());
        resp.addHeader("Content-Disposition", "attachment; filename="+upload.getOriginal_name());
        resp.getOutputStream().write(bytes);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
