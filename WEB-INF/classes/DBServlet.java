import java.io.*;
import java.util.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class DBServlet extends HttpServlet
{
	//Driver Settings
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	//Database Connection Settings
	static final String DB_URL = "jdbc:mysql://localhost:3306/examples";//?verifyServerCertificate=false&useSSL=true";
	static final String DB_User = "username";
	static final String DB_Pass = "password";
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Connection conn = null;
		Statement statement = null;
		String title = "Database Result";
      
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      
		out.println(docType +
         "<html>\n" +
         "<head><title>" + title + "</title></head>\n" +
         "<body bgcolor = \"#85C1E9\">\n" +
         "<h1 align = \"center\">" + title + "</h1>\n");
		try{
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,DB_User,DB_Pass);
			statement = conn.createStatement();
			String sql;
			sql = "SELECT id, age, first_name, last_name, city, email FROM employees";
			ResultSet rs = statement.executeQuery(sql);
			out.println("<body>");
			out.println("<center>");
			out.println("These are our Employees:");
			while(rs.next())
			{
				//Retrieve by Column name
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String city = rs.getString("city");
				String email = rs.getString("email");
				
				//Display Values
				out.print("ID: "+id+" ");
				out.print(",AGE: "+age+" ");
				out.print(",Name: "+first_name+" "+last_name+" ");
//				out.print(",LastName: "+last_name);
				out.print(",City: "+city);
				out.println(",Email: "+email);
			} //while
			//out.println("</center></body></html>");
			//STEP 6: Clean-up Enviroment
			rs.close();
			statement.close();
			conn.close();
		}catch(SQLException ex){
			out.println("ERROR: "+ex.getMessage());
			ex.printStackTrace();
		}catch(Exception e){
			out.println("Error: "+e.getMessage());
			e.printStackTrace();
		}finally{
			if(conn != null)
			{
				//closes the database connection
				try{
					conn.close();
				}catch(SQLException ex){
					out.println("Error: "+ex.getMessage());
				ex.printStackTrace();
				}
			}
		}
		out.println("Thank You!");
		out.println("</center></body></html>");
	}
}
