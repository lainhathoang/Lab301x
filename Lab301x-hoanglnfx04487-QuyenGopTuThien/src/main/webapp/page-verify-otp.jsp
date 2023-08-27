<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
  <head>
    <c:import url="components/head-meta-tag.jsp"></c:import>
    <title>Verify OTP</title>
    <c:import url="components/head-link-lib.jsp"></c:import>
  </head>
<body>
    <!-- NAVBAR -->
    <c:import url="components/navbar.jsp"></c:import>
	
	<div class="container mt-5">
        <h1>Enter the OTP we sent to ${emailOTP}:</h1>         
        <form action="VerifyOTPController" method="post">
            <!-- Password Field -->
            <div class="form-group">
              <label for="userOTP">Please enter a valid 6-digit OTP:</label>
              <input
                type="text"
                class="form-control"
                id="userOTP"
                name="userOTP"
                required
                pattern=".{6}"
                title="must be at least 6 characters long"
              />
            </div>
            <!-- Log in & Sign in button -->
            <button type="submit" class="btn btn-primary">Submit</button>
            <p></p>
          </form>      
    </div>
    <!-- <p>${ codeOTP }</p>  -->
    
    <!-- Bootstrap & JQuery libs -->
    <c:import url="components/body-js-lib.jsp"></c:import>
</body>
</html>