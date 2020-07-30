

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addKeyword
 */
@WebServlet("/addKeyword")
public class addKeyword extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String keyword = request.getParameter("name");
		String end = request.getParameter("endline");
		int length = Integer.parseInt(request.getParameter("length"));
		int time = Integer.parseInt(request.getParameter("time"));
		System.out.println("Hiiiiiiiiiiii - " + keyword +"  "+ length +"  "+  time);
		
		
		try {
			Jdbc obj = new Jdbc();
			obj.addToDb(keyword, length, time, end);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
