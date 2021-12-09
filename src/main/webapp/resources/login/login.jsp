<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Insert title here</title>
<script
    src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
   <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<link rel="stylesheet" href="css/style.css">
   
    
   
</head>
<body>
<jsp:include page='header.jsp' flush='false'/>
<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
			</div>
			<div class="row justify-content-center">
				<div class="col-md-6 col-lg-5">
					<div class="login-wrap p-4 p-md-5">
		      	<div class="icon d-flex align-items-center justify-content-center">
		      		<span class="fa fa-user-o"></span>
		      	</div>
		      	<h3 class="text-center mb-4">아이디가 있습니까?</h3>
						<form action="#" class="login-form">
		      		<div class="form-group">
		      			<input type="text" class="form-control rounded-left" placeholder="아이디" required>
		      		</div>
	            <div class="form-group d-flex">
	              <input type="password" class="form-control rounded-left" placeholder="비밀번호" required>
	            </div>
	            <div class="form-group d-md-flex">
	            	<div class="w-50">
	            	
								</div>
								<div class="w-50 text-md-right">
									<a href="#">비밀번호 찾기</a>
								</div>
	            </div>
	            <div class="form-group">
	            	<button type="submit" class="btn btn-primary rounded submit p-3 px-5">아이디 생성</button>
	            </div>
	          </form>
	        </div>
				</div>
			</div>
		</div>
	</section>

	<script src="js/jquery.min.js"></script>
  <script src="js/popper.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/main.js"></script>
</body>
</html>