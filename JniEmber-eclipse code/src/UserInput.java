public class UserInput {  
   static {
      System.loadLibrary("Search_String"); 
   }
   public native void searchString(String file_name, String file_path, String end_delimiter, String search_keyword);
 
}
