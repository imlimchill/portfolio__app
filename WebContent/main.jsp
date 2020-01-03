<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<title>main</title>
</head>
<body>
<br>
<br>
	<div class="container">
		<jsp:include page="nav.html"></jsp:include>
		
		<!-- profile screen -->
		<form>
			<div class="col-sm-12 text-center">
				<img src="uploadFileSave/person.jpg" style="border: 3px solid gold; border-radius: 200px; margin:80px">
			</div>
			<div style="padding-left:70px">
				<div class="row col-sm-12">
					<div class="form-group row col-sm-6">
						<label for="name" class="col-sm-2 col-form-label">이름</label>
						<a class="list-group-item col-sm-9" name="name">임채정</a>
					</div>
					<div class="form-group row col-sm-6">
						<label for="colFormLabel" class="col-sm-2 col-form-label">영어</label>
						<a class="list-group-item col-sm-9" name="english">Lim Cheajeong</a>
					</div>
				</div>

				<div class="row col-sm-12">
					<div class="form-group row col-sm-6">
						<label for="colFormLabel" class="col-sm-2 col-form-label">전화</label>
						<a class="list-group-item col-sm-9" name="tel">010-xxxx-xxxx</a>
					</div>
					<div class="form-group row col-sm-6">
						<label for="colFormLabel" class="col-sm-2 col-form-label">학과</label>
						<a class="list-group-item col-sm-9" name="major">일어일본학과</a>
					</div>
				</div>
				
				<div class="row col-sm-12">
					<div class="form-group row col-sm-6">
						<label for="colFormLabel" class="col-sm-2 col-form-label">이메일</label>
						<a class="list-group-item col-sm-9" name="email">xxxx@gmail.com</a>
					</div>
					<div class="form-group row col-sm-6" >
						<label for="colFormLabel" class="col-sm-2 col-form-label">복수</label>
						<a class="list-group-item col-sm-9" name="major2">컴퓨터공학과</a>
					</div>
				</div>
				<div class="form-group row col-sm-12" style="padding-top:30px; padding-bottom:30px">
					<label for="colFormLabel" class="col-sm-1 col-form-label">학력</label>
					<ul class="list-group col-sm-10" name="education">
						<li class="list-group-item">2019: 선문대학교  컴퓨터공학과 복수전공</li>
						<li class="list-group-item">2016: 선문대학교 일어일본학과 입학</li>
						<li class="list-group-item">XXXXXXXX</li>
						<li class="list-group-item">XXXXXXXX</li>
					</ul>
				</div>
				<div class="form-group row col-sm-12" style="padding-bottom:100px">
					<label for="colFormLabel" class="col-sm-1 col-form-label">자격증</label>
					<ul class="list-group col-sm-10" name="certificate">
						<li class="list-group-item">데헿?</li>
						<li class="list-group-item">XXXXXXXX</li>
						<li class="list-group-item">XXXXXXXX</li>
					</ul>
				</div>
			</div>
		</form>
		<button class="btn btn-sm btn-secondary" id="btnProjectInsertForm" style="margin-left: 25px" 
				data-toggle="modal" data-target=".bd-example-modal-lg">프로젝트 추가하기</button>
	</div>
	
	<!-- modify Modal -->
	<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document" >
			<div class="modal-content">
				<form style="margin:25px">
					<div class="row col-sm-19" style="padding-left:15px">
						<div class="form-group row col-sm-6" style="padding-right:40px">
							<input type="text" class="form-control" id="exampleFormControlInput1" placeholder="이름">
						</div>
						<div class="form-group row col-sm-6"  style="padding-left:40px">
							<input type="text" class="form-control" id="exampleFormControlInput1" placeholder="영어 이름">
						</div>
					</div>
					<div class="row col-sm-19" style="padding-left:15px">
						<div class="form-group row col-sm-6" style="padding-right:40px">
							<input type="text" class="form-control" id="exampleFormControlInput1" placeholder="전화">
						</div>
						<div class="form-group row col-sm-6"  style="padding-left:40px">
							<input type="text" class="form-control" id="exampleFormControlInput1" placeholder="학과">
						</div>
					</div>
					<div class="row col-sm-19" style="padding-left:15px">
						<div class="form-group row col-sm-6" style="padding-right:40px">
							<input type="email" class="form-control" id="exampleFormControlInput1" placeholder="이메일">
						</div>
						<div class="form-group row col-sm-6"  style="padding-left:40px">
							<input type="text" class="form-control" id="exampleFormControlInput1" placeholder="학과2">
						</div>
					</div>
					<div class="row col-sm-12" style="padding-left:15px">
						<label>학력</label>
						<input type="text" class="form-control" id="exampleFormControlInput1" placeholder="학력1">
						<input type="text" class="form-control" id="exampleFormControlInput1" placeholder="학력2">
						<input type="text" class="form-control" id="exampleFormControlInput1" placeholder="학력3">
						<input type="text" class="form-control" id="exampleFormControlInput1" placeholder="학력4">
						<input type="text" class="form-control" id="exampleFormControlInput1" placeholder="학력5">
						<input type="text" class="form-control" id="exampleFormControlInput1" placeholder="학력6">
					</div>
					<br>
					<div class="row col-sm-12" style="padding-left:15px">
						<label>학력</label>
						<input type="text" class="form-control" id="exampleFormControlInput1" placeholder="자격증1">
						<input type="text" class="form-control" id="exampleFormControlInput1" placeholder="자격증2">
						<input type="text" class="form-control" id="exampleFormControlInput1" placeholder="자격증3">
						<input type="text" class="form-control" id="exampleFormControlInput1" placeholder="자격증4">
						<input type="text" class="form-control" id="exampleFormControlInput1" placeholder="자격증5">
						<input type="text" class="form-control" id="exampleFormControlInput1" placeholder="자격증6">
					</div>
					<input type="button" name="" value="수정하기">
				</form>
			</div>
		</div>
	</div>
</body>
</html>