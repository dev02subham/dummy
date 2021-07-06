<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>International Patients Management System</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Pinyon+Script&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/css/style-table.css">
<link rel="stylesheet" href="/css/style-admin.css">
</head>
<body>
	<div class="main-container-register">
		<%@ include file="fragments/header.jsp"%>
		<div class="section grid">
			<%@ include file="admin-fragments/admin-sidebar.jsp"%>
			<div class="content list-container">
				<h1>Our Specialists</h1>
				<div class="container">
					<form:form action="/portal/addSpeclialistForm" method="post"
						modelAttribute="specialistDetail">
						<div class="form-group">
							<form:label path="name">Specialist Name:</form:label>
							<form:input path="name" class="form-control" id="name"
								type="text" required="required" pattern="[a-zA-Z]+([\s][a-zA-Z]*)*"
								data-error="Please enter a valid Name." />
						</div>
						
						<div class="form-group">
							<form:label path="ailment">Select Ailment:</form:label>
							<form:select path="ailment" class="form-control" id="ailment"
								items="${ailmentList}" required="required" />
						</div>
						<div class="form-group">
							<form:label path="exp">Experience:</form:label>
							<form:input path="exp" class="form-control" id="exp"
								type="number" max="100" min="1" required="required" />
						</div>
						<div class="form-group">
							<form:label path="contact">Contact Number:</form:label>
							<form:input path="contact" class="form-control"
								id="contact" type="number" required="required"/>
						</div>
						<form:button class="btn">Add</form:button>
					</form:form>
				</div>
				
				
			</div>
		</div>
		<%@ include file="fragments/footer.jsp"%>
	</div>
</body>
</html>