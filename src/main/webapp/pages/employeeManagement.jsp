<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Arial,Helvetica,sans-serif">
    <link rel="stylesheet" href="resources/style/index.css">
    <title>Employee management</title>
</head>
<body style="display: flex; justify-content: center; padding: 10vh 10vw; color: darkslategray;">
    <div class="container">

        <h1>Employee Information</h1>
        
        <table>
            <tr>
                <th>ID</th>
                <th>Last Name</th>
                <th>First Name</th>
                <th>Birth Date</th>
                <th>Phone Number</th>
                <th>Recruitment Date</th>
                <th>email</th>
            </tr>
            <c:forEach var="employee" items="${employees}">
                <tr>
                <td>${employee.id}</td>
                <td>${employee.lastName}</td>
                <td>${employee.firstName}</td>
                <td>${employee.dateOfBirth}</td>
                <td>+${employee.phoneNumber}</td>
                <td>${employee.recruitmentDate}</td>
                <td>${employee.email}</td>
            </tr>
        </c:forEach>
    </table>
    
</div>
    
</body>
</html>