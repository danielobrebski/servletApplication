<%@page import="pl.epoint.dobrebski.model.Product"%>
<%@page import="pl.epoint.dobrebski.model.ProductDatabase"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista Produktów</title>
</head>
<body>
    <script>
        function confirmDelete(e) {
            if(!confirm('Czy jesteś pewny?'))e.preventDefault();
        }
    </script>

    <div style="text-align:left; width:100%">
        <div style="display:inline-block">
            <p>Poniżej jest lista produktów:</p>
        </div>
        <div style="display:inline-block">
            <form action="/products/edit">
                <input type="hidden" name="addSave" value="false"/>
                <input type="submit" value="Dodaj produkt"/>
            </form>
        </div>
    </div>

    <table style="width:50%">
        <tr>
            <th>Nazwa</th>
            <th>Ilość</th>
            <th>Cena</th>
        </tr>

    <% for(Product p : ProductDatabase.productsMap.values()) { %>
        <tr>
            <td><a href = "/products/edit?editSave=false&pk=<%=p.getPK()%>"><%=p.getName()%></td>
            <td><%=p.getQuantity()%></td>
            <td><%=p.getPrice()%></td>
            <td>
                <form action="/products/edit?">
                    <input type="hidden" name="pk" value="<%=p.getPK()%>"/>
                    <input type="hidden" name="delete" value="true"/>
                    <input type="submit" value="Usuń produkt" onclick="confirmDelete(event)"/>
                </form>
            </td>
          </tr>

    <% } %>
    </table>

    <form action="/products/list">
        <input type="hidden" name="invalidate" value="true"/>
        <input type="submit" value="Wyloguj się"/>
    </form>

    <p>Liczba odwiedzin (pole Servlet): ${counter}</p>
    <p>Liczba odwiedzin (pole Session): ${sessionCounter}</p>
    <p>Liczba odwiedzin (pole ServletContext): ${servletContextCounter}</p>
</body>
</html>