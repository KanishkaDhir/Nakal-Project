package nakl_project;

import java.sql.Connection;
import java.sql.DriverManager;
 class mysql
{
	 
	 public static Connection getConnection()
	 {
		 Connection con=null;
		  try 
		  {
			Class.forName("com.mysql.jdbc.Driver");
		    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/nakl_project","root","bce");
		    System.out.println("connected");
		  
		  } 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		  return con;
	 }
     
}






