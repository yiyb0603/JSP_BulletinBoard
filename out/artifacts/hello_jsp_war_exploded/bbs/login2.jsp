<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    boolean authorized = false;
    String job = request.getParameter("job");
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");
    String invalidMessage = null;

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("dgswId".equals(cookie.getName())) {
                authorized = true;
                break;
            }
        }
    }

    if ("login".equals(job)) {
        if ("null".equals(id) || "".equals(id.trim()) || "".equals(id)) {
            invalidMessage = "아이디를 올바르게 입력해주세요.";
        } else {
            if ("1234".equals(pw)) {
                Cookie cookie = new Cookie("dgswId", id);
                response.addCookie(cookie);

                response.sendRedirect("list.jsp");
            } else {
                invalidMessage = "비밀번호가 틀렸습니다.";
            }
        }
    } else if ("logout".equals(job)) {
        Cookie cookie = new Cookie("dgswId", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        response.sendRedirect("login2.jsp");
    }
%>
<html>
<head>
    <title>Title</title>

    <style>
        .header {
            width: 98%;
            padding: 10px;
            border: 1px solid #ccc;
        }
    </style>
</head>
<body>
<div>
    <%
        if (!authorized) {
    %>
    <form method ="POST" action ="login2.jsp">
        ID: <input type ="text" name ="id" placeholder ="아이디를 입력하세요." />
        비밀번호: <input type ="password" name ="pw" placeholder ="비밀번호를 입력하세요." />
        <input type ="hidden" name ="job" value ="login" />
        <button type ="submit">로그인</button>
    </form>
    <button onclick="location.href ='list.jsp'">로그인 없이 이용하기</button>

    <%
        } else {
    %>
        <form method ="POST" action ="login2.jsp">
            <input type ="hidden" name ="job" value ="logout" />
            <span>로그아웃하기: </span>
            <button type ="submit">로그아웃</button>
        </form>
    <%
        }
    %>

    <%
        if (invalidMessage != null) {
    %>
        <%= invalidMessage %>

    <%
        }
    %>
</div>
</body>
</html>
