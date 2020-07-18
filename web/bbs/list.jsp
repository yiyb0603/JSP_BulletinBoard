<%--
  Created by IntelliJ IDEA.
  User: lllllllllllllllllll
  Date: 7/10/2020
  Time: 7:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int count = 0;
    boolean isCanWrite = true;
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
        if (!("dgswId".equals(cookie.getName()))) {
            ++count;
            if (count == cookies.length) {
                isCanWrite = false;
            }
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판</title>

    <style type="text/css">

        td, th {
            border: 1px solid #CCC;
            padding: 3px;
        }

    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>

        $(document).ready(() => {
            readList((list) => {
                showList(list);
            });
        });

        function readList(callback)
        {
            $.get("list.dgsw", null, function(response) {
                console.log(response);
                callback(response);
            });
        }

        function showList(list)
        {
            $("#list_body").empty();

            for (var i in list)
            {
                showLine(list[i]);
            }
        }

        function increaseView(sequence) {
            const data = {
                sequence
            };

            $.post("list.dgsw", data, () => {
                alert("조회수 증가 성공.");
            })
        }

        function showLine(data)
        {
            var tr = $("<tr />");
            var tdSequence = $("<td />");
            var tdTitle = $("<td />");
            var tdWriter = $("<td />");

            var aTag = $("<a />");
            aTag.html(data.title);
            aTag.prop("href", "read.jsp?sequence=" + data.sequence);

            tdTitle.append(aTag);
            tdWriter.html(data.writer);
            tdSequence.html(data.sequence);

            tr.append(tdSequence);
            tr.append(tdTitle);
            tr.append(tdWriter);

            $("#list_body").append(tr);
        }

        const clickWrite = () => {
            if (!<%= isCanWrite %>) {
                alert("로그인 후 이용가능 합니다.");
                location.href ="login2.jsp";
            } else {
                location.href ="write.jsp";
            }
        }
    </script>

</head>
<body>
<h1>게시판</h1>

<div style="text-align: right;">
    <button onclick ="clickWrite();">글쓰기</button>
    <button id ="loginButton" onclick ="location.href ='login2.jsp';">로그인/로그아웃</button>
</div>

<table style="border-collapse: collapse;">
    <thead>
    <tr>
        <th>글번호</th>
        <th style="width: 500px;">제목</th>
        <th style="width: 200px;">글쓴이</th>
    </tr>
    </thead>

    <tbody id="list_body">
    <tr>
        <td></td>
    </tr>
    </tbody>
</table>
</body>
</html>