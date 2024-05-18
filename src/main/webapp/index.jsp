<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE-edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<h1>Currency Converter</h1>
<form name="inputForm" action="currency-converter-servlet" method="post">
    <div class="originalCurrency">
        <label for="oCurrency">Original Currency:</label>
        <input type="text" id="oCurrency" name="oCurrency" required><br>
        <label for="fromCurrency"></label>
        <select id="fromCurrency" name="fromCurrency" required>
            <option value="USD">US Dollar (USD)</option>
            <option value="CNY">Chinese Yen (CNY)</option>
            <option value="INR">Indian Rubies (INR)</option>
            <option value="EUR">Euro (EUR)</option>
            <option value="JPY">Japanese Yen (JPY)</option>
        </select>
        <br>
        <input id="submit" type="submit" value="Submit"/>
        <br><br>
    </div>
    <div class="convertedCurrency">
        <label>Converted Currency:</label>
        <label id="cCurrency">Result Here</label><br>
        <select id="toCurrency" name="toCurrency" required>
            <option value="CNY">Chinese Yen (CNY)</option>
            <option value="USD">US Dollar (USD)</option>
            <option value="INR">Indian Rubies (INR)</option>
            <option value="EUR">Euro (EUR)</option>
            <option value="JPY">Japanese Yen (JPY)</option>
        </select>
        <br><br>
    </div>
</form>
</body>
</html>