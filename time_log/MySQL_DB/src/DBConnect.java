import java.sql.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DBConnect {

	private Connection con;
	private Statement st;
	//private ResultSet rs;
	String db = "jdbc:mysql://localhost/solar";
	String user ="solar";
	String pw ="pvanlage";

	public DBConnect(){

		try{
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(db, user, pw);
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}
	}

	public void writeData(double Wtoday, int Pac, double n) {
		try {
			Date date = Calendar.getInstance().getTime();
			Format formatter = new SimpleDateFormat("YYMM");
			String datum = formatter.format(date);

/*			String query = "select * from statistik_"+datum+" where id=1";
			rs = st. executeQuery(query);
			System.out.println("Records from Database");
			while (rs.next()) {
				String leistung = rs.getString("leistung");
				String spannung = rs.getString("spannung");
				System.out.println("Leistung: " + leistung + "   Spannung: " + spannung +  "   Zeit: " + rs.getString("zeit"));
			}

			rs.moveToInsertRow();
			rs.updateDouble("Wtoday", Wtoday);
			rs.updateInt("Pac", Pac);
			rs.updateDouble("n", n);
			rs.insertRow();*/

			String query = "INSERT into statistik_" + datum + " (Wtoday, Pac, n) values (" + Wtoday + ", " + Pac + ", " + n + ")";
			st.execute(query);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
