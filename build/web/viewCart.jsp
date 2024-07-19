<%-- 
    Document   : viewCart
    Created on : Mar 3, 2024, 8:23:47 PM
    Author     : ADMIN
--%>

<%@page import="java.util.Map"%>
<%@page import="phucplh.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping</title>
    </head>
    <body>
        <h1>Your Cart include:</h1>
        <c:if test="${not empty sessionScope.CART}">
            <c:set var="cart" value="${sessionScope.CART}"/>
            <c:if test="${not empty cart}">
                <c:set var="items" value="${cart.items}"/>
                <c:if test="${not empty items}">
                    <form action="DispatchController">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Name</th>
                                    <th>Deseription</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="totalPrice" value="0"/>
                                <c:forEach var="itemEntry" items="${items}" varStatus="counter">                                
                                    <tr>
                                        <td>
                                            ${counter.count}
                                            <input type="hidden" name="txtId" value="${itemEntry.key.id}" />                                           
                                        </td>
                                        <td>
                                            ${itemEntry.key.name}
                                        </td>
                                        <td>
                                            ${itemEntry.key.deseription}
                                        </td>
                                        <td>
                                            ${itemEntry.key.price}
                                        </td>
                                        <td>
                                            ${itemEntry.value}
                                            <input type="hidden" name="txtQuantity" value="${itemEntry.value}" />
                                        </td>
                                        <td>
                                            <input type="checkbox" name="chkItem" value="${itemEntry.key.id}" />
                                        </td>
                                    </tr>
                                    <c:set var="totalPrice" value="${itemEntry.key.price * itemEntry.value + totalPrice}"/>
                                </c:forEach>
                                <tr>
                                    <td colspan="3">
                                        <a href="onlineShopping.jsp">Add more Items to Cart</a>
                                    </td>                                   
                                    <td>
                                        Total: ${totalPrice}
                                        <input type="hidden" name="txtTotalPrice" value="${totalPrice}" />
                                    </td>
                                    <td>
                                        <input type="submit" value="Remove Selected Item" name="btAction" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <input type="submit" value="Check_out" name="btAction" />
                    </form>
                </c:if>
            </c:if>
        </c:if>
        <%--<%
            //.1 Customer goes to his/her cart place
            if (session != null) {
                //2. Customer takes cart
                CartObject cart = (CartObject)session.getAttribute("CART");
                if (cart != null) {
                    //3. Customer taked item from cart
                    Map<String, Integer> item = cart.getItems();
                    if (item != null) {
                        //4. Show item 
                        %> 
                        <form action="DispatchController">                        
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%  int count = 0;
                                    for (String key : item.keySet()) {
                                %>
                                    <tr>
                                        <td>
                                            <%= ++count %>
                                        </td>
                                        <td>
                                            <%= key %>
                                        </td>
                                        <td>
                                            <%= item.get(key) %>
                                        </td>
                                        <td>
                                            <input type="checkbox" name="chkItem" value="<%= key %>" />
                                        </td>
                                    </tr>
                                <%    
                                    }//end get each item from items
                                %>
                                <tr>
                                    <td colspan="3">
                                        <a href="onlineShopping.html">Add more Items to Cart</a>
                                    </td>
                                    <td>
                                        <input type="submit" value="Remove Selected Item" name="btAction" />
                                    </td>
                                    
                                </tr>
                            </tbody>
                        </table>
                        </form>
        <%
                        return;
                    }
                }//cart has existed
            }//session has existed
        %>--%>
        <c:if test="${empty items}">
            <h2>
                No Cart Is Existed!!!
                <br>
                <br>

                <a href="onlineShopping.html">Back to Shopping Page</a>
            </h2>
        </c:if>
    </body>
</html>
