<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pet_preference" scope="request" value="${preference}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Preference</title>
<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">

<!-- Font Awesome -->
<script src='https://kit.fontawesome.com/a076d05399.js'
	crossorigin='anonymous'></script>

<!-- CSS style sheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

	<%@include file="../templates/banner.html"%>

	<div class="container-conf">
		<div class="list-title">
			<p class="h1 my-pets-text">Preferences</p>
		</div>
	</div>
	<form method="post" action="PreferenceController" class="form-table-config">
		<div class="container mw-90 w-90 flex-column">
			<div class="row my-pets-text">
				<h3 style="font-weight: bold;">My pet preferences</h3>
			</div>
			<div class="row general-title color-blue">
				<p>Type:</p>
			</div>
			<div class="row-sm-12 center-select custom-preference">
				<p ${(preference.pet.type != null) ? preference.setType(preference.pet.type) :  preference.setType('Null')}>${preference.type}</p>
			</div>
			<div class="row general-title color-blue">
				<p>Sex:</p>
			</div>
			<div class="row-sm-12 center-select custom-preference">
				<p ${(preference.pet.sex != null && preference.pet.sex == 'male') ? preference.setSex('female') :  preference.setSex('male')}>${preference.sex}</p>
			</div>
			<div class="row general-title color-blue">
				<p>Age range:</p>
			</div>
			<div class="row">
				<div class="col text-center">
					<div class="text-center general-title color-blue">Min age</div>
					<div class="row custom-select center-select">
						<select name="pet_minimum_age" id="pet_minimum_age">
							<option value="0">Select option:</option>
							<c:forEach begin="1" end="20" varStatus="loop">
								<option value="${loop.index}" ${(preference != null && preference.minimumAge == loop.index) ? 'selected' : ''}>${loop.index}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="col text-center">
					<div class="text-center general-title color-blue">Max age</div>
					<div class="row custom-select center-select">
						<select name="pet_maximum_age" id="pet_maximum_age">
							<option value="0">Select option:</option>
							<c:forEach begin="1" end="20" varStatus="loop">
								<option value="${loop.index}"
									${(preference != null && preference.maximumAge == loop.index) ? 'selected' : ''}>${loop.index}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" name="pet_id" value="${pet_id}">
		<div>
			<input type="submit" value="Save" id="submit-btn" class="text-light save-text">
		</div>
	</form>

	<script>
		/* Attributes */
		var x, i, j, l, ll, selElmnt, a, b, c;
		
		/* Look for any elements with the class "custom-select": */
		x = document.getElementsByClassName("custom-select");
		l = x.length;
		
		for (i = 0; i < l; i++) {
			selElmnt = x[i].getElementsByTagName("select")[0];
		  	ll = selElmnt.length;
		  	
		  	/* For each element, create a new DIV that will act as the selected item: */
		  	a = document.createElement("DIV");
		  	a.setAttribute("class", "select-selected");
		  	a.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;
		  	x[i].appendChild(a);
		  	
		  	/* For each element, create a new DIV that will contain the option list: */
		  	b = document.createElement("DIV");
		  	b.setAttribute("class", "select-items select-hide");
		
			for (j = 1; j < ll; j++) {
			    /* For each option in the original select element, create a new DIV that will act as an option item: */
			    c = document.createElement("DIV");
			    c.innerHTML = selElmnt.options[j].innerHTML;
			    
			    c.addEventListener("click", function(e) {
			    	/* When an item is clicked, update the original select box, and the selected item: */
			        var y, i, k, s, h, sl, yl;
			        s = this.parentNode.parentNode.getElementsByTagName("select")[0];
			        sl = s.length;
			        h = this.parentNode.previousSibling;
			        for (i = 0; i < sl; i++) {
			        	if (s.options[i].innerHTML == this.innerHTML) {
			        		s.selectedIndex = i;
			            	h.innerHTML = this.innerHTML;
			            	y = this.parentNode.getElementsByClassName("same-as-selected");
			            	yl = y.length;
				            for (k = 0; k < yl; k++) {
				            	y[k].removeAttribute("class");
				            }
				            this.setAttribute("class", "same-as-selected");
				            break;
			          }
			        }
			        h.click();
			    });
			    b.appendChild(c);
			  }
			  x[i].appendChild(b);
			  a.addEventListener("click", function(e) {
				  	/* Close any other select boxes, and open/close the current select box: */
				  	e.stopPropagation();
			    	closeAllSelect(this);
			    	this.nextSibling.classList.toggle("select-hide");
			    	this.classList.toggle("select-arrow-active");
			  });
		}
		
		/* Close all selected boxes except the current one*/
		function closeAllSelect(elmnt) {
			var x, y, i, xl, yl, arrNo = [];
		  	x = document.getElementsByClassName("select-items");
		  	y = document.getElementsByClassName("select-selected");
		  	xl = x.length;
		  	yl = y.length;
		  	
		  	for (i = 0; i < yl; i++) {
		  		if (elmnt == y[i]) {
		  			arrNo.push(i)
		    	} else {
		      		y[i].classList.remove("select-arrow-active");
		    	}
		  	}
		  	
		  	for (i = 0; i < xl; i++) {
		    	if (arrNo.indexOf(i)) {
		      		x[i].classList.add("select-hide");
		    	}
		  	}
		}
	
		/* Close all the boxes whenever the user clicks outside the select box items */
		document.addEventListener("click", closeAllSelect);
		
	</script>
</body>
</html>