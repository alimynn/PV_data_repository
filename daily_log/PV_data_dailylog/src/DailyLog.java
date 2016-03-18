import java.io.IOException;

public class DailyLog {

	public static void main(String[] args) throws IOException {
		DBConnect connection = new DBConnect();
		dataRead fstream = new dataRead();
		String[] content = new String[9];
		int[] p = new int[2];

		fstream.readFile(content);
		fstream.filter(content);
		connection.readData(p);

		connection.writeData(Double.parseDouble(content[0])/1000, Double.parseDouble(content[1])/1000, p[0], p[1]);

	}

}
