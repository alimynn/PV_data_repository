import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		DBConnect connection = new DBConnect();
		dataRead fstream = new dataRead();		
		String[] content = new String[9];
		
		fstream.readFile(content);
		fstream.filter(content);
		
		connection.writeData(Double.parseDouble(content[1])/1000, Integer.parseInt(content[3]), Double.parseDouble(content[4]));
		
		for(String x : content)
			System.out.println(x);		//Werte werden ausgegeben!
	}
	
}
