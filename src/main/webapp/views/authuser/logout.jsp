<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout JSP</title>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 offset-3">
            <h1>Logout</h1>
            <form action="/auth/logout" method="post">
                <div class="alert alert-danger mb-3">
                    <h1>Are you sure sign out ?</h1>
                </div>
                <button type="submit" class="btn btn-danger">Yes</button>
                <a href="/book/list" class="btn btn-warning">Back</a>
            </form>
        </div>
    </div>
</div>
<jsp:include page="/fragments/js.jsp"/>
</body>
</html>
