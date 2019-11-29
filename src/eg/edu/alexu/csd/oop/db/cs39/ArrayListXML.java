package eg.edu.alexu.csd.oop.db.cs39;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class ArrayListXML {
	
	final String names="names";
	final String types="types";
	final String items="items";
	public void Xml(ArrayList<Table>t) throws IOException
	{
		//Table a=t.get(0);
		FileWriter File =new FileWriter("shakira\\gamda.xml");
		StringBuilder sb=new StringBuilder();
		sb.append("<Tables>");
		for(int i=0;i<t.size();i++)
		{
			sb.append("<Table"+Integer.toString(i)+">");
			sb.append("<"+names+">"+t.get(i).getNames()+"</"+names+">");
			sb.append("<"+types+">"+t.get(i).getTypes()+"</"+types+">");
			sb.append("<"+items+">"+t.get(i).getItems()+"</"+items+">");
			sb.append("</Table"+Integer.toString(i)+">");
		}
		sb.append("</Tables>");
		File.write(sb.toString());
		File.flush();
		File.close();
	}
	
	
	
}
