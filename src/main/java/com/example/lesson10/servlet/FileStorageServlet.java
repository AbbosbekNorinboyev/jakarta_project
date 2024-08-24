package com.example.lesson10.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet(
        name = "com.example.lesson10.servlet.FileStorageServlet",
        urlPatterns = "/storage/show/*"
)
public class FileStorageServlet extends HttpServlet {
    private static final Path rootPath = Path.of("C:\\Abbos\\Spring Project\\Jakarta EE\\Lesson10\\src\\main\\webapp\\upload\\library");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String fileName = pathInfo.substring(1);
        Path path = rootPath.resolve(fileName);
        byte[] bytes = Files.readAllBytes(path);
        resp.getOutputStream().write(bytes);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
