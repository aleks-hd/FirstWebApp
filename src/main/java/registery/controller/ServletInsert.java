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
 * Servlet implementation class ServletInter
 */
@WebServlet("/InsertDB")
public class ServletInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private CustomersDAO customersDAO =new CustomersDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInsert() {
        super();}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		
		String id = request.getParameter("Id");
		String dateLight = request.getParameter("DateLight");
		String nameBym =request.getParameter("NameBym");
		String priceBym = request.getParameter("PriceBym");
		
		Customers customers = new Customers(id,dateLight,nameBym,priceBym);
		customers.setId(id);
		customers.setDateLight(dateLight);
		customers.setNameBym(nameBym);
		customers.setPriceBym(priceBym);
		
		try {customersDAO.insertCustomers(customers);
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(request, response);
	}

}
