import java.util.*;
import java.io.*;
import java.sql.*;
import javax.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class formServlet extends HttpServlet
{
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	//	try{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
				String dburl = "jdbc:mysql://localhost:3306/examples?autoReconnect=true&useSSL=false";
				String user = "root";
				String pass = "root";
				Connection connection = null;
				
				connection = DriverManager.getConnection(dburl, user, pass);
				Statement statement = connection.createStatement();
				
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				String sql = "INSERT INTO emp (name, email) values ('"+name+"', '"+email+"')";
				statement.executeUpdate(sql);
				
				out.println("<h1>Record Successfully inserted into emp table</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("/form.html");
				rd.include(request, response);
			}catch(ClassNotFoundException cnfe){
				out.println("Class Not Found");
			}catch(SQLException e){
				out.println("Cannot Connect to Database");
			}
/*		}catch(SQLException e){
			//throw new RunTimeException("Cannot Connect to Database!", e);
			out.println("Cannot Connect to Database!");
		}*/
	}
}