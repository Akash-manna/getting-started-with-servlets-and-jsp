import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

public class RegisterDBEmployee extends HttpServlet
{
	//Driver Settings
	//static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	//Database Connection Settings
	//static final String DB_URL = "jdbc:mysql://localhost:3306/examples?autoReconnect=true&";//?verifyServerCertificate=false&useSSL=true";
	//static final String DB_User = "root";
	//static final String DB_Pass = "root";
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException , ServletException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//Gets values from registrationpage.jsp
		//String first_name = request.getParameter("first_name");
		//String last_name = request.getParameter("last_name");
		//String age = request.getParameter("age");
		//String city = request.getParameter("city");
		//String email = request.getParameter("email");
		
		//Connection conn = null;	//Connection to the Database
		//String Message = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String DB_URL = "jdbc:mysql://localhost:3306/examples?autoReconnect=true&useSSL=false";//?verifyServerCertificate=false&useSSL=true";
			String DB_User = "root";
			String DB_Pass = "root";
			Connection conn = DriverManager.getConnection(DB_URL, DB_User, DB_Pass);
			
	/*		//Register the Driver
			//Class.forName(JDBC_DRIVER);
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			
			//Open a Connection
			conn = DriverManager.getConnection(DB_URL,DB_User,DB_Pass);
			
			//Construct a SQL Statement
			String sql = "INSERT INTO employees (age, first_name, last_name, city, email) values (?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, age);
			statement.setString(2, first_name);
			statement.setString(3, last_name);
			statement.setString(4, city);
			statement.setString(5, email);
			
			//Executes the Statement
			int row = statement.executeUpdate();
			if(row>0)
			{
				Message = "All Done! Thank God!";
			}
			else{
				Message = "Oops! Not Again!";
			}*/
			//Register the Driver
			String first_name = request.getParameter("first_name");
			String last_name = request.getParameter("last_name");
			String age = request.getParameter("age");
			String city = request.getParameter("city");
			String email = request.getParameter("email");
			Statement statement = conn.createStatement();
			String sql = "INSERT INTO employees (age, first_name, last_name, city, email) values ('"+age+"', '"+first_name+"', '"+last_name+"', '"+city+"', '"+email+"')";
			statement.executeUpdate(sql);
			out.println("Done! Record successfully inserted in employees table!");
			RequestDispatcher rd = request.getRequestDispatcher("/registrationpage.jsp");
			rd.include(request, response);
		}catch(SQLException ex){
			out.println("ERROR: "+ex.getMessage());
			ex.printStackTrace();
		}catch(ClassNotFoundException cnfe){
			out.println("Class Not Found!");
		}/*finally{
			if(conn != null)
			{
				//closes the database connection
				try{
					conn.close();
				}catch(SQLException ex){
				ex.printStackTrace();
				}
			}
			//sets the Message in request scope
			request.setAttribute("Message", Message);
			
			//forwards to the message page
			getServletContext().getRequestDispatcher("/Messages.jsp").forward(request, response);
		}*/
	}
}