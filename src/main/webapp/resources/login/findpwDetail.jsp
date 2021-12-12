<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang="en" dir="ltr">
   <head>
      <meta charset="utf-8">
      <title>비밀번호 찾기</title>
 <!--  <link rel="stylesheet" href="style.css">   -->
      <meta name="viewport" content="width=device-width, initial-scale=1.0">

      <style>
           @import url('https://fonts.googleapis.com/css?family=Poppins:400,500,600,700&display=swap');  
         *{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Poppins', sans-serif;
} 
html,body{
  display: grid;
  height: 100%;
  width: 100%;
  place-items: center;
  background: -webkit-linear-gradient(left, #a445b2, #fa4299);
}
::selection{
  background: #fa4299;
  color: #fff;
}
.wrapper{
  overflow: hidden;
  max-width: 390px;
  background: #fff;
  padding: 30px;
  border-radius: 5px;
  box-shadow: 0px 15px 20px rgba(0,0,0,0.1);
  margin-top: 120px;
  margin-bottom: 100px;
}
.wrapper .title-text{
  display: flex;
  width: 200%;
}
.wrapper .title{
  width: 50%;
  font-size: 35px;
  font-weight: 600;
  text-align: center;
  transition: all 0.6s cubic-bezier(0.68,-0.55,0.265,1.55);
}
.wrapper .slide-controls{
  position: relative;
  display: flex;
  height: 50px;
  width: 100%;
  overflow: hidden;
  margin: 30px 0 10px 0;
  justify-content: space-between;
  border: 1px solid lightgrey;
  border-radius: 5px;
}
.slide-controls .slide{
  height: 100%;
  width: 100%;
  color: #fff;
  font-size: 18px;
  font-weight: 500;
  text-align: center;
  line-height: 48px;
  cursor: pointer;
  z-index: 1;
  transition: all 0.6s ease;
}
.slide-controls label.signup{
  color: #000;
}
.slide-controls .slider-tab{
  position: absolute;
  height: 100%;
  width: 50%;
  left: 0;
  z-index: 0;
  border-radius: 5px;
  background: -webkit-linear-gradient(left, #a445b2, #fa4299);
  transition: all 0.6s cubic-bezier(0.68,-0.55,0.265,1.55);
}
input[type="radio"]{
  display: none;
}
#signup:checked ~ .slider-tab{
  left: 50%;
}
#signup:checked ~ label.signup{
  color: #fff;
  cursor: default;
  user-select: none;
}
#signup:checked ~ label.login{
  color: #000;
}
#login:checked ~ label.signup{
  color: #000;
}
#login:checked ~ label.login{
  cursor: default;
  user-select: none;
}
.wrapper .form-container{
  width: 100%;
  overflow: hidden;
}
.form-container .form-inner{
  display: flex;
  width: 200%;
}
.form-container .form-inner form{
  width: 50%;
  transition: all 0.6s cubic-bezier(0.68,-0.55,0.265,1.55);
}
.form-inner form .field{
  height: 50px;
  width: 100%;
  margin-top: 20px;
}
.form-inner form .field input{
  height: 100%;
  width: 100%;
  outline: none;
  padding-left: 15px;
  border-radius: 5px;
  border: 1px solid lightgrey;
  border-bottom-width: 2px;
  font-size: 17px;
  transition: all 0.3s ease;
}
.form-inner form .field input:focus{
  border-color: #fc83bb;
  /* box-shadow: inset 0 0 3px #fb6aae; */
}
.form-inner form .field input::placeholder{
  color: #999;
  transition: all 0.3s ease;
}
form .field input:focus::placeholder{
  color: #b3b3b3;
}
.form-inner form .pass-link{
  margin-top: 5px;
}
.form-inner form .signup-link{
  text-align: center;
  margin-top: 30px;
}
.form-inner form .pass-link a,
.form-inner form .signup-link a{
  color: #fa4299;
  text-decoration: none;
}
.form-inner form .pass-link a:hover,
.form-inner form .signup-link a:hover{
  text-decoration: underline;
}
form .btn{
  height: 50px;
  width: 100%;
  border-radius: 5px;
  position: relative;
  overflow: hidden;
}
form .btn .btn-layer{
  height: 100%;
  width: 300%;
  position: absolute;
  left: -100%;
  background: -webkit-linear-gradient(right, #a445b2, #fa4299, #a445b2, #fa4299);
  border-radius: 5px;
  transition: all 0.4s ease;;
}
form .btn:hover .btn-layer{
  left: 0;
}
form .btn input[type="submit"]{
  height: 100%;
  width: 100%;
  z-index: 1;
  position: relative;
  background: none;
  border: none;
  color: #fff;
  padding-left: 0;
  border-radius: 5px;
  font-size: 20px;
  font-weight: 500;
  cursor: pointer;
}
form .btn input[type="button"]{
  height: 100%;
  width: 100%;
  z-index: 1;
  position: relative;
  background: none;
  border: none;
  color: #fff;
  padding-left: 0;
  border-radius: 5px;
  font-size: 20px;
  font-weight: 500;
  cursor: pointer;
}

#footer{
	width: 100%;
}
.check-text{
	outline: none;
	width:100%;
	height:100%;
	padding-left: 10px;
	border: 1px solid lightgrey;
    border-bottom-width: 2px;
    font-size: 17px;
    transition: all 0.3s ease;
    border-radius: 5px;
}
.check-text-box{
	
}
.form-div{
  height: 100%;
  width: 100%;
  z-index: 1;
  position: relative;
  background: none;
  color: #000000;
  font-size: 20px;
  font-weight: 500;
  outline: none;
  padding-left: 15px;
  border-radius: 5px;
  border: 1px solid lightgrey;
  border-bottom-width: 2px;
  transition: all 0.3s ease;
  line-height:50px;
  text-align:center;
}
      </style>
   </head>
   <body>

  <jsp:include page="/header.jsp" flush="false"/>
      <div class="wrapper">
         <div class="title-text">
            <div class="title">
               비밀번호 재설정
            </div>
         </div>
         <div class="form-container">
            <div class="slide-controls">
               <label for="login" class="slide login">기업</label>
               <label for="signup" class="slide signup">개인</label>
               <div class="slider-tab"></div>
            </div>
            
            
            <div class="form-inner">
               <form action="/resetCPpw.mem" method="post" class="login" id="cp-frm">
             	  <div class="field">
					<div class="form-div">ID : ${cpdto.id }</div>             	  
                  </div>
             	  <div class="signup-link" style="margin-top:20px;">
                     비밀번호는 <br>최소 8글자 이상 최대 16글자를 <br> 만족해야합니다. 
                  </div>
                  <div class="field">
                     <input type="password" name="pw_cp" id="pw" placeholder="새로운 비밀번호를 입력하세요." required>
                     <input type="hidden" name="id" value="${cpdto.id }">
                  </div>
                  <div class="field">
                     <input type="password" name="" id="pwd" placeholder="비밀번호를 한번 더 입력하세요." required>
                  </div>
                  <div class="signup-link " id="pwCheckResult">
                  </div>
                  <div class="field btn">
                     <div class="btn-layer"></div>
                     <input type="submit" value="비밀번호 재설정" id="cp_findpw" class="">
                  </div>
               </form>
               
               <form action="/influencerFindpw.mem" class="signup" method="post" id="if-frm">
                  <div class="field">
                     <input type="text" name="id_if" placeholder="아이디를 입력하세요." required>
                  </div>
                  <div class="field">
                     <input type="text" name="name_if" placeholder="이름을 입력하세요." required>
                  </div>
                  <div class="field check-text-box">
                     <select class="check-text" name="check-text-if" id="check-text-if">
                     	<option>본인 확인 문구를 선택해주세요.</option>
                     	<option>내가 다니던 초등학교는?</option>
                        <option>내가 좋아하는 가수는?</option>
                        <option>내가 좋아하는 숫자는?</option>
                        <option>내가 존경하는 인물은?</option>
                     </select>
                  </div>
                 <div class="field">
                     <input type="text" name="answer-if" placeholder="본인 확인 문구의 답을 입력하세요." required>
                  </div>
                  <div class="pass-link">
                    <a href="#" class="idCheckSpan">아이디를 잊으셨습니까..?</a>
                 </div>
                
                 
                  <div class="field btn">
                     <div class="btn-layer"></div>
                     <input type="button" value="비밀번호 찾기" id="if_findpw" class="">
                     <script>
	                     $("#cp_findpw").on("click",function(){
	                  		if($("#check-text-cp").val()== "본인 확인 문구를 선택해주세요.") {
	                  			alert("본인 확인 문구를 선택해주세요.");
	                  			return false;
	                  		}else{
	                  			$("#cp-frm").submit();
	                  		}
	                  		
	                  	})
                     	$("#if_findpw").on("click",function(){
                     		if($("#check-text-if").val()== "본인 확인 문구를 선택해주세요.") {
                     			alert("본인 확인 문구를 선택해주세요.");
                     			return false;
                     		}else{
                     			$("#if-frm").submit();
                     		}
                     		
                     	})
                     </script>
                  </div>
               </form>
            </div>
         </div>
          
      </div>
     
      <div id="footer">
       	<jsp:include page="/footer.jsp" flush="false" />
      </div>
      <div id="errorMessage" style="display:hidden">${errorMessage}</div>
      
<script>

$("#cp_findpw").on("click",function(){
	let regexPw = /^[a-zA-Z\d]{8,16}$/;
	let resultPw = regexPw.test($("#pw").val());
	if(resultPw == false){
		alert("비밀번호 형식이 올바르지않습니다.");
		return false;
	}
	
	if($("#pw").val() != $("#pwd").val()){
		alert("비밀번호를 다시 확인 해주세요");
		return false;
	}
});
   
      
	let result = document.getElementById("pwCheckResult");
	
	document.getElementById("pwd").oninput = function() {
		let pw1 = $("#pw").val();
		let pw2 = $("#pwd").val();
		if (pw1 != pw2) {
			result.innerHTML = "패스워드가 일치하지않습니다"

		}else if (pw1 ==""){
			result.innerHTML = ""
		} else if (pw2 ==""){
			result.innerHTML = ""
		}else {
			result.innerHTML = "패스워드가 일치합니다."
		}
	}
	document.getElementById("pw").oninput = function() {
		let pw1 = $("#pw").val();
		let pw2 = $("#pwd").val();
		if (pw1 != pw2) {
			result.innerHTML = "패스워드가 일치하지않습니다"

		} else if (pw1 ==""){
			result.innerHTML = ""
		} else if (pw2 ==""){
			result.innerHTML = ""
		}else  {
			result.innerHTML = "패스워드가 일치합니다."
		}
	}
	
	</script>
    
   </body>
</html>