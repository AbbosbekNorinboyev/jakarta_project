<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Details JSP</title>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>
<jsp:include page="/fragments/navbar.jsp"/>
<div class="container m-0 p-2">
    <div class="row">
        <div class="col-md-4">
            <p><b>Id: </b> ${book.id}</p>
            <h2>Title: <i>${book.title}</i></h2>
            <h2>Type: ${book.file.extension}</h2>
            <p><b>Size: </b> ${book.file.size / 1024 / 1024} MB</p>
            <p><b>Description: </b> ${book.description}</p>
            <a href="/file/download/${book.file.id}" class="btn btn-success">Download</a>
        </div>
        <div class="col-md-8">
            <iframe src="/storage/show/${book.file.generated_name}" width="1000px" height="1000px">

            </iframe>
        </div>
    </div>
</div>
<jsp:include page="/fragments/js.jsp"/>
</body>
</html>
