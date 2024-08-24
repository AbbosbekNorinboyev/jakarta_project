<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Book JSP</title>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>
<jsp:include page="/fragments/navbar.jsp"/>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 offset-3">
            <h1>Create Book</h1>
            <form action="/admin/book/create" method="post" enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="title" class="form-label">Title</label>
                    <input id="title" type="text" name="title" class="form-control"/>
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">Description</label>
                    <textarea id="description" name="description" cols="30" rows="5" class="form-control"></textarea>
                </div>
                <div class="mb-3">
                    <label for="file" class="form-label">File</label>
                    <input id="file" type="file" name="file" class="form-control"/>
                </div>
                <div>
                    <button type="submit" class="btn btn-primary">Save</button>
                    <a href="/book/list" class="btn btn-danger">Back</a>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="/fragments/js.jsp"/>
</body>
</html>
