<%--
  Created by IntelliJ IDEA.
  User: lllllllllllllllllll
  Date: 7/9/2020
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String writer = (String) session.getAttribute("dgsw_id1");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>글쓰기</title>

    <style>
        div {
            padding: 5px;
        }

        label {
            width: 80px;
            display: inline-block;
        }
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        const save = () => {
            const title = $("input[name='title']").val();
            const content = $("textarea[name='content']").val();

            if (title === "" || content === "") {
                alert("내용을 작성 해주세요.");
                return;
            }

            const params = {
                "title": title,
                "content": content
            };

            $.post("write.dgsw", params, () => {
                alert("글 작성 성공.")
                location.href ="list.jsp";
            });
        };
    </script>
</head>
<body>
<h1>게시판 - 글쓰기</h1>

<div>
    <label>제목</label>
    <input type ="text" name ="title" placeholder ="제목을 입력하세요." />
</div>

<div>
    <label>내용</label>
    <textarea name ="content" placeholder ="내용을 입력하세요."></textarea>
</div>

<div>
    <label></label>
    <button onclick="save();">저장</button>
</div>
</body>
</html>
