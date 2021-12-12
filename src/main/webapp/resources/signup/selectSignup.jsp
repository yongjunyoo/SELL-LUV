<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   	 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <!-- Basic -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">   
   
    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
 
     <!-- Site Metas -->
    <title>회원가입 페이지</title>  
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Site CSS -->
    <link rel="stylesheet" href="style.css">
    <!-- Responsive CSS -->
    <link rel="stylesheet" href="css/responsive.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/custom.css">
	<script src="js/modernizr.js"></script> <!-- Modernizr -->


</head>
<body id="page-top" class="politics_version">   
	
	<jsp:include page="/header.jsp" flush="false"/>
	
	<section id="home" class="main-banner parallaxie" style="background: url('uploads/banner-01.jpg')">
		<div class="heading">
			<h1>SELL LUV 에 오신걸 환영합니다</h1>			
			<h3 class="cd-headline clip is-full-width">
				<span>기업과 인플루언서의 연결고리</span>
				<span class="cd-words-wrapper">
					<b class="is-visible">제품 홍보</b>
					<b>홍보 모델</b>
					
				</span>
				<div class="btn-ber">
					<a class="get_btn hvr-bounce-to-top" href="/resources/signup/CPSignup.jsp">기업 회원가입</a>
					<div style="padding-bottom:10px;"></div>
					<a class="get_btn hvr-bounce-to-top" href="/resources/signup/IFSignup.jsp">인플루언서 회원가입</a>
				</div>
			</h3>
		</div>
	</section>

   <jsp:include page="/footer.jsp" flush="false"/>

   

    <!-- ALL JS FILES -->
    <script src="js/all.js"></script>
	<script src="js/headline.js"></script>
	<script src="js/jquery.vide.js"></script>

</body>
</html>