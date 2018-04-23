<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<html>
	<head>
		<title>DB Table</title>
	</head>
	<body>
<%String first_name="first_name";
   String last_name="last_name";
   String city="city";
   String email="email";
   String age="age";
   try{
	   Class.forName("com.mysql.jdbc.Driver");
	   Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/examples", "root", "root");
	   Statement st = conn.createStatement();
	   
	   int i=st.executeUpdate("insert into employees (age,first_name,last_name,city,email) values('"+age+"','"+first_name+"','"+last_name+"','"+city+"','"+email+"')");
	   out.println("Data is Successfully Inserted!");
   }
   catch(Exception e){
	   System.out.print(e);
	   e.printStackTrace();
   }%>
   <center></center>
	</body>
</html>