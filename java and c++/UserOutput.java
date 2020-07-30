import java.io.File;
import java.io.FileWriter; 
import java.io.FileNotFoundException; 
import java.io.IOException;
public class UserOutput {
	String path = "C:\\Users\\HI\\Documents\\jni_task_2\\Output.txt";

   
   public void generateOutput(String one_Line) { 
		try{
		FileWriter output_Writer = new FileWriter(path, true);
		one_Line +="\n";
		output_Writer.write(one_Line);
		output_Writer.close();
		}
		catch (IOException e) {
		System.out.println("An error occurred.");
		e.printStackTrace();
		
		}
     }
	 
    public static void main(String[] args) {
	   System.out.println("Hello form user output!..");
   }
}
