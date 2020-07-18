<%@ page import="java.util.Date" %>
<%@ page language="java"
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%
	int sequence = Integer.parseInt(request.getParameter("sequence"));
	String writerId = null;

	Cookie[] cookies = request.getCookies();
	for (Cookie cookie : cookies) {
		if ("dgswId".equals(cookie.getName())) {
			writerId = cookie.getValue();
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>

<style>
	label {
		display: inline-block;
		width: 150px;
	}
	
	div {
		padding: 5px;
	}
	

</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	const date = new Date();
	const update = document.getElementById("update");
	const length = $("#hitLength");
	let responseData = null;
	let responseHit = null;

	$(document).ready(function() {
		readWriting();
		readHit();
	});

	const readWriting = () => {
		$.get("read.dgsw", {sequence: <%= sequence %>},
				function(response) {
			responseData = response;
			showWriting(response);
		});
	}

	const readHit = () => {
		$("#list_body").empty();
		$.get("hit.dgsw", { sequence: <%= sequence %> }, (response) => {
			responseHit = response;

			for (let i in responseHit) {
				showHits(responseHit[i]);
			}
		});
		$("#hitLength").html(responseHit.length);
	}

	const onClickUpdate = () => {
		if (responseData.writer === "<%= writerId %>") {
			location.href ="modify.jsp?sequence=" + responseData.sequence;
		} else {
			alert("당신의 글이 아니므로 수정 못합니다.");
		}
	}
	
	const showWriting = (writing) => {
		$("#title").html(writing.title);
		$("#content").html(writing.content);
		$("#writer").html(writing.writer);
		$("#hitLength").html(writing.hit);
	}

	const showHits = (writing) => {
		console.log(writing);
		var tr = $("<tr />");
		var tdWriter = $("<td />");
		var tdHitTime = $("<td />");

		if (writing === null) {
			tdWriter.html("<span>댓글이 없습니다.</span>");
			tdHitTime.html("<span>댓글이 없습니다.</span>");
		} else {
			tdWriter.html(writing.writer);
			tdHitTime.html(writing.hitTime);
		}

		tr.append(tdWriter);
		tr.append(tdHitTime);

		$("#list_body").append(tr);
	}

	const increaseHit = () => {
		let count = 0;
		const data = {
			"bulletin_id": <%= sequence %>,
			"writer_id": "<%= writerId %>"
		}

		if ("<%= writerId %>" === "null") {
			alert("로그인 후 가능합니다.");
			location.href ="login2.jsp";
			return;
		}

		for (let i = 0; i < responseHit.length; i++) {
			if (responseHit[i].writer === "<%= writerId %>") {
				alert("좋아요를 두번이상 할 수 없습니다.");
				return;
			}
		}

		$.post("hit.dgsw", data, () => {
			alert("좋아요 성공.");
			location.reload();
		});
	}

	const clickDelete = () => {
		if (responseData.writer === "<%= writerId %>"){
			const data = {
				"id": <%= sequence %>
			};
			$.post("delete.dgsw", data, () => {
				alert("글 삭제 성공.");
				location.href = "list.jsp";
			});
		} else {
			alert("당신의 글이 아니므로 삭제할 수 없습니다");
		}
	};
</script>

</head>
<body>

	<h1>게시판 - 글읽기</h1>

	<div>
		<label>제목</label>
		<span id="title">안녕하세요</span>
	</div>

	<div>
		<label>내용</label>
		<textarea id="content" readonly>반갑습니다.</textarea>
	</div>

	<div>
		<label>글쓴이</label>
		<span id="writer">홍길동</span>
	</div>

	<div>
		<label>좋아요 개수</label>
		<span id ="hitLength"></span>
	</div>

	<table>
		<thead>
			<tr>
				<th>좋아요한 사람</th>
				<th>좋아요한 시간</th>
			</tr>
		</thead>
		<tbody id ="list_body">
			<tr>
				<td></td>
			</tr>
		</tbody>
	</table>

	<button onclick ="increaseHit();">좋아요</button>

	<section id ="updateDiv">
		<button id ="update" onclick="onClickUpdate();">수정</button>
		<button onclick ="clickDelete();">삭제</button>
	</section>
</body>
</html>