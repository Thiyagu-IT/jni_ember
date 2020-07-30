import java.io.File;
import java.io.IOException;
public class Main {  

	public Main()
	{
		System.out.println("Constructor");
	}
	
	public static String finalOutput = "";
   static {
      System.loadLibrary("Search_Folder"); 
   }
   public native void folderSearch(String file_name, String pattern);
   
   public void sendOutput(String outputContent) {
		System.out.println("In java jni >>" + outputContent);
		finalOutput = outputContent;
     }
	 
	 public static void recursiveDelete(File file) {
		 System.out.println("In java jni >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        //to end the recursive loop
        if (!file.exists())
            return;
        
        //if directory, go inside and call recursively
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                //call recursively
                recursiveDelete(f);
            }
        }
        //call delete to delete files and empty directory
        file.delete();
        System.out.println("Deleted file/folder: "+file.getAbsolutePath());
    }


   public static void main(String[] args) {
	  Main obj = new Main();
	  
	  
	   String folder_path = "C:\\Users\\HI\\Documents\\jni_task\\";
	   String pattern = "javaasdfg";
	   
	   File directory = new File(folder_path + "Metadata_Folder");
	   recursiveDelete(directory);
	   obj.folderSearch	(folder_path, pattern);
	   if(finalOutput == "")
	   {
		   System.out.println("\n\nJava output >> No matching found\n");
		   return;
	   }
		   
	   System.out.println("\n\nJava output >> \n" + finalOutput);
   }
}
