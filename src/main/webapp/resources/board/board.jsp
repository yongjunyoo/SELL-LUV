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

<style>
body {
    margin: 0;
    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, "Noto Sans", sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
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
    box-shadow: 0 0.46875rem 2.1875rem rgba(4, 9, 20, 0.03), 0 0.9375rem 1.40625rem rgba(4, 9, 20, 0.03), 0 0.25rem 0.53125rem rgba(4, 9, 20, 0.05), 0 0.125rem 0.1875rem rgba(4, 9, 20, 0.03);
    border-width: 0;
    transition: all .2s;
    margin:auto;
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
    box-shadow: 0 0.125rem 0.625rem rgba(63, 106, 216, 0.4), 0 0.0625rem 0.125rem rgba(63, 106, 216, 0.5);
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
	text-align:center; 
	height:100px; 
	line-height:100px;
	padding: 100px 0;
	
}
#title{
	width:50px;
	height:50px;
}
.search-bar {
	margin:auto;
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
    <%= (String)session.getAttribute("cpage") %>
        <%= request.getParameter("cpage") %>
    </div>       
    <br>
	
	<!-- 게시판 박스 -->
    <div class="card mb-3 col-xl-8 col-md-12">
    
      <!-- 분류 네비 -->  
        <div class="card-header pl-0 pr-0" style="justify-content: space-around;">
            <div class="row no-gutters w-100 align-items-center" style="justify-content: space-around">
                <div class="col-1 d-none d-md-block" style="text-align:center;" >111번호</div>
                <div class="col-7 d-none d-md-block" style="text-align:center;" >111제목</div>
                <div class="col-2 d-md-none" style="text-align:center;" >번호</div>
                <div class="col-9 d-md-none" style="text-align:center;" >제목</div>
                <div class="col-4 text-muted">
                    <div class="row no-gutters align-items-center">
                        <div class="d-none d-md-block col-4" style="text-align:center;">조회수</div>
                        <div class="d-none d-md-block col-8" style="text-align:center;">작성자/작성일</div>
                    </div>
                </div>
            </div>
        </div>
        
        <c:forEach var="dto" items="${boardList}">
        	
        	
        	<div class="card-body py-3 " style="justify-content: space-around;">
            	<div class="row no-gutters align-items-center" style="justify-content: space-around">
            	
	            	<%-- 웹버전 seq --%>
	                <div class="col-2 d-none d-md-block pl-3"> ${dto.seq } </div>
	                <%-- 웹버전 title --%>
	                <div class="col-6 d-none d-md-block"> <a href="/detail.board?cpage=${cpage }&seq=${dto.seq}" class="text-big" data-abc="true">${dto.title }</a>
	                    <div class="text-muted small mt-1 d-md-none">${dto.detailDate } &nbsp;·&nbsp; <a href="javascript:void(0)" class="text-muted" data-abc="true">by ${dto.writer }</a></div>
	                </div>
	               	<%-- 웹버전 조회수, 작성자, 날짜 --%>
	                <div class="d-none d-md-block col-4">
	                    <div class="row no-gutters align-items-center"  >
	                        <div class="col-4" style="text-align:center;">${dto.view_count }</div>
	                        <div class="media pl-4 col-8 align-items-center"> <img src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1574583246/AAA/2.jpg" alt="" class="d-block ui-w-30 rounded-circle">
	                            <div class="media-body flex-truncate ml-2">
	                                <div class="line-height-1 text-truncate">${dto.detailDate }</div> <a href="javascript:void(0)" class="text-muted small text-truncate" data-abc="true">by ${dto.writer }</a>
	                            </div>
	                       	</div>
	                    </div>
	                </div>
	                
	                <%-- 모바일버전 seq --%>
	                <div class="col-2 d-md-none pl-2"> ${dto.seq } </div>
	                <%-- 모바일버전 title,작성자,날짜 --%>
	                <div class="col-8 d-md-none pl-2"> <a href="/detail.board?cpage=${cpage }&seq=${dto.seq}" class="text-big" data-abc="true">${dto.title }</a>
	                    <div class="text-muted small mt-1 d-md-none">${dto.detailDate } &nbsp;·&nbsp; <a href="javascript:void(0)" class="text-muted" data-abc="true">by ${dto.writer }</a></div>
	                </div>
            	
	            </div>
	        </div>
	        
	        <hr class="m-0">
        </c:forEach>
     </div>   
        
        
        
        <%-- 
        <!-- 게시판 내용들 -->
        <div class="card-body py-3">
            <div class="row no-gutters align-items-center">
                <div class="col pl-3"> <a href="javascript:void(0)" class="text-big" data-abc="true">제목제목글제목</a>
                    <div class="text-muted small mt-1 d-md-none">2021.12.08 &nbsp;·&nbsp; <a href="javascript:void(0)" class="text-muted" data-abc="true">홍길동</a></div>
                </div>
                <div class="d-none d-md-block col-4">
                    <div class="row no-gutters align-items-center" >
                        <div class="col-4" style="text-align:center;">12</div>
                        <div class="media col-8 align-items-center"> <img src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1574583246/AAA/2.jpg" alt="" class="d-block ui-w-30 rounded-circle">
                            <div class="media-body flex-truncate ml-2">
                                <div class="line-height-1 text-truncate">1 day ago</div> <a href="javascript:void(0)" class="text-muted small text-truncate" data-abc="true">by Tim cook</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <hr class="m-0">
    	--%>
    
     <!-- 글쓰기 & 검색 라인 -->
    <div class="row search-bar" style="justify-content: space-around;">
    	<div class="col-xl-8 col-md-12 d-none d-md-block"> 
    		<div class="row">
		        <div class="col-6"><input type="text" placeholder="Search..." style="width:100%; height:100%;">
		        </div>
		        <div class="col-4">
		        <button type="button" class="btn btn-shadow btn-wide btn-primary" style="background-color:rgb(255, 111, 97); border-color:rgb(255, 111, 97);"> 
         		검색하기 </button>     
         		</div>
		    	<div class="col-2" style="text-align:right;"> 
		        <button type="button" class="btn-write btn btn-shadow btn-wide btn-primary" style="background-color:rgb(255, 111, 97); border-color:rgb(255, 111, 97);"> 
		         글 쓰기 </button> 
		        </div> 	
    		</div>
    	</div> 
        
        <div class="d-md-none">
        	<div class="row">
        		<div class="col-5">
        			<input type="text" placeholder="Search..." style="width:100%; height:100%;">
		        </div>
		        <div class="col-4">
		        	<button type="button" class="btn btn-shadow btn-wide btn-primary" style="background-color:rgb(255, 111, 97); border-color:rgb(255, 111, 97);"> 
         		검색하기 </button>     
         		</div>
         		<div class="col-3" style="text-align:right;"> 
	         		
	        		<button type="button" id="done" class="btn btn-shadow btn-wide btn-primary btn-write" style="background-color:rgb(255, 111, 97); border-color:rgb(255, 111, 97);"> 
	         		글쓰기 </button> 
	         		<script>
	         			$(".btn-write").on("click",function(){
	         				location.href="/write.board?cpage=${cpage}";
	         			})
	         		</script>
        		</div> 
        	</div>
        </div>
        
         
    </div>
    
    <br>
        ${navi }
    
    <%-- 
    <!-- 페이지 네비 -->
    <nav>
        <ul class="pagination mb-5 justify-content-center">
            <li class="page-item disabled"><a class="page-link" href="javascript:void(0)" data-abc="true">«</a></li>
            <li class="page-item active"><a class="page-link" href="javascript:void(0)" data-abc="true">1</a></li>
            <li class="page-item"><a class="page-link" href="javascript:void(0)" data-abc="true">2</a></li>
            <li class="page-item"><a class="page-link" href="javascript:void(0)" data-abc="true">3</a></li>
            <li class="page-item"><a class="page-link" href="javascript:void(0)" data-abc="true">»</a></li>
        </ul>
    </nav>
    --%>
    <!-- 푸터 -->
	<jsp:include page="/footer.jsp" flush="false"/>
</div> 
                           

</body>
</html>