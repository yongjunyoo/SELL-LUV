<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   	 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${dto.title }</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap" rel="stylesheet">

</head>
 <style>
         *{box-sizing:border-box;}        
        .container{width:1000px;}
        textarea {
            resize: none;
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
    </style>
<body>
	<jsp:include page="/header.jsp" flush="false"/>
	
	<form action="/done.board" method="post" >
    <div class="container">
        <div class="row">
            <div class="col-sm-12" style="text-align:center; height:200px; line-height:250px; font-family:BlackHanSans;"></div>
        </div>
        <div class="row" style="padding-bottom:5px;">
            <div class="col-sm-12"><input type=text id=title name=title style="width:100%;" value=${dto.title} readonly></div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <textarea id="contents" cols=170 rows=25 name="contents" readonly></textarea>
                <script>
                	$("#contents").text("${dto.contents}");
                </script>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12" style="text-align:right">
                <button type="button" id="boardList" class="btn btn-dark" style="background-color:rgb(255, 111, 97);">목록으로</button>
                <button class="btn btn-dark" id="write" style="background-color:rgb(255, 111, 97);">작성하기</button>
                <script>
					$("#boardList").on("click",function(){
						location.href="/boardList.board?cpage=1";
					})
				</script>
            </div>
        </div>
    </div>	
    
    <!-- 푸터 -->
	<jsp:include page="/footer.jsp" flush="false"/>
</form>

</body>
</html>