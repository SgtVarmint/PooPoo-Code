/*Team: Austin Reth & Joe Divinagracia
 * Date: 9/4/17
 * Class: CS221 A
 * Prof: Dr. Wittman
 * Project 1
 * 
 * Purpose: Read .bmp files and transform image accordingly
 * 
 * No Padding:
 * -delaware
 * -jonah
 * -sleeping
 * 
 * Padding:
 * -dan
 * -maybe
 * -amy.bmp
 */
import java.io.*;
import java.util.Scanner;

public class Manipulator extends Thread{
	static FileInputStream in;
	static FileOutputStream out;

	public static void main(String[] args) throws IOException{

		//Timing Variables
		double start;
		double run;

		//File variables
		String file;
		File fileLocation;
		String newFile;
		File writeLocation;
		Scanner console;

		console=new Scanner(System.in);

		//file input
		System.out.print("What image file would you like to edit (do not include '.bmp' in the file name): ");
		file=console.next();

		fileLocation = new File(file);//attaches '.bmp' to end of file name

		while(!fileLocation.exists()) {//if the file does not exist, prompt for re-enter
			System.out.print("File does not exist."+"\nWhat image file would you like to edit (do not include '.bmp' in the file name): ");
			file=console.next();

			fileLocation = new File(file+".bmp");		
		}

		try{
			in=new FileInputStream(fileLocation);//attempts to find file
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}

		Bitmap bitmap=new Bitmap();//calls constructor

		Bitmap.choice();//calls choice method
		
		//saving files
		System.out.print("What do you want to name the new image : ");
		newFile=console.next();

		File directory=new File("Test\\");//creates file directory name
		
		if(!directory.exists())//creates directory if specified directory does not exist
			directory.mkdir();

		writeLocation=new File(directory+"\\"+newFile+".bmp");//sets path to file

		out=new FileOutputStream(writeLocation);

		start=System.nanoTime();

		try {

			Bitmap.print();//calls print method from Bitmap

			run=(System.nanoTime()-start)*1e-9;
			System.out.printf("File %s has been written in %.2f seconds to %s",newFile,run,writeLocation);
		}catch(Exception e) {
			System.out.println("Could not write file. RIP.");
		}finally {
			//close all input and output streams
			if(in!=null) in.close();
			if(out!=null) out.close();
			console.close();
		}
	}
}