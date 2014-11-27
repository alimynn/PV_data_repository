import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		DBConnect connection = new DBConnect();
		dataRead fstream = new dataRead();
		String[] content = new String[9];
		int[] p = new int[2];

		
		java.util.Date now = new java.util.Date();
		java.sql.Date date = new java.sql.Date(now.getTime());
		
		fstream.readFile(content);
		fstream.filter(content);
		connection.readData(p);
		
		connection.writeData(Double.parseDouble(content[0])/1000, Double.parseDouble(content[1])/1000, date, p[0], p[1]);
		
	}
	
}
