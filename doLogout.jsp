<%@page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:remove var="authUser" scope="session" />
<c:redirect url="/index.jsp" />
