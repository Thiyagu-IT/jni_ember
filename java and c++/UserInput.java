public class UserInput {  
   static {
      System.loadLibrary("Search_String"); 
   }
   private native void searchString(String file_name, String file_path, String end_delimiter, String search_keyword);
 
   public static void main(String[] args) {
	   UserInput obj = new UserInput();
	   String file_name = "Input.txt";
	   String file_path = "C:\\Users\\HI\\Documents\\jni_task_2\\Input.txt";
	   String end_delimiter = ".";
	   String search_keyword = "email";
	   obj.searchString(file_name, file_path, end_delimiter, search_keyword);
   }
}
