<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="${pageContext.servletContext.contextPath }/">
<title>Đặt lệnh</title>
</head>
<style>
* {
	margin: 0; padding 0;
	box-sizing: border-box;
}
html {
	font-family: sans-serif;
}

.error {
	color: red;
	display: block;
}
.head-msg {
	width: 100%;
	text-align: center;
	margin: 24px 0;
	font-size: 24px;
}
.head-msg-fail {
	color: red;
}
.head-msg-success {
	color: green;
}
#frmMain {
	width: 1000px;
	margin-left: auto;
	margin-right: auto;
	margin-top: 100px;
	border-radius: 16px;
	padding: 20px 40px;
	box-shadow: 0 0 10px #4b72cc;
	font-size: 20px;
}

.col-container {
	display: flex;
	justify-content: space-around;
}
.btn-container {
	width: 400px;
	margin-left: auto;
	margin-right: auto;
	margin-top: 24px;
	display: flex;
	justify-content: space-around;
}
.col-input {
	padding: 2px 10px;
	font-size: 20px;
	border-radius: 8px;
	border: 1px solid #ccc;
}
.form-title {
	width: 100%;
	text-align: center;
	color: #1041b3;
}
.row {
	margin-bottom: 20px;
}
.col-label {
	width: 180px;
	display: inline-block;
}
.col-input {
	width: 240px;
}
#submit-btn {
	border: none;
	background-color: #1041b3;
	border-radius: 4px;
	color: #fff;
	font-size: 20px;
	cursor: pointer;
	width: 100px;
	height: 40px;
}
#btn-reset {
	background-color: #ccc;
	border-radius: 4px;
	color: #000;
	display: inline-block;
	width: 100px;
	height: 40px;
	text-align: center;
	line-height: 40px;
	text-decoration: none;
}
</style>
<body>
	<form:form id="frmMain" action="index.htm" method="post"
		modelAttribute="lenhDat">
		<h1 class="form-title">ĐẶT LỆNH</h1>
		<h3 class="head-msg ${trangThaiDatClass }">${HEAD_MESSAGE }</h3>
		<div class="col-container">
			<div class="col">
				<div class="row">
					<form:label cssClass="col-label" path="loaiLenh">Loại lệnh</form:label>
					<form:select cssClass="col-input" path="loaiLenh">
						<form:option value="Lệnh thông thường">Lệnh thông thường</form:option>
					</form:select>
				</div>
				<div class="row">
					<form:label cssClass="col-label" path="maCK">Mã chứng khoán:</form:label>
					<form:input cssClass="col-input" path="maCK" type="text" />
					<form:errors cssClass="error" path="maCK"></form:errors>
				</div>
				<div class="row">
					<form:label cssClass="col-label" path="soLuong">Khối lượng:</form:label>
					<form:input cssClass="col-input" path="soLuong" type="number" min="10" />
					<form:errors cssClass="error" path="soLuong"></form:errors>
				</div>
				<div class="row">
					<form:label cssClass="col-label" path="giaDat">Giá:</form:label>
					<form:input cssClass="col-input" path="giaDat" type="number" step="0.1" min="0.1" />
					<form:errors cssClass="error" path="giaDat"></form:errors>
				</div>
			</div>
			<div class="col">
				<div class="row">
					<form:label cssClass="col-label" path="loaiGD">Mua/bán:</form:label>
					<form:select onchange="changeBtnSubmitText()" id="dsLoaiGD" cssClass="col-input" path="loaiGD">
						<form:option value="M">Mua</form:option>
						<form:option value="B">Bán</form:option>
					</form:select>
				</div>
				<div class="row">
					<form:label cssClass="col-label" path="lenh">Lệnh:</form:label>
					<form:select cssClass="col-input" path="lenh">
						<form:option value="LO">LO</form:option>
					</form:select>
				</div>
			</div>
		</div>
		<div class="btn-container">
			<div>
				<form:button id="submit-btn">
				<c:if test="${lenhDat.loaiGD eq 'M'.charAt(0) }">Mua</c:if>
				<c:if test="${lenhDat.loaiGD eq 'B'.charAt(0) }">Bán</c:if>
				</form:button>
			</div>
			<div>
				<a id="btn-reset" href="index.htm">Làm lại</a>
			</div>
		</div>
	</form:form>
	<script type="text/javascript">
		function changeBtnSubmitText() {
			var loaiGD = document.getElementById('dsLoaiGD');
			var submitBtn = document.getElementById('submit-btn');
			if (loaiGD.value == 'M') {
				submitBtn.innerText = 'Mua';
			}
			else {
				submitBtn.innerText = 'Bán';
			}
		}
	</script>
</body>
</html>