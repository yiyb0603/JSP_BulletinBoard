<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Cookie[] cookies = request.getCookies();
    String cookieValue = null;

    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("cookie_study".equals(cookie.getName())) {
                cookieValue = cookie.getValue();
                break;
            }
        }
    }

%>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>쿠키 읽기</h1>
    <p>쿠키 값 : <%= cookieValue %></p>
</body>
</html>
