<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ page
contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
  <head>
    <c:import url="components/head-meta-tag.jsp"></c:import>
    <title>Login</title>
    <c:import url="components/head-link-lib.jsp"></c:import>
  </head>
  <body>
    <!-- NAVBAR -->
    <c:import url="components/navbar.jsp"></c:import>

    <!-- CONTENT -->
    <div class="container">
      <div class="row">
        <!-- Right Content Area -->
        <div class="col-md-10 content">
          <h1>Log In</h1>
          <form action="LoginController" method="post">
            <!-- Email Field -->
            <div class="form-group">
              <label for="email">Email:</label>
              <input
                type="email"
                class="form-control"
                id="email"
                name="email"
                required
                pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
              />
            </div>

            <!-- Password Field -->
            <div class="form-group">
              <label for="password">Password:</label>
              <input
                type="password"
                class="form-control"
                id="password"
                name="password"
                required
                pattern=".{6,}"
                title="Password must be at least 6 characters long"
              />
            </div>

            <!-- Radio button -->
            <div class="form-group">
              <label>User Type:</label>
              <div class="radio">
                <label>
                  <input type="radio" name="userType" value="admin" required />
                  Admin
                </label>
              </div>
              <div class="radio">
                <label>
                  <input type="radio" name="userType" value="user" required />
                  User
                </label>
              </div>
            </div>

            <!-- Log in & Sign in button -->
            <button type="submit" class="btn btn-primary">Login</button>
            <a
              href="page-user-registry.jsp"
              type="button"
              class="btn btn-outline-primary"
            >
              Registry
            </a>
            <a href="page-user-forgot-password.jsp" class="btn btn-link"
              >Forgot password?</a
            >
          </form>
        </div>
      </div>
    </div>

    <!-- Bootstrap & JQuery libs -->
    <c:import url="components/body-js-lib.jsp"></c:import>
  </body>
</html>
