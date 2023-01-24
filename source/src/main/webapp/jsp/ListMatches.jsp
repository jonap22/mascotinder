<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="requester_pet" scope="request" value="${requester_pet}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Matches</title>

<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">

<!-- CSS style sheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

	<%@include file="../templates/banner.html"%>

	<div class="container-conf">
		<div class="list-title" style="width: 100%">
			<p class="h1 my-pets-text" style="width: 100%">Matches of ${requester_pet.name}</p>
		</div>
	</div>
	
	<div class="card-total-container">
		<c:forEach items="${pets}" var="applicant_pet">
			<div class="row card-container border-conf">
				<!-- Pet profile photo -->
				<div class="col-3 profile-pet-photo">
					<img
						src="data:image/png;base64,${applicant_pet.images[0].base64Image}" />
				</div>
				<!-- Pet description -->
				<div class="col mh-100">
					<div class="row mh-100 ht-100">
						<h1 id="pet-title">${applicant_pet.name}</h1>
					</div>
					<div class="row mh-100 ht-100 color-blue">
						<div class="center-description">
							<p>${requester_pet.name} has a match with
								${applicant_pet.name}. Your future partner is a ${applicant_pet.age} year old
								${applicant_pet.sex}.</p>
						</div>
					</div>
				<div class="row mh-100 pet-options-row ht-100">
						<div class="col">
							<div class="preferences-text">
								<a href="ChatController?receiver_owner_email=${applicant_pet.owner.email}"> Contact ${applicant_pet.name}'s owner. </a>
							</div>
						</div>
						<div class="col color-blue">
							<p class="center-text">	</p>
						</div>
						<div class="col color-blue"> <p class="center-text">
							</p>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

</body>
</html>