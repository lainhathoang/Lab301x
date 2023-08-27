<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="components/head-meta-tag.jsp"></c:import>
<title>Campaign Detail</title>
<c:import url="components/head-link-lib.jsp"></c:import>
</head>
<body>
	<!-- NAVBAR -->
	<c:import url="components/navbar.jsp"></c:import>

	<!-- CONTENT -->
	<div class="container-fluid">
		<div class="row">
			<!-- Left Navigation Bar - 2 columns wide on small and larger screens -->
			<c:import url="components/sidebar.jsp"></c:import>

			<div class="col-md-10">
				<div class="row mt-3">
					<div class="col-md-9">
						<h2>${requestScope.campaign.title}</h2>
						<ul class="list-group mb-4 mt-3">
							<li class="list-group-item"><strong>Campaign ID:</strong>
								${requestScope.campaign.id}</li>
							<li class="list-group-item"><strong>Owner:</strong>
								${requestScope.campaign.owner}</li>
							<li class="list-group-item"><strong>Email:</strong>
								${requestScope.campaign.email}</li>
							<li class="list-group-item"><strong>Phone Number:</strong>
								${requestScope.campaign.phone}</li>
							<li class="list-group-item"><strong>Description:</strong>
								${requestScope.campaign.description}</li>
							<li class="list-group-item text-danger"><strong>Bank
									name:</strong> ${requestScope.campaign.bankName}</li>
							<li class="list-group-item text-danger"><strong>Bank
									account:</strong> ${requestScope.campaign.bankAccount}</li>
							<li class="list-group-item"><strong>Start Date:</strong>
								${requestScope.campaign.startDate}</li>
							<li class="list-group-item"><strong>End Date:</strong>
								${requestScope.campaign.endDate}</li>
							<li class="list-group-item"><strong>Current Amount:</strong>
								${requestScope.campaign.currentAmount}</li>
							<li class="list-group-item"><strong>Target Amount:</strong>
								${requestScope.campaign.targetAmount}</li>
							<li class="list-group-item"><strong>Status:</strong> Open</li>
						</ul>
					</div>
					<div class="col-md-3">
						<c:if
							test="${sessionScope.person.role != 'Admin' &&  requestScope.campaign.status != 'Closed'}">
							<form id="donateForm" method="post" action="ContributionFormController">
								<h2>Donate now</h2>
								<div class="form-group">
									<label for="campaignSelect">Campaign Id:</label> <input
										type="text" class="form-control" id="campaignSelect"
										name="campaignId" required value="${requestScope.campaign.id}"
										readonly required>
								</div>

								<div class="form-group">
									<label for="email">Email:</label> <input type="email"
										class="form-control" id="email" name="email" required
										value="${sessionScope.person != null ? sessionScope.person.email : '' }">
								</div>

								<div class="form-group">
									<label for="contributionMethod">Contribution Method</label> <select
										class="form-control" id="contributionMethod"
										name="contributionMethod">
										<option value="Bank Transfer">Bank Transfer</option>
										<!-- 
								<option value="Zalo Pay">Zalo Pay</option>
								<option value="Momo">Momo</option>
								<option value="Paypal">Paypal</option>
								 -->
									</select>
								</div>

								<div class="form-group">
									<label for="donateAmount">Donate Amount:</label> <input
										type="number" class="form-control" id="donateAmount"
										name="donateAmount" required>
								</div>
								<button type="submit" class="btn btn-primary">Submit</button>
							</form>
						</c:if>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<div class="container-fluid mt-4">
							<h3>Contribution Records of "${requestScope.campaign.title}"</h3>
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>Contribution ID</th>
										<th>Campaign ID</th>
										<th>Email</th>
										<th>Donation Time</th>
										<th>Contribution Amount</th>
										<th>Contribution Method</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${requestScope.contributionRecords}" var="r">
										<tr>
											<td>${r.contributionId}</td>
											<td>${r.campaignId}</td>
											<td>${r.email}</td>
											<td>${r.donationTime}</td>
											<td>${r.contributionAmount}</td>
											<td>${r.contributionMethod}</td>
											<td><c:if
													test="${sessionScope.person.role == 'Admin' && r.verify == 1}">
													<a
														href="ContributionActionController?verify=0&&contributionId=${r.contributionId}&campaignId=${r.campaignId}"
														class="btn btn-outline-danger">Reject</a>
												</c:if> <c:if
													test="${sessionScope.person.role != 'Admin' && r.verify == 1}">
													<p class="text-success">Approved</p>
												</c:if> <c:if
													test="${sessionScope.person.role == 'Admin' && r.verify == 0}">
													<a
														href="ContributionActionController?verify=1&&contributionId=${r.contributionId}&campaignId=${r.campaignId}"
														class="btn btn-outline-success">Approve</a>
												</c:if> <c:if
													test="${sessionScope.person.role != 'Admin' && r.verify == 0}">
													<p class="text-secondary">Pending...</p>
												</c:if></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- LIB -->
	<c:import url="components/body-js-lib.jsp"></c:import>
</body>
</html>