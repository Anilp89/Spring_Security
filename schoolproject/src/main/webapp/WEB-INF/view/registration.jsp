<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<!-- <meta charset="ISO-8859-1"> -->
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Insert title here</title>

<!-- Bootstrap CSS-->
<link href="/resources/vendor/bootstrap-4.1/bootstrap.min.css"
	rel="stylesheet" media="all">

<!-- Main CSS-->
<link href="/resources/css/theme.css" rel="stylesheet" media="all">


</head>
<body class="animsition">
	<div class="page-wrapper">
		<div class="page-content--bge5">
			<div class="container">
				<div class="login-wrap">
					<div class="login-content">
						<div class="login-logo">
							<a href="#"> <img src="/resources/images/icon/logo.png"
								alt="CoolAdmin">
							</a>
						</div>
						<div class="login-form">
							<form:form method="POST" modelAttribute="userForm" id="register"
								class="form-signin" action="/register">
								<div class="container">
									<h2 class="form-signin-heading">Create your account</h2>
											<p style="color:red">${message}</p>
									<spring:bind path="userName">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<form:input type="text" path="userName" class="form-control" id="firstName" placeholder="FirstName" autofocus="true"></form:input>
											<form:errors path="userName"></form:errors>
											<label id="firstNameError" class="hidden"style="Display:none;color:red;">Please enter the first name</label>
										</div>
									</spring:bind>
									
									<spring:bind path="lastName">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<form:input type="text" path="lastName" class="form-control"
												placeholder="lastName" autofocus="true"></form:input>
											<form:errors path="lastName"></form:errors>
										</div>
									</spring:bind>
									
									<spring:bind path="email">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<form:input type="text" path="email" class="form-control" id="email"
												placeholder="email" autofocus="true"></form:input>
											<form:errors path="email"></form:errors>
											<label id="emailError" class="hidden"style="Display:none;color:red;">Please Enter Email Address</label>
										</div>
									</spring:bind>
									<spring:bind path="password">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<form:input type="password" path="password" id="password"
												class="form-control" placeholder="Password"></form:input>
											<form:errors path="password"></form:errors>
											<label id="passwordError" class="hidden"style="Display:none;color:red;">Please Enter Password</label>
										</div>
									</spring:bind>

									<spring:bind path="passwordConfirm">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<form:input type="password" path="passwordConfirm" id="passwordConfirm"
												class="form-control" placeholder="Confirm your password"></form:input>
											<form:errors path="passwordConfirm"></form:errors>
											<label id="passwordConfirmError" class="hidden"style="Display:none;color:red;">Password Not Match</label>
										</div>
									</spring:bind>
									<spring:bind path="mobile">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<form:input type="text" path="mobile" id="mobile"
												class="form-control" placeholder="Enter mobile"></form:input>
											<form:errors path="mobile"></form:errors>
											<label id="mobileError" class="hidden"style="Display:none;color:red;">Enter You'r Mobile Number</label>
										</div>
									</spring:bind>

								</div>
								<div class="container signin">
									<button class="btn btn-lg btn-primary btn-block" type="submit" id="submit">Submit</button>
								</div>
							</form:form>
						</div>
							<div class="login-form">
                                <p>
                                    Do you have account?
                                    <a href="/login">Sign in</a>
                                </p>
                            </div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script src="https://code.jquery.com/jquery-1.7.min.js"></script>
<script src="script.js"></script>
<script type="text/javascript">


$('form').submit(function () {
    var name = $.trim($('#firstName').val());
    var email = $.trim($('#email').val());
    var pwd = $.trim($('#password').val());
    var rpwd = $.trim($('#passwordConfirm').val());
    var mobile = $.trim($('#mobile').val());
    if (name  === '') {
        $('#firstNameError').show();
        return false;
    }else{
		$('#firstNameError').hide();
	}
    if (email  === '') {
        $('#emailError').show();
        return false;
    }else{
		$('#emailError').hide();
	}
    if (pwd  === '') {
        $('#passwordError').show();
        return false;
    }else{
		$('#passwordError').hide();
	}
    if (pwd != rpwd) {
    		$('#passwordConfirmError').show();
    	return false;
    	}else{
    		$('#passwordConfirmError').hide();
    	}
    if (mobile  === '') {
        $('#mobileError').show();
        return false;
    }else{
		$('#mobileError').hide();
	}
});
	
</script>
</html>