<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="components/head-meta-tag.jsp"></c:import>
<title>Campaign List</title>
<c:import url="components/head-link-lib.jsp"></c:import>
</head>
<body>
	<!-- NAVBAR -->
	<c:import url="components/navbar.jsp"></c:import>



	<div class="container-fluid">
		<div class="row">
			<!-- SIDE-BAR -->
			<c:import url="components/sidebar.jsp"></c:import>

			<!-- CONTENT -->
			<div class="col-md-8">
				<h1>Campaign List</h1>
				<c:if test="${sessionScope.person.role == 'Admin'}">
					<a href="page-campaign-create.jsp" class="btn btn-outline-secondary my-2">Create</a>
				</c:if>
				
				<!-- search field -->
				<form id="searchForm" action="CampaignListController" method="get">
					<div class="input-group mb-3">
						<input name="search" type="text" class="form-control"
							placeholder="Search Id / Owner / Email / Phone"
							aria-label="Search Campaign" id="searchInput">
						<div class="input-group-append">
							<button class="btn btn-outline-secondary" type="submit"
								id="searchButton">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
				</form>
				<!-- SEARCH BY CAMPAIGN'S STATUS -->
				<div class="my-3 d-flex justify-content-between">
					<div>
						<a href="CampaignSearchByStatusController?status=new"
							class="btn btn-outline-success">New</a> <a
							href="CampaignSearchByStatusController?status=open"
							class="btn btn-outline-primary">Open</a> <a
							href="CampaignSearchByStatusController?status=completed"
							class="btn btn-outline-secondary">Completed</a> <a
							href="CampaignSearchByStatusController?status=closed"
							class="btn btn-outline-dark">Closed</a>
					</div>
					<a href="CampaignListController?search="
						class="btn btn-outline-danger">All</a>
				</div>
				<!-- END SEARCH BY CAMPAIGN STATUS -->
				<%-- Loop through the campaigns and generate cards dynamically --%>
				<c:forEach items="${campaigns}" var="campaign">
					<div class="card mb-3">
						<div class="row flex-row no-gutters">
							<div class="col-md-6">
								<div class="card-body">
									<h5 class="card-title">${campaign.title}</h5>
									<p class="card-text">${campaign.description}</p>
									<p class="card-text">
										<strong>Id:</strong> ${campaign.id}<br> <strong>Owner:</strong>
										${campaign.owner}<br> <strong>Email:</strong>
										${campaign.email}<br> <strong>Phone:</strong>
										${campaign.phone}<br>
									</p>
								</div>
							</div>
							<div class="col-md-3">
								<div class="card-body">
									<p class="card-text">
										<strong>Current Amount:</strong> ${campaign.currentAmount}<br>
										<strong>Total Amount:</strong> ${campaign.targetAmount}<br>
										<br>
										<strong>Bank Name:</strong> ${campaign.bankName}<br>
										<strong>Bank Account:</strong> ${campaign.bankAccount}<br>
									</p>
									<a href="CampaignDetailController?campaignId=${campaign.id}"
										class="btn btn-primary">Detail</a>
								</div>
							</div>
							<div class="col-md-3">
								<div class="card-body">
									<p class="card-text">
										<strong>Status:</strong> ${campaign.status}<br> <strong>Start
											date:</strong> ${campaign.startDate}<br> <strong>End
											date:</strong> ${campaign.endDate}<br> <strong>Remaining:
										</strong>
										<c:if
											test="${campaign.calculateDaysRemaining(campaign.endDate) > 0}">
												${campaign.calculateDaysRemaining(campaign.endDate)}
											</c:if>
										<c:if
											test="${campaign.calculateDaysRemaining(campaign.endDate) <= 0}">
												0
											</c:if>
										<br />
									</p>
									<c:if
										test="${sessionScope.person.role == 'Admin' && campaign.status != 'Closed'}">
										<a href="CampaignEditController?id=${campaign.id}"
											class="btn btn-outline-secondary donate-btn">Edit</a>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>

			<!-- DONATE FORM -->
			<div class="col-md-2 mt-5">
				<c:if test="${sessionScope.person.role != 'Admin'}">
					<form id="donateForm" method="post" action="ContributionFormController">
						<h2>Donate now</h2>
						<div class="form-group">
							<label for="campaignSelect">Campaign:</label> <select
								class="form-control" id="campaignSelect" name="campaignId"
								required>
								<c:forEach items="${sessionScope.campaigns}" var="campaign">
									<c:if test="${campaign.status != 'Closed'}">
										<option value="${campaign.id}" id="campaignTitle">${campaign.title}</option>
									</c:if>
								</c:forEach>
							</select>
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
	</div>

	<!-- Add Bootstrap JS and jQuery -->
	<!-- LIB -->
	<c:import url="components/body-js-lib.jsp"></c:import>
</body>
</html>