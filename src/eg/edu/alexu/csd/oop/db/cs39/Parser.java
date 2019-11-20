package eg.edu.alexu.csd.oop.db.cs39;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
	
	Partitions object =new Partitions();
	public boolean checkInput(String s)  {
		if(s.matches("(?i)SELECT\\s+[*]\\s+FROM\\s+\\w+(\\s+)?")) {
			
			object.SelectTable(s);
			
			return true ;
		}
		else if (s.matches("(?i)CREATE\\s+DATABASE\\s+\\w+(\\s+)?")) {
			object.CreateDatabase(s);
	
			return true ;
		}
		else if (s.matches("(?i)DROP\\s+DATABASE\\s+\\w+(\\s+)?")) {
		     object.DropDatabase(s);
			
			return true ;
		}
		else if (s.matches("(?i)DROP\\s+TABLE\\s+\\w+(\\s+)?")) {
			object.DropTable(s);
			
			return true ;
		}
		else if (s.matches("(?i)DELETE\\s+FROM\\s+\\w+\\s+WHERE\\s+\\w+(\\s+)?[=><](\\s+)?(('\\w+')|(\\d+))")) {
			object.Delete(s);
			 
			return true ;
		}
		else if(s.matches("(?i)UPDATE\\s+\\w+\\s+SET\\s+((\\s+)?\\w+(\\s+)?=(\\s+)?((\\d+)|('\\w+'))(,)?)+\\s+WHERE\\s+\\w+(\\s+)?=(\\s+)?((\\d+)|('\\w+'))")) {
			object.Update(s);
			
			return true ;
		}
		else if(s.matches("(?i)INSERT\\s+INTO\\s+\\w+(\\s+)?[(]((\\s+)?\\w+(\\s+)?(,)?)+[)]\\s+VALUES\\s+[(]((\\s+)?(('\\w+')|(\\d+))(\\s+)?(,)?)+[)]")) {
			
			object.Insert(s);
			
			return true ;
		}
		else if(s.matches("(?i)CREATE\\s+TABLE\\s+\\w+(\\s+)?[(]((\\s+)?\\w+\\s+((varchar)|(int))(\\s+)?(,)?)+[)]")) {
			object.CreateTable(s);
		
			return true ;
		}
		else if(s.matches("(?i)SELECT\\s+[*]\\s+FROM\\s+\\w+\\s+WHERE\\s+\\w+(\\s+)?[=><](\\s+)?\\d+(\\s+)?")) {
			object.Select(s);
			return true ;
		}
		return false ;
		
	}

}