<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="components/head-meta-tag.jsp"></c:import>
<title>Edit Campaign</title>
<c:import url="components/head-link-lib.jsp"></c:import>
</head>
<body>
	<!-- NAVBAR -->
	<c:import url="components/navbar.jsp"></c:import>

	<!--  -->
	<div class="container-fluid">
		<div class="row">
			<!-- SIDEBAR -->
			<c:import url="components/sidebar.jsp"></c:import>

			<!-- CONTENT -->
			<div class="col-md-10 pt-2">
				<div class="container mt-4">
					<!-- TITLE -->
					<div class="d-flex justify-content-between">
						<h2>
							Edit Campaign: <em>${campaign.title}</em>
						</h2>
						<c:if test="${campaign.status eq 'New' }">
							<a href="CampaignDeleteById?id=${campaign.id}"
								class="btn btn-danger text-white">Delete Campaign</a>
						</c:if>
					</div>
					<!-- FORM -->
					<form action="CampaignUpdateController" method="post"
						onsubmit="return validateForm()">
						<div class="form-group">
							<label for="id">ID:</label> <input type="text"
								class="form-control" id="id" name="id" value="${campaign.id}"
								readonly required>
						</div>
						<div class="form-group">
							<label for="owner">Owner:</label> <input type="text"
								class="form-control" id="owner" name="owner"
								value="${campaign.owner}" required>
						</div>
						<div class="form-group">
							<label for="phone">Phone Number:</label> <input type="tel"
								class="form-control" id="phone" name="phone" pattern="[0-9]{10}"
								value="${campaign.phone}" required> <small
								class="form-text text-muted">Phone number must be 10
								digits.</small>
						</div>
						<div class="form-group">
							<label for="email">Email:</label> <input type="email"
								class="form-control" id="email" name="email"
								value="${campaign.email}" required>
						</div>
						<div class="form-group">
							<label for="bankName">Bank Name:</label> <select
								class="form-control" id="bankName" name="bankName" required>
								<option value="${campaign.bankName}">${campaign.bankName}</option>
								<option value="Vietcombank">Vietcombank</option>
								<option value="BIDV">BIDV</option>
								<option value="VietinBank">VietinBank</option>
								<option value="Techcombank">Techcombank</option>
								<option value="Agribank">Agribank</option>
								<option value="VPBank">VPBank</option>
								<option value="Sacombank">Sacombank</option>
								<option value="ACB">ACB</option>
								<option value="MBBank">MBBank</option>
								<option value="SHB">SHB</option>
								<option value="HDBank">HDBank</option>
								<option value="OceanBank">OceanBank</option>
								<option value="TPBank">TPBank</option>
								<option value="SeABank">SeABank</option>
								<option value="ABB">ABB</option>
								<option value="PVcomBank">PVcomBank</option>
								<option value="VietABank">VietABank</option>
								<option value="NCB">NCB</option>
								<option value="OCB">OCB</option>
								<option value="LienVietPostBank">LienVietPostBank</option>
							</select>
						</div>
						<div class="form-group">
							<label for="bankAccount">Bank Account:</label> <input
								type="number" class="form-control" id="bankAccount"
								name="bankAccount" pattern="[0-9]+"
								value="${campaign.bankAccount}" required> <small
								class="form-text text-muted">Please enter only numeric
								values for the bank account.</small>
						</div>
						<div class="form-group">
							<label for="title">Title:</label> <input type="text"
								class="form-control" id="title" name="title"
								value="${campaign.title}" required>
						</div>
						<div class="form-group">
							<label for="status">Status:</label> <select class="form-control"
								id="status" name="status" required>
								<option value="${campaign.status}">${campaign.status}</option>
								<option value="new">New</option>
								<option value="open">Open</option>
								<option value="completed">Completed</option>
								<option value="closed">Closed</option>
							</select>
						</div>
						<div class="form-group">
							<label for="description">Description:</label>
							<textarea class="form-control" id="description"
								name="description" rows="4">${campaign.description}</textarea>
						</div>
						<div class="form-group">
							<label for="startDate">Start Date:</label> <input type="date"
								class="form-control" id="startDate" name="startDate"
								value="${campaign.startDate}" required>
						</div>
						<div class="form-group">
							<label for="endDate">End Date:</label> <input type="date"
								class="form-control" id="endDate" name="endDate"
								value="${campaign.endDate}" required> <small
								id="endDateError" class="form-text text-danger d-none">End
								date must be after start date.</small>
						</div>
						<div class="form-group">
							<label for="targetAmount">Target Amount:</label> <input
								type="number" step="0.01" class="form-control" id="targetAmount"
								name="targetAmount" value="${campaign.targetAmount}" required>
						</div>
						<div class="form-group">
							<label for="currentAmount">Current Amount:</label> <input
								type="number" step="0.01" class="form-control"
								id="currentAmount" name="currentAmount"
								value="${campaign.currentAmount}" readonly required>
								<!--  
								<small
								id="currentAmountError" class="form-text text-danger d-none">Current
								amount cannot be greater than target amount.</small>
								 -->
						</div>
						<button type="submit" class="btn btn-primary mb-3">Update
							Campaign</button>
					</form>
				</div>

			</div>
		</div>
	</div>

	<!-- LIBS & JS CONFIG-->
	<script>
		function validateForm() {
			const startDateInput = document.getElementById('startDate');
			const endDateInput = document.getElementById('endDate');
			const endDateError = document.getElementById('endDateError');

			const startDateValue = new Date(startDateInput.value);
			const endDateValue = new Date(endDateInput.value);

			if (endDateValue <= startDateValue) {
				endDateError.classList.remove('d-none');
				return false; // Block form submission
			} else {
				endDateError.classList.add('d-none');
			}

			<!-- 
			const targetAmountInput = document.getElementById('targetAmount');
			const currentAmountInput = document.getElementById('currentAmount');
			const currentAmountError = document
					.getElementById('currentAmountError');


			const targetAmountValue = parseFloat(targetAmountInput.value);
			const currentAmountValue = parseFloat(currentAmountInput.value);

			if (currentAmountValue > targetAmountValue) {
				currentAmountError.classList.remove('d-none');
				return false; // Block form submission
			} else {
				currentAmountError.classList.add('d-none');
			}
			-->

			return true; // Allow form submission
		}
	</script>
</body>
</html>
