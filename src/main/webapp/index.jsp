<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
	<!-- Favicon  -->
    <link rel="icon" href="#">
</head>
<style>
.quotes {
	color:black;
}
</style>
<body data-bs-spy="scroll" data-bs-target="#navbarExample">
    
    
    <jsp:include page="/header.jsp" flush="false" />
	 
      
    <!-- Header -->
    <header id="header" class="header">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-xl-5">
                    <div class="text-container">
                        <h1 class="h1-large">영향력 있는<br> 인플루언서들을<br> 만나보세요.</h1>
                        <p class="p-large">당신의 제품들을 홍보할 준비가 되어있습니다. <br>유튜브, 인스타그램, 트위터, 페이스북 등 다방면의<br> 인플루언서들과 협업이 가능합니다.</p>
                        <a class="btn-solid-lg" href="/influencerList.ifcp?cpage=1" style="background-color:rgba(255, 111, 97);border:none">인플루언서 보러가기</a>
                    </div> <!-- end of text-container -->
                </div> <!-- end of col -->
                <div class="col-lg-6 col-xl-7">
                    <div class="image-container">
                        <img class="img-fluid" src="resources/mainpage/images/main_in.png" alt="alternative">
                    </div> <!-- end of image-container -->
                </div> <!-- end of col -->
            </div> <!-- end of row -->
        </div> <!-- end of container -->
    </header> <!-- end of header -->
    <!-- end of header -->


    <!-- Services -->
    <div id="services" class="cards-1 bg-gray">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h2>제품 홍보는 왜 SELL-LUV ?</h2>
                </div> <!-- end of col -->
            </div> <!-- end of row -->
            <div class="row">
                <div class="col-lg-12">
                    
                    <!-- Card -->
                    <div class="card">
                        <div class="card-icon">
                            <span class="fas fa-headphones-alt"></span>
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">믿고 맡기는 홍보</h5>
                            <p>엄선된 기업, 인플루언서들이 찾아오는 SELL-LUV. 등급제와 리뷰로 더욱 믿음직한 만남을 확인하세요.</p>
                            <%--
                            <a class="read-more no-line" href="article.html">더보기 <span class="fas fa-long-arrow-alt-right"></span></a>
                             --%>
                        </div>
                    </div>
                    <!-- end of card -->

                    <!-- Card -->
                    <div class="card">
                        <div class="card-icon red">
                            <span class="far fa-clipboard"></span>
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">간보는 미팅은 NO !</h5>
                            <p>기업은 원하는 조건을, 인플루언서는 원하는 제품을 ! 각자의 needs를 충족시킬 수 있는 미팅을 SELL-LUV이 함께 합니다.</p>
                        </div>
                    </div>
                    <!-- end of card -->

                    <!-- Card -->
                    <div class="card">
                        <div class="card-icon green">
                            <span class="far fa-comments"></span>
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">한눈에 정리하는 나의 활동</h5>
                            <p>얼만큼의 계약을 성사했는지, 어떤 기업 혹은 인플루언서가 나를 관심있어하는지 확인하세요! 마이페이지에 모든 것이 있습니다.</p>
                        </div>
                    </div>
                    <!-- end of card -->

                </div> <!-- end of col -->
            </div> <!-- end of row -->
        </div> <!-- end of container -->
    </div> <!-- end of cards-1 -->
    <!-- end of services -->


    <!-- Details 1 -->
    <div id="details" class="basic-1">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-xl-7">
                    <div class="image-container">
                        <img class="img-fluid" src="resources/mainpage/images/company.png" alt="alternative">
                    </div> <!-- end of image-container -->
                </div> <!-- end of col -->
                <div class="col-lg-6 col-xl-5">
                    <div class="text-container">
                        <div class="section-title"></div>
                        <h2>당신의 손에 닿고 싶어하는 수많은 상품들이 있습니다.</h2>
                        <p>내로라하는 대기업의 신상품을 가장 먼저 리뷰해보고 싶다면? 나만 알기에는 아까운 꿀템들까지! <br>당신의 홍보 능력을 펼칠 기회입니다.</p>
                        <a class="btn-solid-reg" href="/companyList.ifcp?cpage=1" style="background-color:rgba(255, 111, 97);border:none">기업 보러가기</a>
                    </div> <!-- end of text-container -->
                </div> <!-- end of col -->
            </div> <!-- end of row -->
        </div> <!-- end of container -->
    </div> <!-- end of basic-1 -->
    <!-- end of details 1 -->


    <!-- Details 2 -->
    <div class="basic-2" style="padding-bottom:0">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-xl-5">
                    <div class="text-container">
                        <div class="section-title"></div>
                        <h2>깐부가 되어보세요!</h2>
                        <p>SELL-LUV에서 원하는 조건에 맞는 나의 깐부를 찾아서 깐부맺기 클릭만 하면 상대에게 자동으로 깐부 요청이 생성됩니다. 깐부가 되면 일정을 잡고, 만나서 일을 어떻게 진행할지 논의해보세요. 리뷰를 남겨 상대에게 피드백을 할 수도 있습니다. </p>
                        <a class="btn-outline-reg" href="/resources/signup/selectSignup.jsp" style="background-color:rgba(255, 111, 97);border:none;">회원가입 하러가기</a>
                    </div> <!-- end of text-container -->
                </div> <!-- end of col -->
                <div class="col-lg-6 col-xl-7">
                    <div class="image-container">
                        <img class="img-fluid" src="resources/mainpage/images/kkanbu.png" alt="alternative">
                    </div> <!-- end of image-container -->
                </div> <!-- end of col -->
            </div> <!-- end of row -->
        </div> <!-- end of container -->
    </div> <!-- end of basic-2 -->
    <!-- end of details 2 -->


    <!-- Features -->
    <div id="features" class="accordion-1" style="display:none;">
        <div class="container">
            <div class="row">
                <div class="col-xl-12">
                    <h2 class="h2-heading"></h2>
                    <p class="p-heading">Suspendisse vitae enim arcu. Aliquam convallis risus a felis blandit, at mollis nisi bibendum. Aliquam nec purus at ex blandit posuere nec a odio. Proin lacinia dolor justo</p>
                </div> <!-- end of col -->
            </div> <!-- end of row -->   
            <div class="row">
                <div class="col-xl-5">
                    <div class="accordion" id="accordionExample">
                        
                        <!-- Accordion Item -->
                        <div class="accordion-item">
                            <div class="accordion-icon">
                                <span class="fas fa-tv"></span>
                            </div> <!-- end of accordion-icon -->
                            <div class="accordion-header" id="headingOne">
                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    Analyse the product and design plan
                                </button>
                            </div> <!-- end of accordion-header -->
                            <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                                <div class="accordion-body">Mauris ornare libero et pharetra hendrerit. Cura elementum lectus quis tellus pretium, vitae lobortis dui sagittis aliquam et enim vel semon anticus</div>
                            </div> <!-- end of accordion-collapse -->
                        </div> <!-- end of accordion-item -->
                        <!-- end of accordion-item -->

                        <!-- Accordion Item -->
                        <div class="accordion-item">
                            <div class="accordion-icon blue">
                                <span class="fas fa-microphone"></span>
                            </div> <!-- end of accordion-icon -->
                            <div class="accordion-header" id="headingTwo">
                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    Find the market opportunities
                                </button>
                            </div> <!-- end of accordion-header -->
                            <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
                                <div class="accordion-body">Mauris ornare libero et pharetra hendrerit. Cura elementum lectus quis tellus pretium, vitae lobortis dui sagittis aliquam et enim vel semon anticus</div>
                            </div> <!-- end of accordion-collapse -->
                        </div> <!-- end of accordion-item -->
                        <!-- end of accordion-item -->

                        <!-- Accordion Item -->
                        <div class="accordion-item">
                            <div class="accordion-icon purple">
                                <span class="fas fa-video"></span>
                            </div> <!-- end of accordion-icon -->
                            <div class="accordion-header" id="headingThree">
                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                    Prepare the product launch campaign
                                </button>
                            </div> <!-- end of accordion-header -->
                            <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
                                <div class="accordion-body">Mauris ornare libero et pharetra hendrerit. Cura elementum lectus quis tellus pretium, vitae lobortis dui sagittis aliquam et enim vel semon anticus</div>
                            </div> <!-- end of accordion-collapse -->
                        </div> <!-- end of accordion-item -->
                        <!-- end of accordion-item -->
                        
                        <!-- Accordion Item -->
                        <div class="accordion-item">
                            <div class="accordion-icon orange">
                                <span class="fas fa-tools"></span>
                            </div> <!-- end of accordion-icon -->
                            <div class="accordion-header" id="headingFour">
                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                    Evaluate the campaign and adjust
                                </button>
                            </div> <!-- end of accordion-header -->
                            <div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingFour" data-bs-parent="#accordionExample">
                                <div class="accordion-body">Mauris ornare libero et pharetra hendrerit. Cura elementum lectus quis tellus pretium, vitae lobortis dui sagittis aliquam et enim vel semon anticus</div>
                            </div> <!-- end of accordion-collapse -->
                        </div> <!-- end of accordion-item -->
                        <!-- end of accordion-item -->

                    </div> <!-- end of accordion -->
                </div> <!-- end of col -->
                <div class="col-xl-7">
                    <div class="image-container">
                        <img class="img-fluid" src="resources/mainpage/images/features-dashboard.png" alt=alternative>
                    </div> <!-- end of image-container -->
                </div> <!-- end of col -->
            </div> <!-- end of row -->
        </div> <!-- end of container -->
    </div> <!-- end of accordion-1 -->
    <!-- end of features -->


    <!-- Testimonials -->
    <div class="cards-2 bg-gray">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h2 class="h2-heading">Customer Review</h2>
                </div> <!-- end of col -->
            </div> <!-- end of row -->
            <div class="row">
                <div class="col-lg-12">

                    <!-- Card -->
                    <div class="card">
                        <img class="quotes" src="resources/mainpage/images/quotes.svg" alt="alternative">
                        <div class="card-body">
                            <p class="testimonial-text">저희 기업 제품을 어디서 홍보해야하나 막막했는데 셀럽을 발견해서 정말 다행이었습니다.</p>
                            <div class="testimonial-author">(주)이랜드이노플</div>
                            <div class="occupation">이준협</div>
                        </div>
                        <div class="gradient-floor red-to-blue" style="background:rgba(255, 111, 97);"></div>
                    </div>
                    <!-- end of card -->

                    <!-- Card -->
                    <div class="card">
                        <img class="quotes" src="resources/mainpage/images/quotes.svg" alt="alternative">
                        <div class="card-body">
                            <p class="testimonial-text">원하는 계약 조건들을 상세히 적을 수 있어서 좋았습니다. 조건이 맞는 파트너를 고르기도 편했습니다.</p>
                            <div class="testimonial-author">웅진식품(주)</div>
                            <div class="occupation">김정국</div>
                        </div>
                        <div class="gradient-floor blue-to-purple" style="background:rgba(255, 111, 97);"></div>
                    </div>
                    <!-- end of card -->

                    <!-- Card -->
                    <div class="card">
                        <img class="quotes" src="resources/mainpage/images/quotes.svg" alt="alternative">
                        <div class="card-body">
                            <p class="testimonial-text">저희 제품이 워낙 독특해서, 평범하게 홍보를 하고싶지 않았어요. <br>만족합니다.</p>
                            <div class="testimonial-author">맥시즘</div>
                            <div class="occupation">박재은</div>
                        </div>
                        <div class="gradient-floor purple-to-green" style="background:rgba(255, 111, 97);"></div>
                    </div>
                    <!-- end of card -->
                    
                    <!-- Card -->
                    <div class="card">
                        <img class="quotes" src="resources/mainpage/images/quotes.svg" alt="alternative">
                        <div class="card-body">
                            <p class="testimonial-text">장난감을 가지고 노는 어른이라니.. 다들 무시했죠. 그런데 이제는 이게 직업이 되었습니다.</p>
                            <div class="testimonial-author">유튜브(구독자 12만)</div>
                            <div class="occupation">홍지tube</div>
                        </div>
                        <div class="gradient-floor red-to-blue" style="background:rgba(255, 111, 97);"></div>
                    </div>
                    <!-- end of card -->

                    <!-- Card -->
                    <div class="card">
                        <img class="quotes" src="resources/mainpage/images/quotes.svg" alt="alternative">
                        <div class="card-body">
                            <p class="testimonial-text">요리를 시작한지 10년, 직접 요리를 할 줄 아니까 어떻게 홍보해야할지 그려지더라구요.</p>
                            <div class="testimonial-author">인스타그램(팔로워 7만)</div>
                            <div class="occupation">승훈아빠</div>
                        </div>
                        <div class="gradient-floor blue-to-purple" style="background:rgba(255, 111, 97);"></div>
                    </div>
                    <!-- end of card -->

                    <!-- Card -->
                    <div class="card">
                        <img class="quotes" src="resources/mainpage/images/quotes.svg" alt="alternative">
                        <div class="card-body">
                            <p class="testimonial-text">강아지 3마리와 함께하는 제 삶에서 애견용품 리뷰는 뗄래야 뗄 수 없는 존재죠.<br>&nbsp;</p>
                            <div class="testimonial-author">유튜브(구독자 3만)</div>
                            <div class="occupation">찰리와 도로시</div>
                        </div>
                        <div class="gradient-floor purple-to-green" style="background:rgba(255, 111, 97);"></div>
                    </div>
                    <!-- end of card -->

                </div> <!-- end of col -->
            </div> <!-- end of row -->
        </div> <!-- end of container -->
    </div> <!-- end of cards-2 -->
    <!-- end of testimonials -->


    <!-- Customers -->
    <div class="slider-1" style="display:none;">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h4>Trusted by over <span class="blue">5000</span> customers worldwide</h4>
                    <hr class="section-divider">

                    <!-- Image Slider -->
                    <div class="slider-container">
                        <div class="swiper-container image-slider">
                            <div class="swiper-wrapper">
                                <div class="swiper-slide">
                                    <img class="img-fluid" src="resources/mainpage/images/customer-logo-1.png" alt="alternative">
                                </div>
                                <div class="swiper-slide">
                                    <img class="img-fluid" src="resources/mainpage/images/customer-logo-2.png" alt="alternative">
                                </div>
                                <div class="swiper-slide">
                                    <img class="img-fluid" src="resources/mainpage/images/customer-logo-3.png" alt="alternative">
                                </div>
                                <div class="swiper-slide">
                                    <img class="img-fluid" src="resources/mainpage/images/customer-logo-4.png" alt="alternative">
                                </div>
                                <div class="swiper-slide">
                                    <img class="img-fluid" src="resources/mainpage/images/customer-logo-5.png" alt="alternative">
                                </div>
                                <div class="swiper-slide">
                                    <img class="img-fluid" src="resources/mainpage/images/customer-logo-6.png" alt="alternative">
                                </div>
                            </div> <!-- end of swiper-wrapper -->
                        </div> <!-- end of swiper container -->
                    </div> <!-- end of slider-container -->
                    <!-- end of image slider -->

                </div> <!-- end of col -->
            </div> <!-- end of row -->
        </div> <!-- end of container -->
    </div> <!-- end of slider-1 -->
    <!-- end of customers -->


    <!-- Invitation -->
    <div class="basic-3" style="display:none;">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="text-container">
                        <h2>Find the right influencer for your product</h2>
                        <p class="p-large">Eu convallis arcu ultrices in. Mauris ornare libero et pharetra hendrerit. Curabitur elementum lectus quis vioc tellus</p>
                        <a class="btn-solid-lg" href="#contact">Get free quote</a>
                    </div> <!-- end of text-container -->
                </div> <!-- end of col -->
            </div> <!-- end of row -->
        </div> <!-- end of container -->
    </div> <!-- end of basic-3 -->
    <!-- end of invitation -->


    <!-- Contact -->
    <div id="contact" class="form-1" style="display:none;">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="text-container">
                        <div class="section-title">GET QUOTE</div>
                        <h2>Submit the form for quote</h2>
                        <p>Aliquam et enim vel sem pulvinar suscipit sit amet quis lorem. Sed risus ipsum, egestas sed odio in, pulvinar euismod ipsum. Sed ut enim non nunc fermentum dictum et sit amet erat. Maecenas</p>
                        <ul class="list-unstyled li-space-lg">
                            <li class="d-flex">
                                <i class="fas fa-square"></i>
                                <div class="flex-grow-1">Vel maximus nunc aliquam ut. Donec semper, magna a pulvinar</div>
                            </li>
                            <li class="d-flex">
                                <i class="fas fa-square"></i>
                                <div class="flex-grow-1">Suscipit sit amet quis lorem. Sed risus ipsum, egestas mare</div>
                            </li>
                            <li class="d-flex">
                                <i class="fas fa-square"></i>
                                <div class="flex-grow-1">Sem pulvinar suscipit sit amet quis lorem. Sed risus</div>
                            </li>
                        </ul>
                    </div> <!-- end of text-container -->
                </div> <!-- end of col -->
                <div class="col-lg-6">

                    <!-- Contact Form -->
                    <form>
                        <div class="form-group">
                            <input type="text" class="form-control-input" placeholder="Name" required>
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-control-input" placeholder="Email" required>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control-input" placeholder="Industry" required>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control-input" placeholder="Your product" required>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="form-control-submit-button">Submit</button>
                        </div>
                    </form>
                    <!-- end of contact form -->

                </div> <!-- end of col -->
            </div> <!-- end of row -->
        </div> <!-- end of container -->
    </div> <!-- end of form-1 -->
    <!-- end of contact -->
	
	<jsp:include page="/footer.jsp" flush="false" />

	<%-- 제외 
    <!-- Back To Top Button -->
    <button onclick="topFunction()" id="myBtn">
        <img src="resources/images/up-arrow.png" alt="alternative">
    </button>
    <!-- end of back to top button -->
    --%>
    <!-- Scripts -->
    <script src="<c:url value="resources/mainpage/js/bootstrap.min.js" />"></script>
    <script src="<c:url value="resources/mainpage/js/swiper.min.js" />"></script>
    <script src="<c:url value="resources/mainpage/js/scripts.js" />"></script>
</body>
</html>