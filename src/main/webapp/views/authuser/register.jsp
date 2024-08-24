<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register JSP</title>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 offset-3">
            <h1>Register</h1>
            <form action="/auth/register" method="post">
                <div class="mb-3">
                    <label for="p_email" class="form-label">Email Address</label>
                    <input id="p_email" type="email" name="email" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="p_password" class="form-label">Password</label>
                    <input id="p_password" type="password" name="password" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="p2_password" class="form-label">Confirm Password</label>
                    <input id="p2_password" type="password" name="confirm_password" class="form-control">
                </div>
                <button type="submit" class="btn btn-primary">Register</button>
                <a href="/auth/login" class="btn btn-warning">Login</a>
            </form>
        </div>
    </div>
</div>
<jsp:include page="/fragments/js.jsp"/>
</body>
</html>
