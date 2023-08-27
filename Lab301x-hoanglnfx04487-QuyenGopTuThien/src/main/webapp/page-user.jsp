<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="components/head-meta-tag.jsp"></c:import>
<title>Quyen gop tu thien</title>
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
				<h2>${ person.role }</h2>
			</div>
		</div>
	</div>
	<!-- END CONTENT -->

	<!-- LIB -->
	<c:import url="components/body-js-lib.jsp"></c:import>
</body>
</html>
