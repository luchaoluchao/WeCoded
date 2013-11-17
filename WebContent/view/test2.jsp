<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ include file="./base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Test2</title>
    <%@ include file="./head.jsp"%>  
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="keywords" content="jinnan,jinn">
	<meta http-equiv="description" content="jinn">
	
	<!-- Css -->
	<link href="${BOOTSTRAP_CSS}" rel="stylesheet" media="screen">
	<link href="${BOOTSTRAP_THEME_CSS}" rel="stylesheet" media="screen">
	
	<!-- Js -->
	<script type="text/javascript" src="${JQUERY}"></script>
	<script type="text/javascript" src="${BOOTSTRAP_JS}"></script>
	<script type="text/javascript" src="${AJAXANYWHERE}"></script>
	<script type="text/javascript" src="${JQUERY_FORM}"></script>
	
	<script type="text/javascript" src="${RESOURCE}/js/util/jinva.js"></script>
	<script type="text/javascript" src="${RESOURCE}/js/test2.js"></script>
	
</head>
<body>

	<div class="container">
		<br/><br/><br/>
		
		<img id="input" src="${RESOURCE}/image/test.jpg" />
	
 		<canvas id="canvas" width="500px" height="400px" style="border: 1px solid red;"></canvas>
 
		<button onclick="go()" id="testBtn">Go</button>
		
		<div id="outputDiv" style="border: 1px solid black;"></div>
 		
 		<form id="uploadForm" action="${CONTEXT_PATH}/setting/upload" method="post" enctype="multipart/form-data">
 			<input id="uploadData" name="uploadData">
 			<input id="uploadName" name="uploadName">
 			<input id="uploadType" name="uploadType">
 		</form>
 		
	</div>
</body>
</html>