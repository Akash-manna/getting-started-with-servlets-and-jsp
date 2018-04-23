import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FirstServlet extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException , ServletException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>My First Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h3>It Finally Works!</h3><br>");
		out.println("<h2>Fuck Yeah! </h2><br>");
		out.println("Time: "+(new Date().toLocaleString())+"<br>");
		out.println("-Akash Manna");
		out.println("</body>");
		out.println("</html>");
	}
}