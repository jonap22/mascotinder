<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pet_register" scope="request" value="${types}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Pet</title>

<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">

<!-- CSS style sheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css">

<!-- Font Awesome -->
<script src='https://kit.fontawesome.com/a076d05399.js'
	crossorigin='anonymous'></script>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">

</head>
<body>
	<nav class="navbar navbar-dark banner-config rad-border">
		<div class="container-fluid">
			<span class="navbar-brand mb-0 h1  app-title"> MascoTinder <i
				class="fas fa-dog" style="font-size: 34px"></i>
			</span> <span class="navbar-brand mb-0 h3">
				<button onclick="myFunction()" class="text-light dropbtn">
					<i class="bi bi-person-circle" style="font-size: 24px"></i>
					${sessionScope.loggedOwner.getName()}
				</button> <span id="myDropdown" class="dropdown-content"> <a
					class="" href="ListPetsController"> My pets </a> <a
					class="" href="LoginController"> Logout </a>
			</span>
			</span>
		</div>
	</nav>
	<div class="container-conf">
		<div class="list-title" style="width: 100%">
			<p class="h1 my-pets-text" style="width: 100%">Register my Pet</p>
		</div>
	</div>
	<form method="post" action="RegisterController" class="form-table-config">
		<fieldset>
			<div class="container mw-90 w-90 flex-column">
				<div class="row my-pets-text">
					<h3 style="font-weight: bold;">About my Pet</h3>
				</div>
				<!--Name's pet-->
				<div class="row general-title color-blue">
					<p>Name:</p>
				</div>
				<div class="row-sm-12 center-select">
					<input type="text" name="pet_name" class="form-content-name" required>
				</div>
				<!--Field of selection of age and type-->
				<div class="row general-title color-blue">
					<!--Field age-->
					<p>Age:</p>
				</div>
				<div class="row-sm-12 center-select custom-select">
					<select name="pet_age" id="pet_age" required>
						<option value="0">Select option:</option>
						<c:forEach begin="1" end="20" varStatus="loop">
							<option value="${loop.index}">${loop.index}</option>
						</c:forEach>
					</select>
				</div>
				<div class="row general-title color-blue">
					<!--Field type-->
					<p>Type:</p>
				</div>
				<div class="row-sm-12 custom-select center-select">
					<select name="pet_type" id="pet_type" required>
						<option value="0">Select option:</option>
						<c:forEach items="${types}" var="types">
							<option value="${types}">${types}</option>
						</c:forEach>
						<option value="cat">cat</option>
						<option value="bird">bird</option>
						<option value="gorilla">gorilla</option>
						<option value="wombat">wombat</option>
						<option value="camel">camel</option>
					</select>
				</div>
				<div class="row general-title color-blue">
					<!--Field sex-->
					<p>Sex:</p>
				</div>
				<div class="row-sm-12 custom-select center-select">
					<select name="pet_sex" id="pet_sex" required>
						<option value="0">Select option:</option>
						<option>female</option>
						<option>male</option>
					</select>
				</div>
				<div class="row general-title color-blue">
					<p>Photos:</p>
				</div>
				<!--Field photos-->
				<div>
					<div>
						<!--Upload photo-->
						<div class="container">
							<div class="row file-container file-text center-select">
								<input type="file" class="custom-file-input" name="pet_image_1" accept="image/png, image/jpeg" required>
							</div>

							<div class="row file-container file-text center-select">
								<input type="file" class="custom-file-input" name="pet_image_2"	accept="image/png, image/jpeg" required>
							</div>

							<div class="row file-container file-text center-select">
								<input type="file" class="custom-file-input" name="pet_image_3"	accept="image/png, image/jpeg" required>
							</div>
						</div>
					</div>
				</div>
				
				<div>
					<input type="submit" value="Save" id="submit-btn" class="text-light save-text">
				</div>
			</div>
		</fieldset>
	</form>
	<script>
		/* Toggle between hiding and showing the dropdown content */
		function myFunction() {
			document.getElementById("myDropdown").classList.toggle("show");
		}

		/* Close the dropdown menu whenever the user clicks outside of it */
		window.onclick = function(event) {
			if (!event.target.matches('.dropbtn')) {
				var dropdowns = document
						.getElementsByClassName("dropdown-content");
				var i;
				for (i = 0; i < dropdowns.length; i++) {
					var openDropdown = dropdowns[i];
					if (openDropdown.classList.contains('show')) {
						openDropdown.classList.remove('show');
					}
				}
			}
		}
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

				c
						.addEventListener(
								"click",
								function(e) {
									/* When an item is clicked, update the original select box, and the selected item: */
									var y, i, k, s, h, sl, yl;
									s = this.parentNode.parentNode
											.getElementsByTagName("select")[0];
									sl = s.length;
									h = this.parentNode.previousSibling;
									for (i = 0; i < sl; i++) {
										if (s.options[i].innerHTML == this.innerHTML) {
											s.selectedIndex = i;
											h.innerHTML = this.innerHTML;
											y = this.parentNode
													.getElementsByClassName("same-as-selected");
											yl = y.length;
											for (k = 0; k < yl; k++) {
												y[k].removeAttribute("class");
											}
											this.setAttribute("class",
													"same-as-selected");
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