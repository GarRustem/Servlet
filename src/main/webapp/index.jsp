<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import ="ru.appline.logic.Model"%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Домашняя страница по работе с Пользователями</h1>
    Введите ID Пользователя (0 - для вывода списка всех Пользователей)
<br/>
    Доступно: <%
    Model model = Model.getInstance();
    out.print(model.getFromList().size()); // ошибка в print
    %>
<form method="get" action="get">
    <label>
        ID:
        <input type="text" name="id"><br/>
    </label>
</form>
<button type="submit">Поиск</button>
<a href="addUser.html">Создать нового Пользователя</a>
</body>
</html>