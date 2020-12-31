import java.util.*;
import java.io.*;

public class LFSR {

	public static void main(String[] args) {
		FileInputStream in;
		FileOutputStream out;
		String messageFile = "LFSR.encrypted";
		
		//for LFSR.encrypted
		// (x^8)+(x^4)+(x^3)+(x^2)+1
		byte fill1 = (byte) 255;
		byte[] array = new byte[8];
		
		//for LFSRs.encrypted
		byte fill2 = (byte) 126;
		//(x^8)+(x^6)+(x^5)+(x)+1
		byte fill3 = (byte) 253;
		//(x^8)+(x^5)+(x^3)+(x^2)+1
		byte fill4 = (byte) 55;
		//(x^8)+(x^7)+(x^6)+(x^3)+(x^2)+(x)+1
		
		byte output = (byte) ( ((fill1%Math.pow(2,8))+(fill1%Math.pow(2,4))+(fill1%Math.pow(2,3))+(fill1%Math.pow(2,2))+1) );
		
		String line = null;
		
		try {
			FileReader file = new FileReader(messageFile);
			
			BufferedReader buffer = new BufferedReader(file);
			
			while((line = buffer.readLine()) !=null)
				System.out.println(line);
			buffer.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
