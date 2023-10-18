<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Arial,Helvetica,sans-serif">
    <link rel="stylesheet" href="resources/style/index.css">
    <title>Client management</title>
</head>
<body style="display: flex; justify-content: center; padding: 10vh 10vw; color: darkslategray;">

    <div class="container">

        <h1>Client Information</h1>
        <a href="/Easybank/client?add">add client</a>
        <table>
            <tr>
                <th>ID</th>
                <th>Last Name</th>
                <th>First Name</th>
                <th>Birth Date</th>
                <th>Phone Number</th>
                <th>Address</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="client" items="${clients}">
                <tr>
                    <td><a href="/Easybank/client?id=${client.id}">${client.id}</a></td>
                    <td>${client.lastName}</td>
                    <td>${client.firstName}</td>
                    <td>${client.dateOfBirth}</td>
                    <td>+${client.phoneNumber}</td>
                    <td>${client.address}</td>
                    <td>
                        <div>
                            <button><a href="/Easybank/client?edit=${client.id}">Update</a></button>
                            <button><a href="/Easybank/client?delete=${client.id}">Delete</a></button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
        
    </body>
    </html>