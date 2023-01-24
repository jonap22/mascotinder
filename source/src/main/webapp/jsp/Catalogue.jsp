<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pet_id" scope="request" value="${pet_id}" />

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
<title>Pets Catalogue</title>

<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<!-- CSS style sheet -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">

<script src="https://code.jquery.com/jquery-3.6.1.min.js" 
	    integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" 
	    crossorigin="anonymous"></script>

<script	src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
		
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>

</head>
<body>

	<script>
	
		function addNode(active, id, name, age, image1, image2, image3) {			
			var nodeHTML = document.createElement("div");			
			nodeHTML.className = "carousel-item" + active + "catalogue-pet-photo";
			
			var stringHTML = "<div class='container-conf '><div class='d-block text-center'" +
			" style='margin-left: auto; margin-right: auto'>"+
			"<p style='display: none' name='requesterId'>"+id+"</p>"+
			"<p class='h2 my-pets-text' id='namePet'>"+name+"</p><p class='h3 my-pets-text '"+
			"id='agePet'>"+age+"</p></div></div>" + 
			"<div style=''><img class='rounded mx-auto d-block pet-photo' height: 150px src='data:image/png;base64,"+image1+"'></div>"+
			"<div style='display: none'><img class='rounded mx-auto d-block pet-photo' height: 150px src='data:image/png;base64,"+image2+"'></div>"+
			"<div style='display: none'><img class='rounded mx-auto d-block pet-photo' height: 150px src='data:image/png;base64,"+image3+"'></div>"+
			"</div>";
			
			stringHTML = stringHTML.trim();
			nodeHTML.innerHTML = stringHTML;
			
			return nodeHTML;			
		}
				
		function prev(){
			var petPhoto1Path = document.querySelector("#petCarouselInner > div.carousel-item.catalogue-pet-photo.active > div:nth-child(2)");
			var petPhoto2Path = document.querySelector("#petCarouselInner > div.carousel-item.catalogue-pet-photo.active > div:nth-child(3)");
			var petPhoto3Path = document.querySelector("#petCarouselInner > div.carousel-item.catalogue-pet-photo.active > div:nth-child(4)");
			var indexOfPhotos = document.querySelector("#actualPhoto").textContent;
			
			if(indexOfPhotos > 0){
				indexOfPhotos -= 1;
			}else{
				indexOfPhotos = 2;
			}
			document.querySelector("#actualPhoto").textContent = indexOfPhotos;
			
			switch(indexOfPhotos){
				case 0:
					petPhoto1Path.style = "";
					petPhoto2Path.style = "display: none";
					petPhoto3Path.style = "display: none";
					break;
				case 1:
					petPhoto1Path.style = "display: none";
					petPhoto2Path.style = "";
					petPhoto3Path.style = "display: none";
					break;
				case 2:
					petPhoto1Path.style = "display: none";
					petPhoto2Path.style = "display: none";
					petPhoto3Path.style = "";
					break;
			}
		}
		
		
		function next(){
			var petPhoto1Path = document.querySelector("#petCarouselInner > div.carousel-item.catalogue-pet-photo.active > div:nth-child(2)");
			var petPhoto2Path = document.querySelector("#petCarouselInner > div.carousel-item.catalogue-pet-photo.active > div:nth-child(3)");
			var petPhoto3Path = document.querySelector("#petCarouselInner > div.carousel-item.catalogue-pet-photo.active > div:nth-child(4)");
			var indexOfPhotos = document.querySelector("#actualPhoto").textContent;
			indexOfPhotos = Number(indexOfPhotos);
			
			if(indexOfPhotos < 2){
				indexOfPhotos += 1;
			}else{
				indexOfPhotos = 0;
			}
			
			document.querySelector("#actualPhoto").textContent = indexOfPhotos;
			
			switch(indexOfPhotos){
				case 0:
					petPhoto1Path.style = "";
					petPhoto2Path.style = "display: none";
					petPhoto3Path.style = "display: none";
					break;
				case 1:
					petPhoto1Path.style = "display: none";
					petPhoto2Path.style = "";
					petPhoto3Path.style = "display: none";
					break;
				case 2:
					petPhoto1Path.style = "display: none";
					petPhoto2Path.style = "display: none";
					petPhoto3Path.style = "";
					break;
			}
			
		}
		
		function processMatchOperation(isLike) {
			const queryString = window.location.search;
			const urlParams = new URLSearchParams(queryString);
			
			var requesterId = urlParams.get("pet_id");
			var applicantId = document.querySelector("#petCarouselInner > div.carousel-item.catalogue-pet-photo.active > div.container-conf > div > p:nth-child(1)").textContent;	
			
			requesterId = Number(requesterId);
			applicantId = Number(applicantId);
			
			$.ajax({
				url: "/mascotinder/MatchesController",
				type: "POST",
				data: {
					requester_id: requesterId,
					applicant_id: applicantId,
					is_like: isLike,
				},
				success: function(result) {
					if (isLike) {
						console.log("MatchesController received the data correctly while processing a LIKE!");
					}
					else {
						console.log("MatchesController received the data correctly while processing a DISLIKE!");
					}					
					
					var elementToDelete = document.querySelector("#petCarouselInner > div.carousel-item.catalogue-pet-photo.active");
					elementToDelete.remove();
					
					var scriptToDelete = document.querySelector("#petCarouselInner > script:first-child");
					scriptToDelete.remove();
										
					handleLastElement();
				}
			});			
		}
		
		function handleLastElement() {
			var numberOfElements = document.getElementById("petCarouselInner").childElementCount / 2;
			
			if (numberOfElements == 0) {
				handleNoMoreElements();
			}
		}
		
		function handleNoMoreElements() {
			let message = document.createElement("span");
			message.setAttribute("id", "last-element-message");
			message.setAttribute("class", "last-message");
			
			let text = document.createTextNode("Oops! It seems that we have no more recommendations for you.");
			
			message.appendChild(text);
			document.getElementById("petCarouselInner").appendChild(message);
			document.getElementById("petCarouselInner").setAttribute("style", "text-align: center; height: 200px; padding: 100px 0px;");
			
			document.getElementById("prev-button").remove();
			document.getElementById("next-button").remove();
			document.getElementById("control-buttons").remove();
		}
		
	</script>

	<!-- Nav -->
	<%@include file="../templates/banner.html"%>

	<!-- Carousel -->
	<div id="petCarousel" class="carousel slide" data-interval="false">

		<div id="petCarouselInner" class="carousel-inner">
			<c:if test="${pets.size() > 0}">
				<c:forEach items="${pets}" var="pet" varStatus="vs">
					<script>	
						var active = " ";
						if("${vs.index}" == 0){
							active = " active ";
						}
						var bodyHTML = document.getElementById("petCarouselInner");
						var nodeHTML = addNode(active,"${pet.id}", "${pet.name}", "${pet.age}", "${pet.images[0].base64Image}", "${pet.images[1].base64Image}", "${pet.images[2].base64Image}");
						bodyHTML.appendChild(nodeHTML);
					</script>
				</c:forEach>	
			</c:if>
		</div>

		<a id="prev-button" class="carousel-control-prev" href="#imageCarousel" role="button">
			<span class="carousel-control-prev-icon" aria-hidden="true"
			onclick="prev();"></span> <span class="sr-only">Previous</span></a> 
			
		<a id="next-button" style="max-height: 180px; margin: auto 0px;" class="carousel-control-next" href="#imageCarousel" role="button">
			<span class="carousel-control-next-icon" aria-hidden="true"
			onclick="next();"></span> <span class="sr-only">Next</span></a>

	</div>

	<div id="control-buttons" class="position-relative nav-like">
	
		<!-- Like -->
		<a class="btn btn-primary position-absolute top-100 end-50 like"
			style="background-color: #319EFF; border: none;" href="#petCarousel"
			role="button" data-slide="next" onclick="processMatchOperation(true);"> <i
			class="fa fa-thumbs-up prueba"></i>
		</a>
		
		<!-- DisLike -->
		<a class="btn btn-primary position-absolute top-100 start-50 "
			style="background-color: #319EFF; border: none;" href="#petCarousel"
			role="button" data-slide="next" onclick="processMatchOperation(false);"> <i 
			class="fa fa-thumbs-down"></i>
		</a>
		
	</div>
	
	<div style="display: none" id="actualPhoto">0</div>

	<c:if test="${pets.size() == 0}">
		<script>
			handleNoMoreElements();	
		</script>
	</c:if>

</body>
</html>