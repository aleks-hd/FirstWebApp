<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.io.*" %>


<!DOCTYPE html> 
<html lang="ru">
<head>
  <meta charset="utf-8" >
 <!--   <meta http-equiv="X-UA-Compatible" content="IE=edge"> 
  <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
   <title>Document</title>
      
      <link href="resources/css/stylemodal.css" rel="stylesheet" type="text/css">
    <script language="javascript" type="text/javascript" src="resources/flot/jquery.js"></script>  
  <script language="javascript" type="text/javascript" src="resources/flot/jquery.flot.js"></script>
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> --> 
  <style>
  .form {
  max-width: 50%;
  max-width: 20%;
  }
  </style>
</head>
<body>
	<p>Работа  через JSP </p>
    <button id="myBtn">Вставить данных</button>
     <div id="myModal" class="modal">
	 <div class="modal-content">
        <div class="modal-header">
          <span class="close">&times;</span>
          <h2>Заполните поля</h2>
        </div>
        <div class="modal-body">
            <form action="/brokers/InsertDB" method="post">
                <p>
                 <label for="DateLight">Выберите дату</label>
                     <input type="date" placeholder="2021-03-02" value="2020-02-02" name="DateLight"></p>
                     <p><label for="NameBym">Введите наименование</label>
                        <input type="text" name="NameBym"></p>
                   <p><label for="PriceBym">Введите стоимость</label>
                       <input  type="number" step =0.0001 name="PriceBym"></p>
                    <p><input type="submit" value="Записать в БД"></p>
               </form>
        </div>
      </div>
    </div>
    <script src="resources/JS/forModal.js"></script>
    
 <!-- Отображение таблицы -->   
 		
          <div id="result">fuck</div>
				<form id="fortGr" action="/brokers/ReadDB" method="post">
               <p><input  type="submit" value="Read with Database"></p></form>
           <div  id="fromStyle" >
            <table id="vvodandredVal"> 
              <c:forEach var="cust" items="${ReadDB}">
           		<tr>
           			<form action="/brokers/UpdateDB" method="post">
            		 <td id="tyt bydet ID" ></td> <!-- Вставляем нумерацию строк -->
		           	 <td ><input class="dateCS" type="date" name="DateLight" value="${cust.dateLight}"></td>
		             <td><input  type="text"  maxlength="32" name="NameBym" value="${cust.nameBym}"></td>
		             <td><input class="priceCS" type="number" step =0.0001 name="PriceBym" value="${cust.priceBym}"></td>
		             <td><input type="submit" value="Save"></td>  </form>   
		             <td><a href='<c:url value="/DeleteDB?id=${cust.id}"/>'>DELETE</a></td>
             	 </tr>
              </c:forEach> 
            </table>
            </div> 
           
            <form action="/brokers/ProcDB" method=post>
             
            <table>  <c:forEach var="customers1" items="${spisok}">
              <span> <input type="radio" name = "NameBym1" value="${customers1.nameBym}">${customers1.nameBym}</span>
              </c:forEach> <input type="submit" value="Жми"></table></form>
          
	
  


 
  <!-- тут будет выводится график -->
  <div id="placeholder" style="width:600px;height:300px;"></div>

<script language="javascript" type="text/javascript">


var all_data = <%= request.getAttribute("Znachenuy") %>;
				
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
$.plot($("#placeholder"), all_data, plot_conf);
</script>
                
      
</body>
</html>