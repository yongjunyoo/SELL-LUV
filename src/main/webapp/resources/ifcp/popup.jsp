<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Select</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<form name=frm id=frm method=post action="">
깐부를 맺을 제품을 선택해주세요.
<select name="select" id="select">
<c:forEach var="item" items="${result }">
	<option>${item }</option>
	
</c:forEach>
</select>
<button id=btn type=button>선택</button>
</form>
<script>
$("#btn").on("click",function(){
	let select = $("#select").val();
	$.ajax({
		url:"/select.kkanbu",
		data:{
				select:select
		}
	}).done(function(){
		window.opener.location.href="/kkanbuRequestToInfluencer.kkanbu?kkanbuSeqTo=${dto.key.member_seq }&kkanbuSeqFrom=${IDseq}&kkanbuCardSeq=${dto.key.seq_cp}&cpage=1";
		window.close();
	})
})
</script>
</body>
</html>