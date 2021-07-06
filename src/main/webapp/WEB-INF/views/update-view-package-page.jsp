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
				<h1>Update Package</h1>
				<div class="container">
					<form:form action="updateTreatmentPackage?pid=${pid}" method="GET"
						modelAttribute="packageDetail">
						<div class="form-group">
							<form:label path="pid">Enter Package Id:</form:label>
							<form:input path="pid" class="form-control" id="pid" type="number"/>
						</div>
						<div class="form-group">
							<form:label path="treatmentPackageName">Select Package Name:</form:label>
							<form:input path="treatmentPackageName" class="form-control" type="text" />
						</div>
						<div class="form-group">
							<form:label path="testDetails">Select Test Details:</form:label>
								<form:input path="testDetails" class="form-control" type="text"/>
						</div>
						
						<div class="form-group">
							<form:label path="cost">Enter cost:</form:label>
							<form:input path="cost" class="form-control"  type="number"/>
						</div>
						<div class="form-group">
							<form:label path="treatmentDuration">Enter Duration:</form:label>
							<form:input path="treatmentDuration" class="form-control" type="text"/>
						</div>
						<form:button class="btn" >Update</form:button>
					</form:form>
				</div>
			</div>
		</div>
<%@ include file="fragments/footer.jsp"%>
	</div>	
</body>
</html>