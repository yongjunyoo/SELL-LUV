<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap" rel="stylesheet">
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Home</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://kit.fontawesome.com/5c87e9ee71.js" crossorigin="anonymous"></script>

	<!-- SEO Meta Tags -->
    <meta name="description" content="Your description">
    <meta name="author" content="Your name">
    
    <!-- OG Meta Tags to improve the way the post looks when you share the page on Facebook, Twitter, LinkedIn -->
	<meta property="og:site_name" content="" /> <!-- website name -->
	<meta property="og:site" content="" /> <!-- website link -->
	<meta property="og:title" content=""/> <!-- title shown in the actual shared post -->
	<meta property="og:description" content="" /> <!-- description shown in the actual shared post -->
	<meta property="og:image" content="" /> <!-- image link, make sure it's jpg -->
	<meta property="og:url" content="" /> <!-- where do you want your post to link to -->
	<meta name="twitter:card" content="summary_large_image"> <!-- to have large image post format in Twitter -->

    <!-- Styles -->
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,400;0,600;0,700;1,400&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/mainpage/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/mainpage/css/fontawesome-all.min.css"> 
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/mainpage/css/swiper.css"> 
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/mainpage/css/styles.css"> 
	
</head>
<body>
 <!-- Footer -->
<footer class="text-center text-lg-start bg-light text-muted">
  <!-- Section: Social media -->
  <section
    class="d-flex justify-content-center justify-content-lg-between p-4 border-bottom">
    <!-- Right -->
  </section>
  <!-- Section: Social media -->
  <!-- Section: Links  -->
  <section class="">
    <div class="container text-center text-md-start mt-5">
      <!-- Grid row -->
      <div class="row mt-3">
        <!-- Grid column -->
        <div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">
          <!-- Content -->
          <h6 class="text-uppercase fw-bold mb-4">
            <i class="fas fa-gem me-3"></i>SELL LUV
          </h6>
            안녕하세요 !<br>
			우리는 SELL-LUV!<br>
			사이트를 찾아주셔서 감사합니다!<br>
			더 나은 서비스로 보답하겠습니다!
        </div>
        <!-- Grid column -->

        <!-- Grid column -->
        <div class="col-md-3 col-lg-4 col-xl-2 mx-auto mb-4">
          <!-- Links -->
          <h6 class="text-uppercase fw-bold mb-4">
            빠른 실행
          </h6>
          <p>
            <a href="/companyList.ifcp?cpage=1" class="text-reset" style="text-decoration:none;">기업</a>
          </p>
          <p>
            <a href="/influencerList.ifcp?cpage=1" class="text-reset" style="text-decoration:none;">인플루언서</a>
          </p>
          <p>
            <a class="text-reset" id=freeboard-quick style="text-decoration:none;cursor:pointer">커뮤니티</a>
            <script>
			 $("#freeboard-quick").on("click",function(){
			 	if("${loginID}"==""){
			 		if(confirm("로그인이 필요한 서비스입니다. \r\n로그인 하시겠습니까?")){
			 			location.href="/login.mem";
			 		}	
			 	}else{
			 		location.href="/boardList.board?cpage=1";
			 	}
			 });
			 </script>
          </p>
          <p>
            <a href="/msearch.search" class="text-reset" style="text-decoration:none;">검색</a>
          </p>
        </div>
        <!-- Grid column -->
        <!-- Grid column -->
        <div class="col-md-4 col-lg-4 col-xl-4 mx-auto mb-md-0 mb-4">
          <!-- Links -->
          <h6 class="text-uppercase fw-bold mb-4">
            연락처
          </h6>
          <p>
          <i class="fas fa-home me-3"></i>서울 중구 남대문로 120 대일빌딩 2층, 3층
          </p>
          <p>
            <i class="fas fa-envelope me-3"></i>
            example@gmail.com
          </p>
          <p><i class="fas fa-phone me-3"></i> 010 - 1234 - 5716</p>
          <p><i class="fas fa-print me-3"></i> 02 - 123 - 1234</p>
        </div>
        <!-- Grid column -->
      </div>
      <!-- Grid row -->
    </div>
  </section>
  <!-- Section: Links  -->

  <!-- Copyright -->
  <div class="text-center p-4" style="background-color: rgba(255, 111, 97);color:white">
    Copyright 2020. KKANBU All rights reserved.
  </div>
  <!-- Copyright -->
</footer>
<!-- Footer -->
    <!-- end of footer -->
 
    
</body>
</html>