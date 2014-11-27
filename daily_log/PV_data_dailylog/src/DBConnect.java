import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.*;
import java.util.Calendar;
import java.util.Date;


public class DBConnect {

	private Connection con;
	private Statement st;
	private ResultSet rs;
	String db = "jdbc:mysql://192.168.1.35/solar";
	String user = "solar";
	String pw = "pvanlage";

	public DBConnect() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(db, user, pw);
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}
	}

	public void writeData(double Wtot, double Wtoday, java.sql.Date now,
			int Pmax, int Pavg) {
		try {

			String query = "select * from statistik_daily where id=1";
			rs = st.executeQuery(query);

			rs.moveToInsertRow();
			rs.updateDate("datum", now);
			rs.updateDouble("Wtot", Wtot);
			rs.updateDouble("Wtoday", Wtoday);
			rs.updateInt("Pmax", Pmax);
			rs.updateInt("Pavg", Pavg);
			rs.insertRow();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void readData(int[] p) {		
		String[] temp = new String[60];
		
		try {
			int i = 0;
			
			Date date2 = Calendar.getInstance().getTime();
			Format formatter2 = new SimpleDateFormat("MMYY");
			String datum2 = formatter2.format(date2);
			
			Date date = Calendar.getInstance().getTime(); 
			Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			String datum = formatter.format(date); datum = datum.substring(5, 7) + datum.substring(8, 10);
			String query = "select * from statistik_" + datum2;
			String date21;
			rs = st. executeQuery(query);
			
			while (rs.next()) {
				String Pac = rs.getString("Pac");
				String zeit = rs.getString("zeit");
				date21 = zeit.substring(5, 7) + zeit.substring(8, 10);
				
				if(Integer.parseInt(datum) == Integer.parseInt(date21)) {
					temp[i] = Pac;
					i++;
				}				

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		for(int i = 0; i < temp.length; i++) {
			if(temp[i] != null)
				p[1] += Integer.parseInt(temp[i]);
		}
		p[1] = p[1] / 144;
		
		int itemp = 0;
		for(int i = 0; i < temp.length; i++) {
			if(temp[i] != null) {
				if(Integer.parseInt(temp[i]) > itemp)
					itemp = Integer.parseInt(temp[i]);
			}
		}
		
		p[0] = itemp;		
		
	}
}

