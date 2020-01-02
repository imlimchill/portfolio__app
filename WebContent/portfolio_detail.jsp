<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>포트폴리오 상세조회</h1>

	<table border="1">
		<tr>
			<td>제목</td>
			<td>${portfolio.title}</td>
		</tr>
		<tr>
			<td>대표자</td>
			<td>${portfolio.leader}</td>
		</tr>
		<tr>
			<td>참여자</td>
			<td>총 ${portfolio.memberCount} 명 : ${portfolio.member}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${portfolio.content}</td>
		</tr>
		<tr>
			<td>프로젝트 시작날</td>
			<td>${portfolio.startDate}</td>
		</tr>
		<tr>
			<td>프로젝트 종료일</td>
			<td>${portfolio.endDate}</td>
		</tr>
	</table>
	<c:forEach items="${portfolio.dataList}" var="file">
		<img src="uploadFileSave/${file.realFileName}" width="200px" height="200px"/>
		<br/>
	</c:forEach>
</body>
</html>