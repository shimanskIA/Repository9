<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Регистрация нового пользователя</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
</head>
<body>
<jsp:include page="/static/header.jsp"></jsp:include>
<my:layout1Column>
<h1>Регистрация нового пользователя</h1>
    <my:errorMessage />
<form action="doRegister.jsp" method="post">
    <table body="0" cellspacing="0" cellpadding="10">
        <tr>
            <td>Логин:</td>
            <td><input type="text" name="login" value="${sessionScope.userData.login}"></td>
        </tr>
        <tr>
            <td>Пароль:</td>
            <td><input type="password" name="password" value=""></td>
        </tr>
        <tr>
            <td>Имя:</td>
            <td><input type="text" name="name" value="${sessionScope.userData.name}"></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="email" value="${sessionScope.userData.email}"></td>
        </tr>
        <tr>
            <td>Подтвердите, что вы не робот!</td>
        </tr>
        <tr>
            <td><img src="<c:url value = "/resources/${sessionScope.chosenPicture}" />" width="200" height="199" border="0" align="absmiddle"></td>
        </tr>
        <tr>
            <td>Введите символы изображенные на картинке:</td>
            <td><input type="text" name = "captcha" value = "${sessionScope.userData.captcha}"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Зарегистрироваться">
                <input type="button" value="Отменить" onclick="window.location='<c:url value="/index.jsp" />';"></td>
        </tr>
    </table>
</form>
</my:layout1Column>
<%@ include file="/static/footer.jsp" %>
</body>
</html>
