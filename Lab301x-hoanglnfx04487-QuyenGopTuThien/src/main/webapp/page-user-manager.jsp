<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="components/head-meta-tag.jsp"></c:import>
<title>User Manager</title>
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

			<!-- Right Part with Table - 10 columns wide on small and larger screens -->
			<div class="col-md-10 content">
				<h1>Person Table</h1>
				<div class="d-flex justify-content-between">
					<div>
						<button class="btn btn-secondary" onClick="updateUser('delete')">Delete</button>
						<button class="btn btn-success" onClick="updateUser('active')">Active</button>
						<button class="btn btn-danger" onClick="updateUser('ban')">Ban</button>
					</div>
					<!-- Search form -->
					<form class="form-inline" method="post"
						action="UserSearchController">
						<!-- Search email field -->
						<input type="text" name="search" class="form-control mr-2"
							placeholder="Search Email or Phone">

						<!-- Search icon button -->
						<button type="submit" class="btn btn-secondary">
							<i class="fa fa-search"></i>
						</button>
					</form>
				</div>

				<!-- USER LIST -->
				<table class="table table-bordered table-striped my-3">
					<thead>
						<tr>
							<th class="text-center"><input type="checkbox"
								id="selectAll"></th>
							<th>ID</th>
							<th>Email</th>
							<th>Phone</th>
							<th>Role</th>
							<th>Status</th>
							<th>Last Login</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.list}" var="p">
							<tr>
								<td class="text-center"><input type="checkbox"
									name="toBeChanged" value="${p.id }"></td>
								<td>${p.id }</td>
								<td>${p.email }</td>
								<td>${p.phone }</td>
								<td><c:if test="${p.role eq 'Admin'}">
										<Strong>Admin </Strong>
										<i class="fa fa-exchange mr-2"
											onClick="changeRole(${p.id }, 'Admin')"></i>
									</c:if> <c:if test="${p.role eq 'User'}">
	                    		User <i class="fa fa-exchange mr-2"
											onClick="changeRole(${p.id }, 'User')"></i>
									</c:if></td>
								<td><c:if test="${p.status eq 'Active'}">
										<span class="badge badge-success">Active</span>
									</c:if> <c:if test="${p.status eq 'Banned'}">
										<span class="badge badge-danger">Banned</span>
									</c:if></td>
								<td>${p.lastLogin }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- END USER LIST -->

				<!-- page controller -->
				<div class="mb-5">
					<nav aria-label="">
						<ul class="pagination justify-content-center">
							<%-- Kiem tra xem current page dang o trang bao nhieu de disable item mang gia tri cua page do --%>
							<li
								class="page-item <c:if test="${requestScope.currentPage == 1}">disabled</c:if>">
								<a class="page-link"
								href="${request.getContextPath()}?page=${requestScope.currentPage - 1}&search=${requestScope.search}">Previous</a>
							</li>

							<c:forEach begin="1" end="${requestScope.numPage}" var="i">
								<li
									class="page-item <c:if test="${requestScope.currentPage == i}">disabled</c:if>">
									<a class="page-link"
									href="${request.getContextPath()}?page=${i}&search=${requestScope.search}">${i}</a>
								</li>
							</c:forEach>

							<li
								class="page-item <c:if test="${requestScope.currentPage == requestScope.numPage}">disabled</c:if>">
								<a class="page-link"
								href="${request.getContextPath()}?page=${requestScope.currentPage + 1}&search=${requestScope.search}">Next</a>
							</li>
						</ul>
					</nav>
				</div>
				<%-- End page controller --%>
			</div>
			<!-- END RIGHT PART -->
		</div>
	</div>
	<!-- END CONTENT -->



	<!-- LIB -->
	<c:import url="components/body-js-lib.jsp"></c:import>
	<script>
      // Function to handle checkbox in the header to select all rows
      $(document).ready(function () {
        $("#selectAll").on("click", function () {
          if (this.checked) {
            $('input[type="checkbox"]').each(function () {
              this.checked = true;
            });
          } else {
            $('input[type="checkbox"]').each(function () {
              this.checked = false;
            });
          }
        });
      });

      function updateUser(type)
      {
    	  
          var checkboxes = document.getElementsByName('toBeChanged');
          
          var selected = new Array();
          
          for (var i=0; i<checkboxes.length; i++) {
              if (checkboxes[i].checked) {
                  selected.push(checkboxes[i].value);
              }
          }   
          
          $.ajax({
              url:"UpdateUserStatusController",
              type:"POST",
              dataType:'json',
              data: {toBeChanged:selected, type: type},
          });
          
          setTimeout(function() {
              location.reload();
          }, 2000);
      }
      
      function changeRole(id, role)
      {
    	  console.log("clicked");
	      $.ajax({
	          url:"UpdateUserRoleController",
	          type:"POST",
	          dataType:'json',
	          data: {id:id, role: role},
	      });
	      
          setTimeout(function() {
              location.reload();
          }, 2000);
      }
      
    </script>
</body>
</html>
