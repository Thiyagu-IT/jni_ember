import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Jni1 extends HttpServlet {
	
	private static final long serialVersionUID = 102831973239L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String pattern = request.getParameter("pattern");
		String folderPath = request.getParameter("folderPath");
	
		  String pathnotFound = null;
		  File file = new File(folderPath);
		  
		  if(!file.exists())
		  {
			  pathnotFound = "Enter valid path";
		  }
		  Gson gson = new Gson();
		  PrintWriter printWriter = response.getWriter();
		  response.setContentType("application/json");
		  response.setCharacterEncoding("UTF-8");
		  if(pathnotFound != null)
		  {
			  printWriter.write(gson.toJson(pathnotFound));
			  return;
		  }
			  
		  
			  
		  Main obj = new Main();
		  obj.finalOutput = "";
		  File directory = new File(folderPath + "\\"+"Metadata_Folder");
		  obj.recursiveDelete(directory);
		  
		  obj.folderSearch(folderPath + "\\", pattern); 		//JNI call
		  
		  
		  
		  directory = new File(folderPath +  "\\"+"Metadata_Folder");
		  
		  if(!directory.exists()) {
			  printWriter.write(gson.toJson(pathnotFound));
			  return;
		  }
		  
		  String fileName[] = directory.list();			// fetching file names in Metadata folder

		
		  ArrayList<String> arr = new ArrayList<String>();
		  int ind = 0;
		  for(int indd = 0 ; indd < fileName.length ; arr.add(fileName[indd++])); 		//storing output filenames in arraylist
		  
		  while(ind < fileName.length )
		  {
			  BufferedReader br  = new BufferedReader(new FileReader(folderPath +  "\\"+"Metadata_Folder"+"\\"+arr.get(ind++)));
		      String strLine = null;
		      String fileContent = "<br>";
		      
		      while((strLine = br.readLine()) != null){
		    	  
		    	  fileContent += strLine + "<br>";
		      }
		      arr.add(fileContent);
		  }
		  	  
		  for (int i = 0; i < arr.size();i++) 
	      { 		      
	          System.out.println("## : \n\t"+arr.get(i)); 		
	      }   
		  
		  String responseOutput;
		  
		  responseOutput = gson.toJson(arr);
		  
			  
		  
		  
		  System.out.println("\n"+responseOutput);
		  printWriter.write(responseOutput);
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
	      
	  } catch (Exception e) { }
	  
	  line = jb.toString();
	  System.out.println("Linee" + line);
	  JsonObject jsonObject = new JsonParser().parse(line).getAsJsonObject();
	  
	  String pattern = jsonObject.get("pattern").toString();
	  String folderPath = jsonObject.get("folderPath").toString();
	  System.out.println("Folder Path.. " + folderPath +" Pattern.. : "+ pattern);
	  String newPath = new String(); 
	  System.out.println("Folder Path 1 .. " + folderPath +" Pattern.. 1 : "+ pattern);
	  
	  for (int i = 0; i < folderPath.length(); i++) {
		  
		  if(folderPath.charAt(i) != '"' && folderPath.charAt(i) != '\\')
		  {
			  newPath += folderPath.charAt(i);
		  }
		  if(folderPath.charAt(i) == '\\')
		  {
			  newPath += folderPath.charAt(i);
			  i++;
		  }
	  }
	  newPath += '\\';
	  String newPattern = new String(); 
	  for (int i = 0; i < pattern.length(); i++) {
		  
		  if(pattern.charAt(i) != '"')
		  {
			  newPattern += pattern.charAt(i);
		  }
	  }
	  System.out.println("Folder Path final .. " + newPath +" Pattern.. : "+ newPattern);
	  String fileNames = "";
	  for(int i=0; i<fileName.length; i++) {
		  fileNames += fileName[i] + "\n";
	      }
	  System.out.println(fileNames);
	  
	  */
	
/*  response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
response.setCharacterEncoding("UTF-8"); // You want world domination, huh?

System.out.println("In JNI \n"+ obj.finalOutput);

String employee = "sucesssssss";
String employeeJsonString = gson.toJson(employee);
String output = gson.toJson(obj.finalOutput);

PrintWriter out = response.getWriter();
response.setContentType("application/json");
response.setCharacterEncoding("UTF-8");
out.print(outputt);
response.getWriter().append(outputt);

String text = "some text";
response.getWriter().write(outputt);
//    response.getWriter().print(obj.finalOutput);
//	response.getWriter().append("Served at: ").append(request.getContextPath());

System.out.println("POST");*/

//Main obj1 = new Main();
//String folder_path = "C:\\Users\\HI\\Documents\\jni_task\\";
//String pattern1 = "Search";
//obj.folderSearch(folder_path, pattern1);
//
//System.out.println(jb);
//String employeeJsonString = gson.toJson(jb);
//System.out.println("!..  " + employeeJsonString);

// String employeeJsonString = gson.toJson(employee);
//JSONParser parser = new JSONParser();
//JSONObject json = (JSONObject) parser.parse(line);
//JSONObject jsonObj = new JSONObject("{\"firstname\":\"John\",\"lastname\":\"Lee\"}");

//JSONObject obj = new JSONObject();
//String userName = request.getParameter("pattern");
//System.out.println(userName);
//System.out.println(request.getAttribute("name"));


//	response.setContentType("application/json;charset=UTF-8");
//	User user = JsonReader.receivePost(request);
//	System.out.println(user);

	//Gson gson = new Gson();
	
	//Employee employee = new Employee(1, "Karan", "IT", 5000);
//	String employee = "sucesssssss";
//    String employeeJsonString = this.gson.toJson(employee);
//    
//	PrintWriter out = response.getWriter();
//    response.setContentType("application/json");
//    response.setCharacterEncoding("UTF-8");
//    out.print(employeeJsonString);


