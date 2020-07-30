

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class Jni2
 */
@WebServlet("/Jni2")
public class Jni2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: keyword Search").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fileName = request.getParameter("file_name");
		String filePath = request.getParameter("file_path");
		String keyword = request.getParameter("keyword");
		String endLine = request.getParameter("end_line");
		
		
		UserOutput.path = filePath+ "\\Output.txt";
		UserOutput.count = 0;
		UserOutput.arr.clear();
		
		UserInput obj = new UserInput();
		if(endLine.contains("\\n"))
			obj.searchString(fileName, filePath+"\\"+fileName, "\n", keyword);
		else
			obj.searchString(fileName, filePath+"\\"+fileName, endLine, keyword);
		
	
		  
		  BufferedReader reader = new BufferedReader(new FileReader(filePath+"\\Output.txt"));
		  StringBuffer buffer = new StringBuffer();
		  String line1 = new String();
		  while ((line1 = reader.readLine()) != null) {
			  System.out.println("@@ : " + line1);
			  buffer.append(line1);
		  }
		  
		  reader.close();
		  
		  String content = buffer.toString();
		  
		  Gson gson = new Gson();
		  PrintWriter printWriter = response.getWriter();
		  response.setContentType("application/json");
		  response.setCharacterEncoding("UTF-8");
		  if(UserOutput.arr.size() == 0)
		  {
			  content = "No match found";
			  printWriter.write(gson.toJson(content));
		  }
		  else
			  printWriter.write(gson.toJson(UserOutput.arr));
			  
		  printWriter.close();
		  
	}

}


/*	StringBuffer jb = new StringBuffer();
String line = null;
try {
  BufferedReader reader = request.getReader();
  while ((line = reader.readLine()) != null)
  {
  	jb.append(line);
  }
    
} catch (Exception e) { System.out.println(e); }

line = jb.toString();
System.out.println("Linee" + line);
JsonObject jsonObject = new JsonParser().parse(line).getAsJsonObject();
System.out.println("JSON objj" + jsonObject);

String keyword = jsonObject.get("keyword").toString();
String filePath = jsonObject.get("file_path").toString();
String fileName = jsonObject.get("file_name").toString();
String endLine = jsonObject.get("end_line").toString();
System.out.println("Endline : : : : " + endLine.charAt(0));
System.out.println("keyword : "+ keyword);
String newPath = new String(); 
String newendLine = new String(); 
String newKeyword = new String(); 
for (int i = 0; i < filePath.length(); i++) {
	  
	  if(filePath.charAt(i) != '"' && filePath.charAt(i) != '\\')
	  {
		  newPath += filePath.charAt(i);
	  }
	  if(filePath.charAt(i) == '\\')
	  {
		  newPath += filePath.charAt(i);
		  i++;
	  }
}
for (int i = 0; i < keyword.length(); i++) {
	  
	  if(keyword.charAt(i) != '"')
	  {
		  newKeyword += keyword.charAt(i);
	  }
}
for (int i = 0; i < endLine.length(); i++) {
	  
	  if(endLine.charAt(i) != '"' && endLine.charAt(i) != '\\')
	  {
		  newendLine += endLine.charAt(i);
	  }
	  if(endLine.charAt(i) == '\\')
	  {
		  newendLine += endLine.charAt(i);
		  i++;
	  }
}
String newFileName = new String();
for (int i = 0; i < fileName.length(); i++) {
	  
	  if(fileName.charAt(i) != '"')
	  {
		  newFileName += fileName.charAt(i);
	  }
}
UserOutput.path = newPath+ "\\Output.txt";
FileWriter output_Writer = new FileWriter(UserOutput.path, false);
output_Writer.write("");
output_Writer.close();

UserInput obj = new UserInput();
int check = 0;
for(int i=0; i<endLine.length()-1; i++)
{
	  if(endLine.charAt(i) == '\\' && endLine.charAt(i+1) == 'n')
	  {
		  check = 1;
		  break;
	  }  
}

if(check == 1)
	  obj.searchString(fileName, newPath+"\\"+newFileName, "\n", newKeyword);
else
	  obj.searchString(fileName, newPath+"\\"+newFileName, newendLine, newKeyword);

System.out.println("New Pathh : " + newPath + "\t" +newKeyword+ "\t" +fileName + "\t" +newendLine);
*/


//Scanner scanner = new Scanner(Paths.get("C:\\Users\\HI\\Documents\\jni_task_2\\Output.txt"), StandardCharsets.UTF_8.name());
//String content = scanner.useDelimiter("\\A").next();
//scanner.close();


// System.out.println("New Pathh : " + newPath + "\t" +newKeyword+ "\t" +fileName + "\t" +newendLine);
 
// UserOutput objj = new UserOutput(0);
//System.out.println("Output!!! "+ content);

// System.out.println("New Pathh : " + newPath + "\t" +newKeyword+ "\t" +fileName + "\t" +newendLine);

//  System.out.println("Test^^^^^^^^^^^"+"\"\\\\n\"");
  //String end = "\"\\\\n\"";
  //System.out.println("Test "+ end.charAt(0) +"\t" );