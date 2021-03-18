package registery.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import registery.model.Customers;

public class CustomersDAO {
	private String namesSQL = "kot";
	private String pass = "kot";
	
	//метод на добавление данных
	public void insertCustomers(Customers customers) throws 
	ClassNotFoundException {
String INSERT_USERS_SQL = "Insert into BRO.BROKERTABLE (DateLight,NameBym,PriceBym)" + 
	"  values (?, ?, ?);"; 
			Class.forName("org.h2.Driver");
				try ( Connection connection = DriverManager.getConnection
				("jdbc:h2:C:/Eclipse_new_projects/mWebBrokers/brokers/base", namesSQL, pass);
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)
			) 
		{  System.out.println("Соединение было успешно установленно");
			preparedStatement.setString(1, customers.getDateLight());
			preparedStatement.setString(2, customers.getNameBym());
			preparedStatement.setString(3, customers.getPriceBym());
			System.out.println(preparedStatement);
			 preparedStatement.executeUpdate();
		connection.close();
		} 	catch (SQLException e) {
			 System.out.println("Соединение ПРОВАЛЕННО");
			 e.printStackTrace();
		}
	}
	//метод на вывод всех данных
	public   ArrayList<Customers> readDB() throws ClassNotFoundException {
			ArrayList<Customers> arrayListDB = new ArrayList<>();
			String readDB = "SELECT * FROM BRO.BROKERTABLE order by DateLight ASC, NameBym ASC";
			Class.forName("org.h2.Driver");
			try (Connection connection = DriverManager.getConnection
					("jdbc:h2:C:/Eclipse_new_projects/mWebBrokers/brokers/base", "kot", "kot");
				PreparedStatement preparedStatement = connection.prepareStatement(readDB);
			)
			{ResultSet RS = preparedStatement.executeQuery();
			while (RS.next()) {
				String id = RS.getString(1);
				String dateLight =RS.getString(2);
				String nameBym = RS.getString(3);
				String priceBym = RS.getString(4);
			Customers cust = new Customers(id,dateLight,nameBym,priceBym);
			arrayListDB.add(cust);
			} 
			connection.close();
			} catch (Exception e) {
				 System.out.println("Соединение ПРОВАЛЕННО");
				 e.printStackTrace();}
			
	return arrayListDB; }
	
// вывод  списка компаний
	public ArrayList<Customers> readDBDIST() throws ClassNotFoundException {
		 ArrayList<Customers> arrayListDBDIST = new ArrayList<>();
		 String readDBDIST = "SELECT DISTINCT nameBym FROM BRO.BROKERTABLE ";
		 Class.forName("org.h2.Driver");
		 try (Connection connection = DriverManager.getConnection
				("jdbc:h2:C:/Eclipse_new_projects/mWebBrokers/brokers/base", "kot", "kot");
				PreparedStatement preparedStatement = connection.prepareStatement(readDBDIST);	
				 ResultSet RS1 = preparedStatement.executeQuery(); )
			{	while (RS1.next()) {
				String nameBym = RS1.getString(1);
				Customers cust1 = new Customers(nameBym);
				arrayListDBDIST.add(cust1);
			}	
			connection.close();
			} catch (Exception e) {
				 System.out.println("Соединение ПРОВАЛЕННО");
				 e.printStackTrace();}
		 return arrayListDBDIST; }
	
	//метод а изменение данных
	public void updateDB(Customers customers) throws ClassNotFoundException{
		String updateSQL ="update BRO.BROKERTABLE SET  DateLight=?, NameBym =?, PriceBym=?  WHERE Id = ?";
		Class.forName("org.h2.Driver");
		try (Connection connection = DriverManager.getConnection
				("jdbc:h2:C:/Eclipse_new_projects/mWebBrokers/brokers/base", namesSQL, pass);
			PreparedStatement preparedStatement = connection.prepareStatement(updateSQL); )
		{
			preparedStatement.setString(4, customers.getId());
			preparedStatement.setString(1, customers.getDateLight());
			preparedStatement.setString(2, customers.getNameBym());
			preparedStatement.setString(3, customers.getPriceBym());
			System.out.println(preparedStatement + " - ведённые параменты");
			preparedStatement.executeUpdate();
			connection.close();
		}
		
		catch (Exception e) {
			 System.out.println("Не удалось записать данные в БД");
			 e.printStackTrace();
		}
	}
	//метод на удаление данных
	public void deleteDB(Customers customers) throws ClassNotFoundException {
		String deleteDB = "delete FROM BRO.BROKERTABLE WHERE Id = ?";
		Class.forName("org.h2.Driver");
		try (Connection connection = DriverManager.getConnection
				("jdbc:h2:C:/Eclipse_new_projects/mWebBrokers/brokers/base", namesSQL, pass);
			PreparedStatement preparedStatement = connection.prepareStatement(deleteDB); )
		{	preparedStatement.setString(1, customers.getId());
		System.out.println(preparedStatement + "-этот на удаление");
		 preparedStatement.executeUpdate();
		connection.close();}  catch (SQLException e) {
			System.out.println("Ошибка при удалении данных с БД");
			e.printStackTrace();
		}
	}
	//метод на обрабоку для графика
	public ArrayList<Customers> procReq(Customers customers) throws ClassNotFoundException {
		ArrayList<Customers> arrayList3 = new ArrayList<>();
		String procReqSQL = "SELECT DATELIGHT, PRICEBYM FROM BRO.BROKERTABLE WHERE NAMEBYM = ? ";
		Class.forName("org.h2.Driver");
		try (Connection connection = DriverManager.getConnection
				("jdbc:h2:C:/Eclipse_new_projects/mWebBrokers/brokers/base", namesSQL, pass);
			PreparedStatement preparedStatement = connection.prepareStatement(procReqSQL); )
			//Записываем имя в кавычках перед запросом
		{	String namegetNameBym ="'"+ customers.getNameBym()+"'";
		preparedStatement.setString(1, customers.getNameBym());
		//Значения для графика
		ResultSet RS2 = preparedStatement.executeQuery(); 
			while(RS2.next()){
				String dateLight = RS2.getString(1);
				String priceBym = RS2.getString(2);
				Customers cust4 = new Customers(dateLight,priceBym);
				arrayList3.add(cust4);
				System.out.println(dateLight + ": " + priceBym);
			}
			connection.close();}  catch (SQLException e) {
			System.out.println("Ошибка при возврате массива данных");
			e.printStackTrace();
			}
		return arrayList3;
	}
	//метод который возвращает строку со списком необходимых графику данных
	public String procReq1(Customers customers) throws ClassNotFoundException {
		String procReqSQL = "SELECT DATELIGHT, PRICEBYM FROM BRO.BROKERTABLE WHERE NAMEBYM = ? order by DateLight ASC";
		String allDateGraf = "[{ data: [";
		final char dm = (char) 34;
		Class.forName("org.h2.Driver");
		try (Connection connection = DriverManager.getConnection
				("jdbc:h2:C:/Eclipse_new_projects/mWebBrokers/brokers/base", namesSQL, pass);
			PreparedStatement preparedStatement = connection.prepareStatement(procReqSQL); )
			//Записываем имя в кавычках перед запросом
		{	String namegetNameBym ="'"+ customers.getNameBym()+"'";
		preparedStatement.setString(1, customers.getNameBym());
		//Значения для графика
		ResultSet RS2 = preparedStatement.executeQuery(); 
		while(RS2.next()){
				String dateLight = RS2.getString(1);
				String priceBym = RS2.getString(2);
				allDateGraf +="[" + dm + dateLight + dm + ", " + priceBym +"],";
			}
			allDateGraf = allDateGraf.substring(0, allDateGraf.length() - 1);
			allDateGraf += "]},]";
			System.out.println(allDateGraf);
			connection.close();}  catch (SQLException e) {
			System.out.println("Ошибка при возврате массива данных");
			e.printStackTrace();
			}
		return allDateGraf;
	}
	}

