package eg.edu.alexu.csd.oop.db.cs39;

import java.io.File;
import java.io.IOException;

public class CreateDB {
	
	static int c = 0 ;
	
	public CreateDB() {
		
		c++;	
		String fileSeparator = System.getProperty("file.separator");
		String relativePath = "MainDirectory"+fileSeparator+"db"+String.valueOf(c);
        File file = new File(relativePath);
        if(file.mkdir())
        {
		    System.out.println(relativePath+" File Created in Project root directory");
		}
        else
        {
        	System.out.println("File "+relativePath+" already exists in the project root directory");
        }
        //System.out.println(file.getAbsolutePath());
//        try {
//			System.out.println(file.getCanonicalPath());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        	
	}

}
