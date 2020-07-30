import java.io.File;
import java.io.FileWriter; 
import java.io.FileNotFoundException; 
import java.io.IOException;
import java.util.ArrayList;
public class UserOutput { 
	public static String path = "";
	public static int count ;
	
	public static ArrayList<String> arr = new ArrayList<String>();
	
   

public void generateOutput(String one_Line) { 
	   
	
		arr.add(one_Line);
		try{
			
		
		FileWriter output_Writer;
		System.out.println("%%%%%%%%%  : "+arr.get(count));
		if(count == 0)
		{
			System.out.println("!!!!!!!!!!\n"+ path);
			output_Writer = new FileWriter(path, false);
			
			
		}
			
		else {
			output_Writer = new FileWriter(path, true);
			one_Line +="----------";
			output_Writer.write(one_Line);
			output_Writer.close();
		}
		count++;
		}
		catch (Exception e) {
		System.out.println("An error occurred.");
		e.printStackTrace();
		
		}
     }
	 
}
