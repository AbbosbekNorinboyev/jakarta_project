<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Login JSP</title>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6">
            <h1>Login</h1>
            <c:if test="${error_message != null}">
                <div class="alert alert-danger">${error_message}</div>
            </c:if>
            <form action="/auth/login?next=${next}" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input id="email" type="email" class="form-control" name="email">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input id="password" type="password" class="form-control" name="password">
                </div>
                <div class="mb-3 form-check">
                    <label for="rememberMe" class="form-check-label">Remember me</label>
                    <input id="rememberMe" type="checkbox" class="form-check-input" name="rememberMe">
                </div>
                <button type="submit" class="btn btn-success">Login</button>
            </form>
        </div>
        <div class="col-md-6">
            <h1>Register</h1>
            <form action="/auth/register" method="post">
                <div class="mb-3">
                    <label for="p_email" class="form-label">Email Address</label>
                    <input id="p_email" type="email" name="email" class="form-control">
                    <c:if test="${email_error != null}">
                        <span class="text-danger">${email_error}</span>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label for="p_password" class="form-label">Password</label>
                    <input id="p_password" type="password" name="password" class="form-control">
                    <c:if test="${password_error != null}">
                        <span class="text-danger">${password_error}</span>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label for="p2_password" class="form-label">Confirm Password</label>
                    <input id="p2_password" type="password" name="confirm_password" class="form-control">
                    <c:if test="${password_error != null}">
                        <span class="text-danger">${password_error}</span>
                    </c:if>
                </div>
                <button type="submit" class="btn btn-primary">Register</button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="/fragments/js.jsp"/>
</body>
</html>
