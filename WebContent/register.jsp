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
 <h1>Register Here To Vote!</h1>
 <div class="card">
  <div class="card-body">
   <form action="register">

    <div class="form-group row">
     <label for="namelabel" class="col-sm-2 col-form-label">Name</label>
     <div class="col-sm-7">
      <input type="text" class="form-control" name="name" placeholder="Enter name">
     </div>
    </div>

    <div class="form-group row">
     <label for="phoneNumberlabel" class="col-sm-2 col-form-label">Phone
      No</label>
     <div class="col-sm-7">
      <input type="text" class="form-control" name="phoneNumber"
             placeholder="Enter phone number">
     </div>
    </div>

    <div class="form-group row">
     <label for="idCardNumberlabel" class="col-sm-2 col-form-label">ID Number</label>
     <div class="col-sm-7">
      <input type="text" class="form-control" name="idCardNumber" placeholder="Enter ID Number">
     </div>
    </div>

    <div class="form-group row">
     <label for="expirationDatelabel" class="col-sm-2 col-form-label">ID Expiration Date</label>
     <div class="col-sm-7">
      <input type="date" class="form-control" name="expirationDate" placeholder="Enter ID Expiration Date">
     </div>
    </div>

    <div class="form-group row">
     <label for="occupation" class="col-sm-2 col-form-label">Occupation:</label>
     <div class="col-sm-7">
      <select name="occupation" id="occupation">
       <option value="student">Student</option>
       <option value="teacher">Teacher</option>
       <option value="employee">Employee</option>
      </select>
     </div>
    </div>

    <div class=" form-group row">
     <label for="addresslabel" class="col-sm-2 col-form-label">Address</label>
     <div class="col-sm-7">
      <input type="text" class="form-control" name="address" placeholder="Enter address">
     </div>
    </div>

    <div class="form-group row">
     <label for="departmentlabel" class="col-sm-2 col-form-label">Department</label>
     <div class="col-sm-7">
      <select name="department" >
       <c:forEach items="${votingBean.allUsers}" var="value">
        <option value="${value}">${value}</option>
       </c:forEach>
      </select>
     </div>
    </div>

    <div class="form-group row">
     <label for="passwordlabel" class="col-sm-2 col-form-label">Password</label>
     <div class="col-sm-7">
      <input type="password" class="form-control" name="password" placeholder="Enter Password">
     </div>
    </div>

    <button type="submit" class="btn btn-primary">Submit</button>

   </form>
  </div>
 </div>
</div>
</body>
</html>