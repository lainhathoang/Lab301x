<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="components/head-meta-tag.jsp"></c:import>
<title>Forgot Password</title>
<c:import url="components/head-link-lib.jsp"></c:import>
</head>
<body>
	<!-- NAVBAR -->
	<c:import url="components/navbar.jsp"></c:import>

	<!-- CONTENT -->
	<div class="container">
		<div class="row">
			<!-- Right Content Area -->
			<div class="col-md-10 content">
				<div class="container">
					<h2>Enter your email to receive instructions:</h2>
					<form action="ForgotPasswordController" method="post">
						<div class="form-group">
							<label for="email">Email:</label> <input type="email"
								class="form-control" id="email" name="email" required
								pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" />
						</div>
						<button type="submit" class="btn btn-primary">Send me!</button>
						<a href="page-user-login.jsp" class="btn btn-link">Back to Login</a>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS -->
	<!-- LIB -->
	<c:import url="components/body-js-lib.jsp"></c:import>
</body>
</html>
