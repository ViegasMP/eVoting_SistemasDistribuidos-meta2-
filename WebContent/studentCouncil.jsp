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
    <style> h1 {text-align: center;} </style>
</head>
<body>
<div class="container">
    <h1>Create Student Council Election</h1>
    <div class="card">
        <div class="card-body">
            <form action="studentCouncilAction">

                <div class="form-group row">
                    <label for="electionName" class="col-sm-2 col-form-label">Election Name</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="title"
                               placeholder="Enter election title">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="description" class="col-sm-2 col-form-label">Description</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="description"
                               placeholder="Enter description">
                    </div>
                </div>

                <div class=" form-group row">
                    <label for="startDate" class="col-sm-2 col-form-label">Start Date</label>
                    <div class="col-sm-7">
                        <input type="datetime-local" class="form-control" name="dateini"
                               placeholder="Enter start date">
                    </div>
                </div>

                <div class=" form-group row">
                    <label for="endDate" class="col-sm-2 col-form-label">End Date</label>
                    <div class="col-sm-7">
                        <input type="datetime-local" class="form-control" name="datend"
                               placeholder="Enter end date">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="department" class="col-sm-2 col-form-label">Department</label>
                    <div class="col-sm-7">
                        <select name="department" >
                            <c:forEach items="${votingBean.allUsers}" var="value">
                                <option value="${value}">${value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>

            </form>
        </div>
    </div>
</div>