import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;

//import com.mysql.jdbc.Connection;

public class Jdbc {
	
	String url;
	String uname;
	String pass;
	String end;
	Connection con;
	String query;
	PreparedStatement pst;
	Statement st;
	public Jdbc() throws ClassNotFoundException, SQLException
	{
		url="jdbc:mysql://localhost:3306/jni";
		uname="root";
		pass="thiyagu";
	
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url,uname,pass);
		
	}

	public void addToDb(String keyword, int length, int time, String end) 
	{
		System.out.println("In db..");
		query = "insert into addKeyword values(?, ?, ?, ?)";
		
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, keyword);
			pst.setInt(2, time);
			pst.setInt(3, length);
			pst.setString(4, end);
			pst.execute();
			
		} catch (SQLException e) {
			
			System.out.println("duplicate entry - Not inserted");
		}
		
		
	}
	public ArrayList<String> getkeywords() throws SQLException
	{
		st=con.createStatement();
		query = "select keyword from addKeyword";
		ResultSet fetch = st.executeQuery(query);
		
		ArrayList<String> arr = new ArrayList<String>();
		int i=0;
		while(fetch.next())
		{
			String str = fetch.getString("keyword");
			if(!arr.contains(str))
				arr.add(str);
		}
		System.out.println("!! .. "+ arr);
		return arr;
	}
}
