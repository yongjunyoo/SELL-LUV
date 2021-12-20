<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   	 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 게시판</title>
	<link
		href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
		rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://rawgit.com/jackmoore/autosize/master/dist/autosize.min.js"></script>
<link rel="icon" href="#">
<style>
body {
	margin: 0;
	font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
		"Helvetica Neue", Arial, "Noto Sans", sans-serif, "Apple Color Emoji",
		"Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
	font-size: .88rem;
	font-weight: 400;
	line-height: 1.5;
	color: #495057;
	background-color: #eef1f3;
}

.mt-100 {
	margin-top: 80px;
}

.card {
	box-shadow: 0 0.46875rem 2.1875rem rgba(4, 9, 20, 0.03), 0 0.9375rem
		1.40625rem rgba(4, 9, 20, 0.03), 0 0.25rem 0.53125rem
		rgba(4, 9, 20, 0.05), 0 0.125rem 0.1875rem rgba(4, 9, 20, 0.03);
	border-width: 0;
	transition: all .2s;
	margin: auto;
}

.card-header:first-child {
	border-radius: calc(.25rem - 1px) calc(.25rem - 1px) 0 0
}

.card-header {
	display: flex;
	align-items: center;
	border-bottom-width: 1px;
	padding-top: 0;
	padding-bottom: 0;
	padding-right: .625rem;
	height: 3.5rem;
	background-color: #fff;
	border-bottom: 1px solid rgba(26, 54, 126, 0.125);
}

.btn-primary.btn-shadow {
	box-shadow: 0 0.125rem 0.625rem rgba(63, 106, 216, 0.4), 0 0.0625rem
		0.125rem rgba(63, 106, 216, 0.5);
}

.btn.btn-wide {
	padding: .375rem 1.5rem;
	font-size: .8rem;
	line-height: 1.5;
	border-radius: .25rem;
}

.btn-primary {
	color: #fff;
	background-color: #3f6ad8;
	border-color: #3f6ad8;
}

.form-control {
	display: block;
	width: 100%;
	height: calc(2.25rem + 2px);
	padding: .375rem .75rem;
	font-size: 1rem;
	font-weight: 400;
	line-height: 1.5;
	color: #495057;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid #ced4da;
	border-radius: .25rem;
	transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

.card-body {
	flex: 1 1 auto;
	padding: 1.25rem
}

.flex-truncate {
	min-width: 0 !important
}

.d-block {
	display: block !important
}

a {
	color: #E91E63;
	text-decoration: none !important;
	background-color: transparent
}

.media img {
	width: 40px;
	height: auto
}

#board-title {
	text-align: center;
	height: 100px;
	line-height: 100px;
	padding: 100px 0;
}

#title {
	width: 50px;
	height: 50px;
}

.search-bar {
	margin: auto;
}

* {
	box-sizing: border-box;
}

.container {
	width: 1000px;
}

textarea {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	width: 100%;
}

body {
	margin: 0;
	line-height: 1.5;
	color: #495057;
	text-align: left;
	background-color: #eef1f3
}
.profile-box{
	display:flex;
}
.img-profile {
	border-radius: 70%;
	width: 100%;
	height: 100%;
}

.profile-detail {
	text-align: center;
}

.name {
	font-weight: bold;
}

.list li {
	margin-top: 5px;
}

.label {
	font-size: 12px;
}
.profile-detail{
	justify-content: flex-start;
    flex-direction: column;
}
</style>
</head>
<body>
	<!-- 메인 네비바 -->
	<jsp:include page="/header.jsp" flush="false"/>
	
	<!-- 타이틀  -->
	<div class="container-fluid mt-100">
    <div id="board-title">
    <img id="title" src="/resources/board/image/title.png">
    <span>커뮤니티 게시판</span>
    </div>       
    <br>
	
	<!-- 게시판 박스 -->
    <div class="card mb-3 col-xl-6 col-md-12">
    <!-- 게시글 등록 박스 -->
      <form action="/done.board" method="post" id="frm">
    <div class="container mb-4 mt-4">
        <div class="row" style="padding-bottom:5px;">
            <div class="col-sm-12"><input type=text id=input-title name=title placeholder="제목을 작성하세요" style="width:100%;"></div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <textarea name="contents" id="contents" placeholder="내용을 입력하세요" style="min-height: 200px;overflow:hidden"></textarea>
                <script>
                autosize($("textArea"));
                </script>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12" style="text-align:right">
                <button type="button" id="boardList" class="btn btn-dark" style="background-color:rgb(255, 111, 97);">목록으로</button>
                <button class="btn btn-dark" type=button id="write" style="background-color:rgb(255, 111, 97);">작성하기</button>
                <script>
					$("#boardList").on("click",function(){
						location.href="/boardList.board?cpage=1";
					})
					$("#write").on("click",function(){
						console.log($("#contents").val());
						if($("#contents").val()==""){
							alert("내용을 입력해주세요.");
						}else if($("#input-title").val()==""){
							alert("제목을 입력해주세요.");
						}else {
							$("#frm").submit();					
						}
					})
				</script>
            </div>
        </div>
    </div>	
    </form>    
        
        
        
        
    </div>   
    
    <br>
    
    <!-- 푸터 -->
	<jsp:include page="/footer.jsp" flush="false"/>
</div>                            
<script type="text/javascript">
</script>
</body>
</html>