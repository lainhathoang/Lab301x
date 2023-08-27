<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="components/head-meta-tag.jsp"></c:import>
<title>Change Password</title>
<c:import url="components/head-link-lib.jsp"></c:import>
</head>
<body>
	<!-- NAVBAR -->
	<c:import url="components/navbar.jsp"></c:import>

	<div class="container mt-5">
		<h1>Change password of: ${emailOTP}</h1>
		<form id="registrationForm" action="ChangePasswordController"
			method="post">
			<div class="form-group">
				<label for="password">New Password:</label> <input type="password"
					class="form-control" id="password" name="password" required
					pattern=".{6,}" title="Password must be at least 6 characters long" />
			</div>
			<div class="form-group">
				<label for="repeatPassword">Repeat New Password:</label> <input
					type="password" class="form-control" id="repeatPassword"
					name="repeatPassword" required pattern=".{6,}"
					title="Password must be at least 6 characters long"
					oninput="checkPasswordMatch(this)" /> <span
					id="passwordMatchMessage" style="color: red"></span>
			</div>
			<!-- SUBMIT BUTTON & GO TO LOGIN BUTTON -->
			<button type="submit" class="btn btn-primary">Change</button>
		</form>
	</div>

	<!-- Bootstrap & JQuery libs -->
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