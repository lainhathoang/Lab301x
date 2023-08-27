<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="components/head-meta-tag.jsp"></c:import>
<title>404 - Not Found</title>
<c:import url="components/head-link-lib.jsp"></c:import>
</head>
<body>
	<!-- NAVBAR -->
	<c:import url="components/navbar.jsp"></c:import>

	<!-- 404 BIG TEXT -->
	<div class="text-center">
		<h1 class="display-1">404</h1>
		<p class="lead">Page Not Found</p>
		<a href="index.jsp" class="btn btn-primary">Go to Home Page</a>
	</div>

	<!-- LIB -->
	<c:import url="components/body-js-lib.jsp"></c:import>
</body>
</html>
