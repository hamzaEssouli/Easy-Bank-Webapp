<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Arial,Helvetica,sans-serif">
    <link rel="stylesheet" href="resources/style/index.css">
    <title>Document</title>
</head>
<body>
    <div class="container">
        <h2>Edit client information</h2>
    <form id="editClientForm" method="POST" action="/Easybank/client?update=${client.id}">
        <input type="hidden" name="id"  value="${client.id}">
        <input type="text" name="lastName" value="${client.lastName}">
        <input type="text" name="firstName" value="${client.firstName}" >
        <input type="date" name="dateOfBirth" value="${client.dateOfBirth}">
        <input type="text" name="phoneNumber" value="${client.phoneNumber}" >
        <input type="text" name="address" value="${client.address}">
        <select name="employeeId" value="{client.employee.id}">
            <option value="3">Hamza Essouli</option>
        </select>
        <button type="submit">Update</button>
    </form>
    </div>
    

    <!-- <script>
        document.addEventListener("DOMContentLoaded", () => {
            let editClientForm = document.getElementById("editClientForm");
            editClientForm.addEventListener("submit", (event) => {
                event.preventDefault();
                let formData = new FormData(event.target);
                let ajax = new XMLHttpRequest();
                ajax.open("PUT", "http://mizo-laptop:8080/Easybank/client", true);
                ajax.onload = () => {
                    if(ajax.status >= 200 && ajax.status < 400) 
                        console.log(ajax.responseText);
                    else 
                        console.log(ajax.statusText);
                }
                ajax.onerror = () => console.error("Network Error");
                formData.append("_method", "put");
                ajax.send(formData);
            });
        })
    </script> -->
    
</body>
</html>