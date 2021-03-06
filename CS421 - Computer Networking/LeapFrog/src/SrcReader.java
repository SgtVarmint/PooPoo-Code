import java.io.*;
import java.util.*;

public class SrcReader {
	String fileName;
	File infile;
	Scanner src;
	char ch=' ';
	String line="";
	int pos=0;//track pos in line
	public static final char EOF=(char)4;//sends end of file
	public static final char EOL='\n';//sends end of line
	
	public SrcReader(String name) throws FileNotFoundException {
		fileName=name;
		infile=new File(fileName);
		src=new Scanner(infile);
	}

	//could read entire file at once then go back and read char by char
	//but do this instead
	public char nextCh() {
		if(pos>=line.length()) {//gone past line length
			if(src.hasNextLine()) {
				line=src.nextLine();
				System.out.println(line);
				pos=0;
				return EOL;
			}
			else
				return EOF;
		}
		ch=line.charAt(pos++);
		return ch;
	}
}
