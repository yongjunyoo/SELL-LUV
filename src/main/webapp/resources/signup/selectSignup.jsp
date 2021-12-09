<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   	 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


    <style>
        #select{ padding-bottom:10px;}
        #select>button[type=button]{width:250px; height:250px; background-color:rgb(255, 111, 97); border-radius:8px;}
        #goCP{left:250px; top:250px;}
        #goIF{right:250px; top:250px;}
        #empty{height:200px;}
    </style>

</head>
<body>
	<jsp:include page="/header.jsp" flush="false"/>

    <div class="container">
        <div class="row">
            <div class="col-sm-12" id=empty></div>
        </div>
        
        <div class="row">
            <div class="col-sm-12" style="text-align:center; padding-bottom:50px;">
            Sell Luv을 가입해서 지금 바로 다양한 기업의 제품 홍보 그리고 개성넘치는 인플루언서를 만나보세요!
            </div>
        </div>
                 
        <div class="row">
            <div class="col-sm-6" style="text-align:center;" id="select">
        <button type="button" class="btn btn-dark" id="goCP">기업 회원가입</button>
    </div>
            <div class="col-sm-6" style="text-align:center;" id="select">
        <button type="button" class="btn btn-dark" id="goIF">인플루언서 회원가입</button>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">푸터</div>
        </div>
    </div>
</body>
</html>