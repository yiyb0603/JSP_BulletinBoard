<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    session.setAttribute("name", "권용빈");
    session.setAttribute("now", new Date());

    String name = (String) session.getAttribute("name");

    Date now = (Date) session.getAttribute("now");
    session.removeAttribute("name");
%>

<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
