<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-md-2 pt-2 sidebar">
	<ul class="nav flex-column">
		<li class="nav-item"><c:choose>
				<c:when test="${!empty person}">
					<c:choose>
						<c:when test="${person.role eq 'Admin' }">
							<div class="nav-link">
								<a href="UserManagerController"><i
									class="fa fa-user mr-2"></i>User Manager</a>
							</div>
						</c:when>
						<c:when test="${person.role eq 'User' }">
							<div class="nav-link">
								<a href="ContributionRecordsController?email=${person.email}"><i
									class="fa fa-bitcoin mr-2"></i>Your contributions</a>
							</div>
						</c:when>
					</c:choose>
					<div class="nav-link">
						<a href="CampaignListController?search="><i
							class="fa fa-user mr-2"></i>Campaign</a>
					</div>
					<div class="nav-link">
						<a href="page-user-change-password.jsp"><i
							class="fa fa-key mr-2"></i>Change Password</a>
					</div>
					<div class="nav-link">
						<a href="LogoutController"><i
							class="fa fa-sign-out mr-2"></i>Log Out</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="nav-link">
						<a href="page-user-login.jsp"><i
							class="fa fa-sign-in mr-2"></i>Log In</a>
					</div>
				</c:otherwise>
			</c:choose></li>
	</ul>
</div>
