<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    boolean authorized = false;
    String failedMessage = null;
    String job = request.getParameter("job");
    String userId = null;


    if ("login".equals(job)) {
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        if ("1234".equals(pw)) {
            Cookie cookie = new Cookie("dgsw_id", id);
            response.addCookie(cookie);

            response.sendRedirect("login.jsp");
        } else {
            failedMessage = "비밀번호가 올바르지 않습니다.";
        }
    }

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("dgsw_id".equals(cookie.getName())) {
                userId = cookie.getValue();
                authorized = true;
                break;
            }
        }
    } else if ("logout".equals(job)) {
        Cookie cookie = new Cookie("dgsw_id", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        response.sendRedirect("login.jsp");
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
<%
    if (!authorized)
    {
%>
    <div>
        <form method ="POST" action ="login.jsp">
            ID: <input type ="text" name ="id" placeholder ="아이디를 입력하세요." />
            비밀번호: <input type ="password" name ="pw" placeholder ="비밀번호를 입력하세요." />
            <input type ="hidden" name ="job" value ="login" />
            <button type ="submit">로그인</button>
        </form>
    </div>
<%
    } else {
%>
    <div>
        <form method ="POST" action ="login.jsp">
            <div class ="header">
                <form method ="POST" action ="login.jsp">
                    <p><%= userId %>님 환영합니다</p>
                    <input type ="hidden" value ="logout" name ="job" />
                    <input type ="submit" value ="로그아웃" name ="submit" />
                </form>
            </div>
        </form>
    </div>
<%
    }
%>
</body>
</html>
