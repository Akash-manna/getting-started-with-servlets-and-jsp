import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

//@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize=16177215)	//upload File's Max Size 16 MB
public class FileUploadDBServlet extends HttpServlet
{
	
	//database connection settings
	private String dbURL = "jdbc:mysql://localhost:3306/examples";
	private String dbUser = "username";
	private String dbPass = "password";
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//gets values of text fields
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		InputStream inputStream = null;	//input Stream of the upload file
		
		//obtains the upload file part in this multipart request
		Part filePart = request.getPart("photo");
		if(filePart != null)
		{
			//prints out some information for debugging
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());
			
			//obtains input stream of the upload file
			inputStream = filePart.getInputStream();
		}
		
		Connection conn = null;	//connection to the database
		String message = null;	//message will be sent back to client
		
		try{
			//connects to the database
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
			
			//constructs SQL statement
			String sql = "INSERT INTO contacts (first_name, last_name, photo) values (?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			
			if(inputStream != null)
			{
				//fetches input stream of the upload file for the blob column
				statement.setBlob(3, inputStream);
			}
			
			//sends the statement to the database server
			int row = statement.executeUpdate();
			if(row>0)
			{
				message = "File Uploaded and Saved into Database";
			}
		}catch(SQLException ex){
			message = "ERROR: "+ex.getMessage();
			ex.printStackTrace();
		}finally{
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
			request.setAttribute("Message", message);
			
			//forwards to the message page
			getServletContext().getRequestDispatcher("/Message.jsp").forward(request, response);
		}
	}
}
