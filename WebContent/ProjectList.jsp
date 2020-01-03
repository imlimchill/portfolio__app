<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>ProjectList</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

	<script src="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/alertify.min.js"></script>
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/css/alertify.min.css"/>
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/css/themes/default.min.css"/>
	<script type="text/javascript">
  		$(function() {
			console.log($("#portfolioList .detail_view").length);
			$("#portfolioList .card").click(function() {
				$.getJSON("portfolio_ajax_detail.do",{no:$("img",this).attr("data-no")},display);
				
			});
			
			function display(json) {
				
				if(json.result){
					$("#detailModal .title").text(json.portfolio.title);
					$("#detailModal .content").text(json.portfolio.content);
					$("#detailModal .start").text("프로젝트 기간: " + json.portfolio.startDate);
					$("#detailModal .end").text("~ " + json.portfolio.endDate);
					$("#detailModal .date").text("최근 수정날짜: " + json.portfolio.regtDate);
					
					$(json.portfolio.dataList).each(function(index) {
						console.log('ea');
						let $div = $('<div>');
						
						$div.addClass("carousel-item")
							.append($('<img class="d-block w-100" width="800px"height="600px">').attr("src","uploadFileSave/"+this.realFileName))
							.appendTo("#detailModal .carousel-inner");
						
						if(index ==0) $div.addClass("active");
					});
					
					
					$("#detailModal").modal("show");
				}else{
					alert("불러오기에 실패하였습니다.");
				}
			}
			
		});
  		
 
  		$(function() {
			$(".test .remove_view").click(function() {
				location.href = "portfolio_remove.do?no=" + $(this).attr("data-no");
			});
		});
  	</script>
</head>
<body>
	<br>
	<br>
	<div class="container">
		<jsp:include page="nav.html"></jsp:include>
		<!-- title and search bar  -->
		<h4 class="text-center" style="margin: 25px">프로젝트 목록</h4>
		<form action="portfolio_search.do" method="post">
			<div class="input-group mb-3">

				<input id="inputSearchWord" type="text" class="form-control" name="title"
					placeholder="Search" value="${param.title}">
				<div class="input-group-append">
					<button id="btnSearchWord" class="btn btn-success">Go</button>
				</div>

			</div>
		</form>

	<!-- project card insert -->
		<div class="card-deck" style="margin-left: 20px" id="portfolioList">
		<c:forEach items="${list}" var="portfolio" varStatus="status">
		  <c:if test="${status.count mod 3 == 1}">
		  	<div class="row col-md-12" style="margin-bottom: 30px" >
		  </c:if>
			  <div class="col-md-4 test">
					<div class="card border-secondary mb-3" style="max-width: 18rem;">
					<img src="uploadFileSave/happy_new_year.png" class="card-img-top btn-primary testest" type="button" 
						data-target=".bd-example-modal-xl" data-no="${portfolio.no}">
					<div class="card-body text-secondary">
						<h5 class="card-title">${portfolio.title}</h5>
						<p class="card-text">참여자 ${portfolio.leader} 외 ${portfolio.memberCount} 명</p>
						<p class="card-text">
							<small class="text-muted">시작일: ${portfolio.startDate} /</small>
							<small class="text-muted"> 종료일: ${portfolio.endDate}</small>
						</p>
					</div>
					
					<button type="button" class="btn btn-secondary remove_view card-img-top" data-no="${portfolio.no}">삭제하기</button>
    				
				</div>
			  </div>	
		  <c:if test="${status.count mod 3 == 0}">
		  	</div>
		  </c:if>		  		
		</c:forEach>
		</div>
			
		<button class="btn btn-sm btn-secondary" id="btnProjectInsertForm" style="margin-left: 25px" 
				data-toggle="modal" data-target=".bd-example-modal-lg">프로젝트 추가하기</button>


	<!-- end container -->

	<!-- introduction Modal-->
	<div id="detailModal" class="modal fade bd-example-modal-xl" tabindex="-1" role="dialog"
		aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl" role="document">
			<div class="modal-content">
				<!-- picture screen -->
				<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
					
					<div class="carousel-inner">
<!-- 						<div class="carousel-item active">
							<img src="uploadFileSave/" class="d-block w-100" width="800px"height="400px" 
								alt="http://placehold.it/800x400">
						</div>
						<div class="carousel-item">
							<img src="http://placehold.it/800x400" class="d-block w-100"
								alt="...">
						</div> -->
					</div>
					<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev"> 
					<span class="carousel-control-prev-icon" aria-hidden="true"></span> 
					<span class="sr-only">Previous</span>
					</a> 
					<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next"> 
					<span class="carousel-control-next-icon" aria-hidden="true"></span> 
					<span class="sr-only">Next</span>
					</a>
				</div>
				<!-- information screen -->
				<div class="media">
					<div class="media-body" style="margin: 25px">
						<h5 class="mt-0 title"></h5>
						<p class="content"></p>
						<p class="">
							<small class="text-muted start"></small>
							<small class="text-muted end"></small>
						</p>
						<small class="text-muted date"></small>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Modal -->

	<!-- insert Modal -->
	<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document" >
			<div class="modal-content">
				<form style="margin: 35px" method="post" enctype="multipart/form-data" action="portfolio_register.do">
					<H3>프로젝트 추가하기</H3>
					<div class="form-group row">
						<input type="text" class="form-control" name="title" placeholder="제목">
					</div>
					<div class="form-group row">
						<input type="text" class="form-control" name="leader" placeholder="대표자">
					</div>
					<div class="form-group row">
						<input type="text" class="form-control" name="member" placeholder="참가자(,로 표시한다.)">
					</div>
					<div class="form-group row">
						<textarea class="form-control" name="content" placeholder="내용"></textarea>
					</div>
					<div class="form-group row">
						<input type="date" class="form-control" name="startDate" placeholder="시작날">
					</div>
					<div class="form-group row">
						<input type="date" class="form-control" name="endDate" placeholder="종료일">
					</div>
					<div class="custom-file">
						<label >사진 첨부하기</label>
					</div>
					<div class="custom-file">
						<input type="file" class="custom-file-input" id="customFile" name="dataFile1">
						<label class="custom-file-label" for="customFile">Choose file</label>
					</div>
					<div class="custom-file">
						<input type="file" class="custom-file-input" id="customFile" name="dataFile2">
						<label class="custom-file-label" for="customFile">Choose file</label>
					</div>
					<div class="custom-file">
						<input type="file" class="custom-file-input" id="customFile" name="dataFile3">
						<label class="custom-file-label" for="customFile">Choose file</label>
					</div>
					<div class="custom-file">
						<input type="file" class="custom-file-input" id="customFile" name="dataFile4">
						<label class="custom-file-label" for="customFile">Choose file</label>
					</div>
					<div>
						<button type="submit" class="btn btn-secondary" style="margin:20px">프로젝트 추가하기</button>
					</div>
				</form>
			</div>
		</div>
	</div>


</body>
</html>