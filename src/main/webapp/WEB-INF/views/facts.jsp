<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.io.*" %>

<!DOCTYPE html> 
<html lang="ru">
<head>
<meta charset="utf-8" >
<title>Insert title here</title>
</head>
<body>
 <form id="fortGr" name="ReadDB" action="/brokers/ReadDB" method="post">
               <p><input type="submit" value="Обновить данные"></p></form>
           <div  id="fromStyle" >
            <table> 
              <tr>  <td> </td>
              		<td>Дата</td>
              		<td>Наименование</td>
              		<td>Стоимость</td>	
              		<td>Сохранить</td>
              		<td>Удалить</td>
               </tr>
              <c:forEach var="cust" items="${ReadDB}">
           		<tr>
           			<form action="/brokers/ReadDB" method="post">
            		 <td> <input type="hidden" name="id" value="${cust.id}"></td> <!-- Вставляем нумерацию строк -->
		           	 <td ><input class="dateCS" type="date" name="DateLight" value="${cust.dateLight}"></td>
		             <td><input  type="text"  maxlength="32" name="NameBym" value="${cust.nameBym}"></td>
		             <td><input class="priceCS" type="number" step =0.0001 name="PriceBym" value="${cust.priceBym}"></td>
		             <td><input name="OnUpdate" type="submit" value="Save"></td> </form>   
		             <td> <form action="/brokers/ReadDB" method="post">
		             <input name="OnDelete" type="submit" value="Delete"> 
		             <input type="hidden" name="Id" value="${cust.id}"> </form>
		           <!--  <a href='<c:url value="/DeleteDB?id=${cust.id}"/>'>DELETE </a>--> </td>
             	 </tr>
              </c:forEach> 
            </table>
            </div> 
</body>
</html>