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
      </style>
   </head>
   <body>

  <jsp:include page="/header.jsp" flush="false"/>
      <div class="wrapper">
         <div class="title-text">
            <div class="title">
               비밀번호 찾기
            </div>
            <div class="title">
               비밀번호 찾기
            </div>
            <div class="login"style="display:hidden"></div>
         </div>
         <div class="form-container">
            <div class="slide-controls">
               <label for="login" class="slide login">기업</label>
               <label for="signup" class="slide signup">개인</label>
               <div class="slider-tab"></div>
            </div>
            <div class="form-inner">
               <form action="/companyFindpw.mem" method="post" class="login" id="cp-frm">
                  <div class="field">
                     <input type="text" name="id_cp" placeholder="아이디를 입력하세요." id="id_cp" required>
                  </div>
                  <div class="field">
                     <input type="text" name="name_cp" placeholder="이름을 입력하세요." id="name_cp" required>
                  </div>
                  <div class="field check-text-box">
                     <select class="check-text" name="check-text-cp" id="check-text-cp">
                     	<option>본인 확인 문구를 선택해주세요.</option>
                     	<option>내가 다니던 초등학교는?</option>
                        <option>내가 좋아하는 가수는?</option>
                        <option>내가 좋아하는 숫자는?</option>
                        <option>내가 존경하는 인물은?</option>
                     </select>
                  </div>
                 <div class="field">
                     <input type="text" name="answer-cp" placeholder="본인 확인 문구의 답을 입력하세요." id="check-answer-cp" required>
                  </div>
                  <div class="pass-link" >
                     <a href="/findid.mem" class="idCheckSpan">아이디를 잊으셨습니까..?</a>
                  </div>
                
                  <div class="field btn">
                     <div class="btn-layer"></div>
                     <input type="button" value="비밀번호 찾기" id="cp_findpw" class="">
                  </div>
                  <div class="signup-link">
                     회원이 아니세요? <a href="/resources/signup/selectSignup.jsp">회원가입</a>
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
                    <a href="/findid.mem" class="idCheckSpan">아이디를 잊으셨습니까..?</a>
                 </div>
                
                 
                  <div class="field btn">
                     <div class="btn-layer"></div>
                     <input type="button" value="비밀번호 찾기" id="if_findpw" class="">
                     <script>
                     
	                  	let isCPMember = function() {
	                     	let id = $("#id_cp").val();
	                     	let name = $("#name_cp").val();
	                     	let text = $("#check-text-cp").val();
	                     	let answer = $("#check-answer-cp").val();
		                  	$.ajax({
	               				url:"/isCPMember.mem",
	               				type:"post",
	               				data:{
	               					id:id,
	               					name:name,
	               					text:text,
	               					answer:answer
	               				}
	               			})
	               			.done(function(resp){
	               					console.log(resp);
	               				if(resp=="false"){
	               					alert("일치하는 정보의 회원이 존재하지 않습니다.");
	               					$("#id_cp").val("");
	               					$("#name_cp").val("");
	               					let answer = $("#check-answer-cp").val("");
	               					return false;
	               				}else {
        	                  		if($("#check-text-cp").val()== "본인 확인 문구를 선택해주세요.") {
        	                  			alert("본인 확인 문구를 선택해주세요.");
        	                  			return false;
        	                  		}else{
        	                  			$("#cp-frm").submit();
        	                  		}
	               				}
	               			});
	                    }
                     	 $("#cp_findpw").on("click",isCPMember);
	                     
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
      console.log('${result}'); 
         const loginText = document.querySelector(".title-text .login");
         const loginForm = document.querySelector("form.login");
         const loginBtn = document.querySelector("label.login");
         const signupBtn = document.querySelector("label.signup");
         const signupLink = document.querySelector("form .signup-link a");
         signupBtn.onclick = (()=>{
           loginForm.style.marginLeft = "-50%";
           loginText.style.marginLeft = "-50%";
         });
         loginBtn.onclick = (()=>{
           loginForm.style.marginLeft = "0%";
           loginText.style.marginLeft = "0%";
         });
        
         
         // 로그인 유효성 검사..
       
         $('#errorMessage')[0].innerText && alert($('#errorMessage')[0].innerText);
         
        /*  const errorMessage = $('#errorMessage').value;
      	if (errorMessage){
      		alert(errorMessage);
      	} */
         
         
      </script>
    
   </body>
</html>