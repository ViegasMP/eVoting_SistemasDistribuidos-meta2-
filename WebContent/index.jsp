<%--
  Created by IntelliJ IDEA.
  Ana Luisa Coelho e Maria Paula Viegas
  SD 2021
--%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script src="https://kit.fontawesome.com/f77f0b423e.js" crossorigin="anonymous"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>eVoting</title>
	<style>
		h1 {text-align: center;}
	</style>
</head>
<body>
<div class="container">
	<h1>Welcome to eVoting!</h1>
	<div class="card">
		<div class="card-body">
			<div class="form-group row">
				<div class="col-md-12 text-center">
					<form action="button1.action">
						<button type="registerUser" class="btn btn-primary">Register User</button>
					</form>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-md-12 text-center">
					<form action="button2.action">
						<button type="createElection" class="btn btn-primary">Create Election</button>
					</form>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-md-12 text-center">
					<form action="button3.action">
						<button type="alterElection" class="btn btn-primary">Alter Election</button>
					</form>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-md-12 text-center">
					<form action="button7.action">
						<button type="candidateList" class="btn btn-primary">Manage Candidate</button>
					</form>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-md-12 text-center">
					<form action="button8.action">
						<button type="addvotingtable" class="btn btn-primary">Add Voting Table to Election</button>
					</form>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-md-12 text-center">
					<button type="votingPlace" class="btn btn-primary">Voting Place</button>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-md-12 text-center">
					<form action="button5.action">
						<button type="login" class="btn btn-primary">Vote!</button>
					</form>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-md-12 text-center">
					<form action="button4.action">
						<button type="consultElections" class="btn btn-primary">Consult Current Election</button>
					</form>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-md-12 text-center">
					<form action="button6.action">
						<button type="pastElections" class="btn btn-primary">Consult Past Election</button>
					</form>
				</div>
			</div>

		</div>
	</div>
</div>
</body>
</html>