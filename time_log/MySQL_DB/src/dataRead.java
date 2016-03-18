import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class dataRead {

	public void readFile(String[] content) throws IOException {

		Process proc = Runtime.getRuntime().exec("/home/stefan/pv-anlage/piko.py --host 192.168.1.8 -t -i -d -p -q");
		BufferedReader in = new BufferedReader(new InputStreamReader(
				proc.getInputStream()));
		String strLine = "";

		for(int i = 0; i < content.length; i++) {
			if((strLine = in.readLine()) != null)
				content[i] = strLine;
			else
				break;
		}

	}

	public void filter(String[] content) {

		String[] temp = new String[content.length];

		for(int i = 0; i < temp.length; i++) {
			temp[i] = content[i];
			content[i] = "";
		}

		content[0] = temp[0];
		content[1] = temp[1];

		temp[2] += " ";
		//char[] ctemp = temp[2].toCharArray();
		String stemp = "";
		int iter = 0;
		for(int i = 0; i < temp[2].length(); i++) {
			if(temp[2].charAt(i) == ' ') {
				content[(2+iter)] = stemp;
				iter++;
				stemp = "";
			}
			else
				stemp += temp[2].charAt(i);
		}

		if(Double.parseDouble(content[4]) >= 100)
		{
			content[4] = "98.9";
		}

		//ctemp = temp[3].toCharArray();
		//char[] c2temp = temp[6].toCharArray();
		//char[] c3temp = temp[7].toCharArray();
		//char[] c4temp = temp[8].toCharArray();

		for(int i = 0; i < 5; i++) {
			content[5] += temp[3].charAt(i);
			content[6] += temp[6].charAt(i);
			content[7] += temp[7].charAt(i);
			content[8] += temp[8].charAt(i);
		}
	}

}
