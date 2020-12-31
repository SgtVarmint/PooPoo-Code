import java.io.*;

public class MegaBasic {
	public static void main(String[] args) throws IOException {
		File file = new File("test.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String str;
		
		while((str = br.readLine()) != null) {
			System.out.println(str);
		}
	}
}
