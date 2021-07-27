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
    <style> h1 {text-align: center;} </style>
    <style> body {text-align: center;} </style
</head>
<body>
<div class="container">
    <h1>Login</h1>
    <div class="card">
        <div class="card-body">
            <form action="login">

                <div class="form-group row">
                    <label for="electionName" class="col-sm-2 col-form-label">Numero cartao cidadao
                    </label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="cc"
                               placeholder="cc">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="description" class="col-sm-2 col-form-label">Password
                    </label>
                    <div class="col-sm-7">
                        <input type="password" class="form-control" name="pass"
                               placeholder="password">
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Log in</button>
            </form>
            <form action="fb.action">
                <button type="fbbutton" class="btn btn-lg btn-social btn-facebook">
                    <i class="fa fa-facebook fa-fw"></i> Log in</button>
            </form>
        </div>
    </div>
</div>
