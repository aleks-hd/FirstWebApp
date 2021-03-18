<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.io.*" %>

<!DOCTYPE html> 
<html lang="ru">
<head>
<meta charset="utf-8" >
   <title>Document</title>
     <link href="resources/css/stylemodal.css" rel="stylesheet" type="text/css">
    <script language="javascript" type="text/javascript" src="resources/flot/jquery.js"></script>  
  <script language="javascript" type="text/javascript" src="resources/flot/jquery.flot.js"></script>
</head>
<body>
	<button id="myBtn">Вставить данные</button>
    <!-- Модальное окно -->
     <div id="myModal" class="modal">
	 <div class="modal-content">
        <div class="modal-header">
          <span class="close">&times;</span>
          <h2>Заполните поля</h2>
        </div>
        <div class="modal-body">
          	  <form action="/brokers/InsertDB" method="post">
            	   <table>
                <tr> 
                		<td> <label for="DateLight">Дата</label> </td>
                   		<td> <input type="date" placeholder="2021-03-02" value="2020-02-02" name="DateLight"></td></tr>
                <tr>  	<td> <label for="NameBym">Наименование</label></td>
                       	<td> <input type="text" name="NameBym"></td></tr>
                <tr> 	<td> <label for="PriceBym">Стоимость</label> </td>
                      	<td> <input  type="number" step =0.0001 name="PriceBym"> </td> </tr>
                <tr> 	<td></td>   
                		<td> <input type="submit" value="Записать в БД"></td></tr></table>
              </form>
        </div>
      </div>
    </div>
   <!-- Скрипт для модального -->
      <script src="resources/JS/forModal.js"></script>
 <!-- Таблица с данными -->   
          <jsp:include page="facts.jsp" />
   <p>Для вывода графика выберите из списка компанию, и выполните подтверждение</p> 
    <div>
     <form  action="/brokers/ReadDB" method=post>
			<select name="NameBym1"> 
					<c:forEach var="customers1" items="${spisok}">
						<option value="${customers1.nameBym}">${customers1.nameBym}</option>
					</c:forEach>
			</select> <input type="submit" value="Построить график"><a></a>
	 </form>${NameComp}
	   </div>
	

  <!-- выводится график -->
  <div id="place" style="width:600px;height:300px;"></div>

<script language="javascript" type="text/javascript">

var all_data = <%= request.getSession().getAttribute("Znachenuy") %>;
for(var j = 0; j < all_data.length; ++j) {
 for(var i = 0; i < all_data[j].data.length; ++i)
   all_data[j].data[i][0] = Date.parse(all_data[j].data[i][0]);
}
// свойства графика
var plot_conf = {
 series: {
   lines: {
     show: true,
     lineWidth: 1.5
   }
 },
 xaxis: {
   mode: "time",
   timeformat: "%y/%m/%d",
 }
};
// выводим график
$.plot($("#place"), all_data, plot_conf);
</script>
</body>
</html>