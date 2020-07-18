<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Cookie cookie = new Cookie("cookie_study", "2학년2반");
    cookie.setMaxAge(10000);
    response.addCookie(cookie);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>쿠키 쓰기</p>


</body>
</html>
