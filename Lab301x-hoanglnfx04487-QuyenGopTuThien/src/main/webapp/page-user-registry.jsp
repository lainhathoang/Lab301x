<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="components/head-meta-tag.jsp"></c:import>
<title>Registry</title>
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
				<h2>Sign Up</h2>
				<form id="registrationForm" action="RegistryController"
					method="post">
					<div class="form-group">
						<label for="email">Email:</label> <input type="email"
							class="form-control" id="email" name="email" required />
					</div>
					<div class="form-group">
						<label for="phoneNumber">Phone Number (10 digits)</label> <input
							type="tel" class="form-control" id="phoneNumber"
							name="phoneNumber" pattern="[0-9]{10}" maxlength="10" required>
					</div>
					<div class="form-group">
						<label for="password">Password:</label> <input type="password"
							class="form-control" id="password" name="password" required
							pattern=".{6,}"
							title="Password must be at least 6 characters long" />
					</div>
					<div class="form-group">
						<label for="repeatPassword">Repeat Password:</label> <input
							type="password" class="form-control" id="repeatPassword"
							name="repeatPassword" required pattern=".{6,}"
							title="Password must be at least 6 characters long"
							oninput="checkPasswordMatch(this)" /> <span
							id="passwordMatchMessage" style="color: red"></span>
					</div>
					<!-- SUBMIT BUTTON & GO TO LOGIN BUTTON -->
					<button type="submit" class="btn btn-primary">Regist</button>
					<a href="page-user-login.jsp" class="btn btn-link">Go to Log in</a>
				</form>
			</div>
		</div>
	</div>

	<!-- Bootstrap & Jquery lib -->
	<c:import url="components/body-js-lib.jsp"></c:import>
	<!-- custom js for validate the form -->
	<script>
		$(document)
				.ready(
						function() {
							$("#registrationForm")
									.submit(
											function(event) {
												var password = $("#password")
														.val();
												var repeatPassword = $(
														"#repeatPassword")
														.val();

												if (password !== repeatPassword) {
													event.preventDefault();
													$("#repeatPassword")[0]
															.setCustomValidity("Passwords do not match.");
													$("#repeatPassword")[0]
															.reportValidity();
												} else {
													$("#repeatPassword")[0]
															.setCustomValidity("");
												}
											});

							// Reset custom validity on input change
							$("#repeatPassword").on("input", function() {
								$(this)[0].setCustomValidity("");
							});
						});
	</script>
</body>
</html>
