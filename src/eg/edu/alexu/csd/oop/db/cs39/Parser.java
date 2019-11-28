package eg.edu.alexu.csd.oop.db.cs39;


public class Parser {
	
	Partitions object =new Partitions();
	public int checkInput(String s)  {
		if(s.matches("(?i)SELECT\\s+[*]\\s+FROM\\s+\\w+(\\s+)?")) {
			
			//object.SelectTable(s);
			
			return 1 ;
		}
		else if (s.matches("(?i)CREATE\\s+DATABASE\\s+\\w+(\\s+)?")) {
			//object.CreateDatabase(s);
	
			return 2 ;
		}
		else if (s.matches("(?i)DROP\\s+DATABASE\\s+\\w+(\\s+)?")) {
		    // object.DropDatabase(s);
			
			return 3 ;
		}
		else if (s.matches("(?i)DROP\\s+TABLE\\s+\\w+(\\s+)?")) {
			//object.DropTable(s);
			
			return 4 ;
		}
		else if (s.matches("(?i)DELETE\\s+FROM\\s+\\w+\\s+WHERE\\s+\\w+(\\s+)?[=><](\\s+)?(('\\w+')|(\\d+))")) {
			//object.Delete(s);
			 
			return 5 ;
		}
		else if(s.matches("(?i)UPDATE\\s+\\w+\\s+SET\\s+((\\s+)?\\w+(\\s+)?=(\\s+)?((\\d+)|('\\w+'))(,)?)+\\s+WHERE\\s+\\w+(\\s+)?[=><](\\s+)?((\\d+)|('\\w+'))")) {
			//object.Update(s);
			
			return 6 ;
		}
		else if(s.matches("(?i)INSERT\\s+INTO\\s+\\w+(\\s+)?[(]((\\s+)?\\w+(\\s+)?(,)?)+[)]\\s+VALUES\\s+[(]((\\s+)?(('\\w+')|(\\d+))(\\s+)?(,)?)+[)]")) {
			
			//object.Insert(s);
			
			return 7 ;
		}
		else if(s.matches("(?i)CREATE\\s+TABLE\\s+\\w+(\\s+)?[(]((\\s+)?\\w+\\s+((varchar)|(int))(\\s+)?(,)?)+[)](\\s+)?")) {
			//object.CreateTable(s);
		
			return 8 ;
		}
//		SELECT column_name1 FROM table_name13 WHERE coluMN_NAME2 < 5
		else if(s.matches("(?i)SELECT\\s+[*]\\s+FROM\\s+\\w+\\s+WHERE\\s+\\w+(\\s+)?[=><](\\s+)?(('\\w+')|(\\d+))(\\s+)?")) {
//			object.Select(s);
			return 9 ;
		}
		else if(s.matches("(?i)SELECT\\s+\\w+\\s+FROM\\s+\\w+\\s+WHERE\\s+\\w+(\\s+)?[=><](\\s+)?(('\\w+')|(\\d+))(\\s+)?")) {
//			object.Select(s);
			return 10 ;
		}
		else if (s.matches("(?i)DELETE\\s+FROM\\s+\\w+")) {
			//object.Delete(s);
			 
			return 11 ;
		}
		else if(s.matches("(?i)UPDATE\\s+\\w+\\s+SET\\s+((\\s+)?\\w+(\\s+)?=(\\s+)?((\\d+)|('\\w+'))(,)?)+")) {
			
			return 12;
		}
		
		
		return 0 ;
		
	}

}