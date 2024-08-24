<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Book JSP</title>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>
<jsp:include page="/fragments/navbar.jsp"/>
<form method="post" class="mt-5 mb-3 m-5">
    <div>
        <label for="title" class="form-label">Title</label>
        <input id="title" type="text" name="title" class="form-control" value="${book.title}"/>
    </div>
    <div class="mt-3">
        <button type="submit" class="btn btn-success">Update</button>
        <a href="/book/list" class="btn btn-danger">Back</a>
    </div>
</form>
<jsp:include page="/fragments/js.jsp"/>
</body>
</html>
