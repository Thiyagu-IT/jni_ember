import java.io.File;
import java.io.IOException;
public class Main {  

	public static String finalOutput = null;
	
   static {
      System.loadLibrary("Search_Folder"); 
   }
   public native void folderSearch(String file_name, String pattern);
   
   public void sendOutput(String outputContent) {
	   
		finalOutput = outputContent;
     }
	 
	 public static void recursiveDelete(File file) {
		 
        if (!file.exists())
            return;
        
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                recursiveDelete(f);
            }
        }
        file.delete();
        System.out.println("Deleted file/folder: "+file.getAbsolutePath());
    }

}
