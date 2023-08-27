<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- TOP NAVBAR -->
<!-- Image and text -->
<nav class="navbar navbar-light bg-light justify-content-between">
	<a class="navbar-brand" href="index.jsp"> <img
		src="https://www.svgrepo.com/show/25475/money.svg"
		width="30" height="30" class="d-inline-block align-top" alt="" />
		<span><b>Lab301x - Campaign</b></span>
	</a>
	<!-- Div inside the navbar -->
	<div class="d-flex align-items-center" id="navbar-right-section">
		<c:choose>
			<c:when test="${!empty person}">
				<c:choose>
					<c:when test="${ person.role == 'Admin' }">
						<p class="mb-0 mr-3 text-primary">
							Hi! <strong>${person.email}</strong> <em>[Admin]</em>
						</p>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${ person.role == 'User' }">
						<p class="mb-0 mr-3">
							Hi! <strong>${person.email}</strong>
						</p>
					</c:when>
				</c:choose>
			</c:when>
			<c:otherwise>
				<p>
					donate !! <strong>donate !!</strong> donate !!
				</p>
			</c:otherwise>
		</c:choose>
		<!-- Content 
    <p class="mb-0 mr-3">hello mah fen!</p>
    <!-- Button 
    <a href="page-user-login.jsp" type="button" class="btn btn-outline-primary">Log In</a>
    -->
	</div>
</nav>
