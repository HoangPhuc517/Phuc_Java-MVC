<%-- 
    Document   : onlineShopping
    Created on : Mar 12, 2024, 4:32:43 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping</title>
    </head>
    <body>
        <c:set var="result" value="${requestScope.ITEMS}"/>
        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Deseription</th>
                        <th>Price</th>
                        <th>Buy</th>              
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                    <form action="DispatchController">                    
                    <tr>                       
                        <td>
                            ${counter.count}
                            <input type="hidden" name="txtId" value="${dto.id}" />
                        </td>
                        <td>
                            ${dto.name}
                            <input type="hidden" name="txtName" value="${dto.name}" />
                        </td>
                        <td>
                            ${dto.deseription}
                            <input type="hidden" name="txtDeseription" value="${dto.deseription}" />
                        </td>
                        <td>
                            ${dto.price}
                            <input type="hidden" name="txtPrice" value="${dto.price}" />
                        </td>
                        <td>
                            <input type="submit" value="Add Item To Your Cart" name="btAction" />
                        </td> 
                    </tr>
                    </form>
                    </c:forEach>
                </tbody>
            </table>
            <form action="DispatchController">            
            <input type="submit" value="View Your Cart" name="btAction" />
            <input type="submit" value="Back" name="btAction" />
            </form>
        </c:if>
        <c:if test="${empty result}" >
            <h2>
                No record is matched!!! 
            </h2> 
        </c:if>
    </body>
</html>
