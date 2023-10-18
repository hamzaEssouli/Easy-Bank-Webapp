<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Arial,Helvetica,sans-serif">
    <link rel="stylesheet" href="resources/style/index.css">
    <title>Document</title>
</head>
<body style="color: darkslategray;">
    <div class="container">

    
        <c:if test="${empty client}">
            <h2>Client not exist</h2>
            <a href="client">go back to clients list</a>
        </c:if>
        <c:if test="${not empty client}">
            <h2>Client information</h2>
            <div>
                <p>Last name: ${client.lastName}</p>
                <p>First name: ${client.firstName}</p>
                <p>Date of birth: ${client.dateOfBirth}</p>
                <p>Phone number: ${client.phoneNumber}</p>
                <p>address: ${client.address}</p>
                <h3>employee</h3>
                    <div>
                        <p>Last name: ${client.employee.lastName}</p>
                        <p>First name: ${client.employee.firstName}</p>
                        <p>Date of birth: ${client.employee.dateOfBirth}</p>
                        <p>Phone number: ${client.employee.phoneNumber}</p>
                        <p>Email: ${client.employee.email}</p>
                    </div>
            </div>
        </c:if>
    </div>
        
            
</body>
</html>