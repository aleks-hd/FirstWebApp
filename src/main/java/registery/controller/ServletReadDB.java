package registery.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import registery.dao.CustomersDAO;
import registery.model.Customers;

/**
 * Servlet implementation class ServletReadDB
 */
@WebServlet("/ReadDB")
public class ServletReadDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private CustomersDAO customersDAO = new CustomersDAO(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletReadDB() {
        super();}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//Выводим список компаний, и все данные из БД
				try { request.getSession().setAttribute("spisok", customersDAO.readDBDIST());
					request.getSession().setAttribute("ReadDB",customersDAO.readDB());
					} catch (Exception e) {	e.printStackTrace();
				}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("text/html");
		//request.setCharacterEncoding("UTF-8");
		
		//Выводим список компаний, и все данные из БД
		try { request.getSession().setAttribute("spisok", customersDAO.readDBDIST());
			request.getSession().setAttribute("ReadDB",customersDAO.readDB());
			} catch (Exception e) {	e.printStackTrace();
		}
		
		
		
		
		
		//Редактировать данные
		if (request.getParameter("OnUpdate") != null)  {
			System.out.println("Условие на изменение данных");
			String id = request.getParameter("id");
		String dateLight = request.getParameter("DateLight");
		String nameBym = request.getParameter("NameBym");
		String priceBym = request.getParameter("PriceBym");
		Customers customers = new Customers(id,dateLight,nameBym,priceBym);
		customers.setId(id);
		customers.setDateLight(dateLight);
		customers.setNameBym(nameBym);
		customers.setPriceBym(priceBym);
		try { customersDAO.updateDB(customers);
			request.getSession().setAttribute("ReadDB",customersDAO.readDB());
			request.getSession().setAttribute("spisok", customersDAO.readDBDIST());
			} catch (ClassNotFoundException e) {
				System.out.println("Ошибка при получении измененных данных");
				e.printStackTrace();
		}	}
			else { System.out.println("идем дальше");
		}
		
		//Удаление
		if(request.getParameter("OnDelete")!= null) {
			String id = request.getParameter("Id");
			Customers customers = new Customers("id");
			customers.setId(id);
			
				try { customersDAO.deleteDB(customers);
					request.getSession().setAttribute("ReadDB",customersDAO.readDB());
					request.getSession().setAttribute("spisok", customersDAO.readDBDIST());
			
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		//для графика
		//Возврат имен компаний и вывод переменной (не правильно реализованно)
		if (request.getParameter("NameBym1") != null ) {
			String nameBym123 =request.getParameter("NameBym1");
			Customers cust2 = new Customers(nameBym123);
	 		System.out.println(nameBym123);
			cust2.setNameBym(nameBym123);
			
				try { request.getSession().setAttribute("Znachenuy", customersDAO.procReq1(cust2));
					request.getSession().setAttribute("NameComp", nameBym123);
		} catch (ClassNotFoundException e) {
				System.out.println("Ошибка при получении измененных данных");
				e.printStackTrace();}
			}  else {System.out.println("Вышли с графика");}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(request, response);
		}
 }
