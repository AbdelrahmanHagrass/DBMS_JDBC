package eg.edu.alexu.csd.oop.db.cs39;


import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;



public class Mainclass {
	public static void validate(String xmlfile) throws ParserConfigurationException, SAXException, IOException {
		
//		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//		dbf.setValidating(true);
//		DocumentBuilder db = dbf.newDocumentBuilder();
//		db.setErrorHandler(new org.xml.sax.ErrorHandler() {
//
//			@Override
//			public void warning(SAXParseException exception) throws SAXException {
//				throw exception;
//			}
//
//			@Override
//			public void error(SAXParseException exception) throws SAXException {
//				throw exception;
//			}
//
//			@Override
//			public void fatalError(SAXParseException exception) throws SAXException {
//				throw exception;
//			}
//
//		});
//		FileInputStream file=new FileInputStream(xmlfile);
//		db.parse(file);
//		//file.close();
//	}
//
//	public static void deleteDir(File file) {
//		if (!file.isDirectory()) {
//
//			System.out.println(file.delete() + " j" + file.getName());
//			return;
//		}
//		if (file.list().length == 0) {
//			deleteEmptyDir(file);
//			return;
//		}
//		File files[] = file.listFiles();
//		for (File fileDelete : files) {
//			System.out.println(fileDelete.getName());
//			deleteDir(fileDelete);
//		}
//		if (file.list().length == 0) {
//			System.out.println(file.getName());
//			deleteEmptyDir(file);
//		}
//	}
//
//	public static void deleteEmptyDir(File file) {
//		System.out.println(file.getName() + " l");
//		file.delete();
//	}
//
//	public static void main(String[] args) throws Throwable {
//
//		DB first = new DB("hagrass");
//		Vector<String> names = new Vector<String>();
//		Vector<String> types = new Vector<String>();
//		names.add("ahmed");
//		types.add("VARCHAR");
//		names.add("aly");
//		types.add("VARCHAR");
//		names.add("ID");
//		types.add("INT");
//		Table a = first.createTable("gamed", names, types);
//		Vector<Object> input = new Vector<Object>();
//		input.add("Germany");
//		input.add("das");
//		input.add(123123);
//		a.InsertIntoTable(input);
//		input.clear();
//		input.add("Mexico");
//		input.add("das");
//		input.add(12312);
//		a.InsertIntoTable(input);
//		Table b = first.createTable("ww-s5a", names, types);
//		b.InsertIntoTable(input);
//		first.SaveDataBase();
//		System.out.println(a.getItems());
//		String l = "ahmed";
//		Vector<String> Condition = new Vector<String>();
//		Condition.add("Germany");
//		Condition.add("Mexico");
//	//	a.DeleteFromTableWithCondition("AHMED", "Germany");
//		System.out.println(a.getItems());
//		//
//		 a.UpdateWithCondition(1, "124", "ID", "AHMED", "hagrass");
//		 System.out.println(a.UpdateWithCondition(1, "124", "ID", "AHMED", "hagras222s"));
//		System.out.println(a.getItems());
//		first.SaveDataBase();
//		Table h=a.LoadTable(a.getFile().getAbsolutePath());
//		System.out.println(h.getItems());
//		Table c=first.createTable("ahmed", names, types);
//		c.InsertIntoTable(input);
//		c.InsertIntoTable(input);
//		first.SaveDataBase();
//		
//		DB second=new DB();
//		second=second.LoadDataBase(first.getAbsolutePath());
//      // System.out.println(second.getTables().size());
//	   Table temp=second.getTables().get(0);
//	   temp.InsertIntoTable(input);
//	  
//	   System.out.println(temp.getItems());
//	   System.out.println(b.getTypes());
//	   b=b.LoadTable(a.getFile().getAbsolutePath());
//	  b.InsertIntoTable(input);
//	  b.InsertIntoTable(input);
//	   System.out.println(a.getFile().getAbsolutePath());
//	   Table H=first.createTable("a0", names, types);
//	   first.SaveDataBase();
//	  H=H.LoadTable("hagrass\\gamed.xml");
//	  H.InsertIntoTable(input);
//	  H.InsertIntoTable(input);
//	  Object aj[][] = H.SelectTable();
//	   for(int i=0;i<aj.length;i++)
//	   {
//		   for(int j=0;j<aj[0].length;j++)
//		   {
//			   System.out.println(aj[i][j]);
//		   }
//	   }
//	  System.out.println(H.getTypes());
//	}	
//	public static void validate(String xmlfile,String Schema) throws SAXException, IOException
//	{
//		SchemaFactory schemaFactory=SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//		schemaFactory.newSchema(new File(Schema)).newValidator().validate(new StreamSource(new File(xmlfile)));
//	}

}
}