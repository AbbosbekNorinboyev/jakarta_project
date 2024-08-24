<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book List</title>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>
<jsp:include page="/fragments/navbar.jsp"/>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-10 offset-1">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Title</th>
                    <th scope="col">Type</th>
                    <th scope="col">Size</th>
                    <c:if test="${authuser.role == 'ADMIN'}">
                        <th scope="col">Actions</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${books}" var="book">
                    <tr>
                        <td>${book.id}</td>
                        <td>
                            <a href="/book/detail/${book.id}">${book.title}</a>
                        </td>
                        <td>${book.file.extension}</td>
                        <td>${book.file.size / 1024 / 1024} MB</td>
                        <td>
                            <c:if test="${authuser.role == 'ADMIN'}">
                                <a href="/admin/book/delete/${book.id}" class="btn btn-danger">Delete</a>
                                <a href="/admin/book/update/${book.id}" class="btn btn-warning">Update</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="/fragments/js.jsp"/>
</body>
</html>
