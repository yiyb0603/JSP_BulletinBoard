<%
    String sequence = request.getParameter("sequence");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src ="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <script>
        const save = () => {
            const title = $("input[name='title']").val();
            const content = $("textarea[name='content']").val();
            const sequence = <%= sequence %>;

            if (title === "" || content === "") {
                alert("내용을 작성 해주세요.");
                return;
            }

            const data = {
                "title": title,
                "content": content,
                "sequence": sequence
            };

            $.post("modify.dgsw", data, () => {
                alert("수정 되었습니다.");
                location.href ="list.jsp";
            });
        }
    </script>
</head>
<body>
    <h1>글 수정하기</h1>

    <div>
        <label>제목</label>
        <input type ="text" name ="title" placeholder ="제목을 입력하세요." />
    </div>

    <div>
        <label>내용</label>
        <textarea type ="text" name ="content" placeholder="내용을 입력하세요."></textarea>
    </div>

    <button onclick="save();">수정</button>
</body>
</html>
