<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="components/head-meta-tag.jsp"></c:import>
<title>Contribution Records</title>
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

			<div class="col-md-10 content">
				<div class="container mt-4">
					<c:choose>
						<c:when test="${requestScope.campaignName != null}">
							<h2>Contribution Records of "${requestScope.campaignName}"</h2>
						</c:when>
						<c:when test="${requestScope.email != null}">
							<h2>Contribution Records of "${requestScope.email}"</h2>
						</c:when>
					</c:choose>
					
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
									<td><c:if test="${r.verify == 1}">
											<p class="text-success">Approved</p>
										</c:if> <c:if test="${r.verify != 1}">
											<p class="text-secondary">Pending ...</p>
										</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- LIB -->
	<c:import url="components/body-js-lib.jsp"></c:import>
</body>
</html>