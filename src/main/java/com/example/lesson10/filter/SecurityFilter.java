package com.example.lesson10.filter;

import com.example.lesson10.dao.AuthUserDAO;
import com.example.lesson10.entity.AuthUser;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@WebFilter(
        filterName = "com.example.lesson10.filter.SecurityFilter",
        value = "/*"
)
public class SecurityFilter implements Filter {
    private static final AuthUserDAO authUserDAO = new AuthUserDAO();
    private static final List<String> WHITE_LIST = List.of(
//            "/book/list",
            "/auth/login",
            "/auth/register"
//            ,
//            "/book/detail/.*",
//            "/storage/show/.*",
//            "/file/download/*"
    );
    private static final Predicate<String> isOpen = o -> {
        for (String s : WHITE_LIST) {
            if (o.matches(s)) {
                return true;
            }
        }
        return false;
    };
    private static final Predicate<String> isAdminPages = (uri) -> uri.startsWith("/admin");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        System.out.println("requestURI: " + requestURI);
        if (checkForRememberMe(request, response)) {
            filterChain.doFilter(request, response);
        } else {
            if (!isOpen.test(requestURI)) {
                HttpSession session = request.getSession();
                Object id = session.getAttribute("id");
                Object role = session.getAttribute("role");
                if (Objects.isNull(id)) {
                    response.sendRedirect("/auth/login?next=" + requestURI);
                } else {
                    if (Objects.equals("USER", role) && isAdminPages.test(requestURI)) {
                        response.sendError(403, "Permission denied");
                        System.out.println("requestURI" + isAdminPages.test(requestURI));
                    } else {
                        filterChain.doFilter(request, response);
                    }
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

    private boolean checkForRememberMe(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session1 = request.getSession();
        if (session1.getAttribute("id") != null) return true;
        for (Cookie cookie : request.getCookies()) {
            String cookieName = cookie.getName();
            if (cookieName.equals("rememberMe")) {
                AuthUser authUser = authUserDAO.findById(cookie.getValue());
                HttpSession session = request.getSession();
                session.setAttribute("email", authUser.getEmail());
                session.setAttribute("role", authUser.getRole());
                session.setAttribute("id", authUser.getId());
                return true;
            }
        }
        return false;
    }
}
