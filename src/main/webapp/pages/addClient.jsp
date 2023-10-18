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
        <form method="POST" action="/Easybank/client">
            <input type="text" name="lastName">
            <input type="text" name="firstName">
            <input type="date" name="dateOfBirth">
            <input type="text" name="phoneNumber">
            <input type="text" name="address">
            <select name="employeeId">
                <option value="3">Hamza Essouli</option>
            </select>
    
            <button type="submit">Create</button>
        </form>
    </div>
    
</body>
</html>