<%-- 
    Document   : createAccount
    Created on : Mar 9, 2024, 12:16:02 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create</title>
    </head>
    <body>
        <form action="DispatchController" method="POST">
            <c:set var="errors" value="${requestScope.INSERT_ERROR}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" /> (6 - 20 chars) <br>
            <c:if test="${not empty errors.usernameLengthErr}"> 
                <font color="red">
                ${errors.usernameLengthErr}
                </font><br>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}"> 
                <font color="red">
                ${errors.usernameIsExisted}
                </font><br>
            </c:if>
                
            Password* <input type="password" name="txtPassword" value="" /> (6 - 30 chars) <br>
            <c:if test="${not empty errors.passwordLengthErr}"> 
                <font color="red">
                ${errors.passwordLengthErr}
                </font><br>
            </c:if>
                
            Confirm* <input type="password" name="txtConfirm" value="" /> <br>
            <c:if test="${not empty errors.confirmNotMatch}"> 
                <font color="red">
                ${errors.confirmNotMatch}
                </font><br>
            </c:if>
                
                Fullname* <input type="text" name="txtLastname" value="${param.txtLastname}" /> (2 - 50 chars) <br>
            <c:if test="${not empty errors.fullNameLengthErr}"> 
                <font color="red">
                ${errors.fullNameLengthErr}
                </font><br>
            </c:if>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset"/>    
        </form>
    </body>
</html>
