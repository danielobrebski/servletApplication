<%@page import="pl.epoint.dobrebski.model.Product"%>
<%@page import="pl.epoint.dobrebski.model.ProductDatabase"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dodanie produktu</title>
</head>
<body>
    <h1>Dodaj produkt: </h1>
        <form action="/products/edit">
            <input type="hidden" name="addSave" value="true"/>
            Nazwa produktu: <br>
            <input type="text" name="name" required/><br>
            Ilość: <br>
            <input type="number" name="quantity" min="1" required/><br>
            Cena: <br>
            <input type="number" name="price" min="0.01" step="0.01" value="1.99" required/>
            <input type="hidden" name="pk" value="<%=ProductDatabase.getNextPK()%>"/>
            <input type="submit" value="Dodaj">
        </form>

        <form action="/products/list">
            <input type="hidden" name="invalidate" value="true"/>
            <input type="submit" value="Wyloguj się"/>
        </form>
</body>
</html>