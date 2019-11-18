package eg.edu.alexu.csd.oop.db.cs39;

public class Parser {

	public boolean checkInput(String s) {
		if(s.matches("(?i)SELECT\\s+[*]\\s+FROM\\s+\\w+(\\s+)?")) {
			
			return true ;
		}
		else if (s.matches("(?i)CREATE\\s+DATABASE\\s+\\w+(\\s+)?")) {
			return true ;
		}
		else if (s.matches("(?i)DROP\\s+DATABASE\\s+\\w+(\\s+)?")) {
			return true ;
		}
		else if (s.matches("(?i)DROP\\s+TABLE\\s+\\w+(\\s+)?")) {
			return true ;
		}
		else if (s.matches("(?i)DELETE\\s+FROM\\s+\\w+\\s+WHERE\\s+\\w+(\\s+)?[=><](\\s+)?(('\\w+')|(\\d+))")) {
			return true ;
		}
		else if(s.matches("(?i)UPDATE\\s+\\w+\\s+SET\\s+((\\s+)?\\w+(\\s+)?=(\\s+)?((\\d+)|('\\w+'))(,)?)+\\s+WHERE\\s+\\w+(\\s+)?=(\\s+)?((\\d+)|('\\w+'))")) {
			return true ;
		}
		else if(s.matches("(?i)INSERT\\s+INTO\\s+\\w+(\\s+)?[(]((\\s+)?\\w+(\\s+)?(,)?)+[)]\\s+VALUES\\s+[(]((\\s+)?(('\\w+')|(\\d+))(\\s+)?(,)?)+[)]")) {
			return true ;
		}
		else if(s.matches("(?i)CREATE\\s+TABLE\\s+\\w+(\\s+)?[(]((\\s+)?\\w+\\s+((varchar)|(int))(\\s+)?(,)?)+[)]")) {
			return true ;
		}
		else if(s.matches("(?i)SELECT\\s+[*]\\s+FROM\\s+\\w+\\s+WHERE\\s+\\w+(\\s+)?[=><](\\s+)?\\d+(\\s+)?")) {
			return true ;
		}
		return false ;
		
	}

}
