/*Team: Austin Reth & Joe Divinagracia
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

public class Bitmap {

	byte[] size=new byte[4];
	byte pixel;
	public static byte[][] pic;
	static int padding;//amount of padding needed
	static int picWidth;//width of picture
	static int picHeight;//height of picture
	static byte[] header;//array in which header is stored
	static int length;//picture width * 3
	static int widthIndex = 18;//index of width inside the header
	static int heightIndex = 22;//index of height inside the header
	static int sizeIndex = 2;//index of size inside the header
	static int dataSizeIndex = 34;//index of datasaize inside the header
	static int picSize;//final size of file
	static int picDataSize;//size of color data

	public Bitmap() throws IOException {
		header = readBytes(54);//read header into array

		//read all necessary data out of header
		picWidth =  readHeader(header, widthIndex);
		picHeight =  readHeader(header, heightIndex);
		picSize =  readHeader(header, sizeIndex);
		picDataSize =  readHeader(header, dataSizeIndex);

		length=picWidth*3;		
		padding=(length%4);//sets up padding equation
		pic=new byte[picHeight][length];//initializes array for color data

		//read in color data
		for(int row=0;row<picHeight;row++) {
			for(int col=0;col<length;col++) {
				pixel=(byte) Manipulator.in.read();
				pic[row][col]=pixel;
			}
			
			//chops off padding from each row
			if(padding!=0) {
				for(int k=0;k<4-padding;k++) {
					Manipulator.in.read();
				}
			}
		}
	}

	public static void invert(byte[][] array){
		//initializes variables for alteration
		int height = array.length;
		int width = array[0].length;
		int[][] temp = new int[height][width];
		byte[] color = new byte[1];
		byte num;
		int[] store = new int[1];
		int adjust;

		for(int row=0;row<picHeight;row++) {//takes the bytes and converts them to ints
			for(int col=0;col<length;col++) {
				num = array[row][col];
				color[0] = num;
				store = toInt(color);
				temp[row][col] = store[0];
			}
		}
		for(int row = 0; row < height; row++){//adjusts the color of each item
			for(int col = 0; col < width; col++){
				if(col == 0 || col % 3 == 0){//for blue color
					adjust = temp[row][col];
					adjust = (int) (255 - adjust);
					temp[row][col] = adjust;
				}
				else if((col + 2) % 3 == 0){//for green color
					adjust = temp[row][col];
					adjust = (int) (255 - adjust);
					temp[row][col] = adjust;

				}
				else{//for red color
					adjust = temp[row][col];
					adjust = (int) (255 - adjust);
					temp[row][col] = adjust;
				}
			}
		}
		for(int row = 0; row < height; row++){//takes the bytes and converts them to ints
			for(int col = 0; col < width; col++){
				adjust = temp[row][col];
				num = (byte) adjust;
				array[row][col] = num;
			}
		}
	}

	public static void grayScale(byte[][] array){
		//initializes variables for alteration
		int height = array.length;
		int width = array[0].length;
		int[][] temp = new int[height][width];
		byte num;
		int adjust;
		double round;

		for(int row = 0; row < height; row++){//takes the bytes and converts them to ints
			for(int col = 0; col < width; col++){
				num = array[row][col];
				temp[row][col] = byteToInt(num);
			}
		}
		for(int row = 0; row < height; row++){//adjusts the color of each item
			for(int col = 0; col < width; col++){
				if(col == 0 || col % 3 == 0){//for blue color
					round = temp[row][col];
					temp[row][col] = (int) ((round * .11) + .5);
				}
				else if((col + 2) % 3 == 0){//for green color
					round = temp[row][col];
					temp[row][col] = (int) ((round * .59) + .5);

				}
				else{//for red color
					round = temp[row][col];
					temp[row][col] = (int) ((round * .3) + .5);
				}
			}
		}
		for(int row = 0; row < height; row++){//takes the total for each color value of each pixel
			for(int col = 0; col < width; col+=3){
				int blue, green, red, total;
				blue = temp[row][col];
				green = temp[row][col + 1];
				red = temp[row][col + 2];
				total = blue + green + red;
				for(int k = 0; k < 3; k++)
					temp[row][col + k] = total;
			}
		}
		for(int row = 0; row < height; row++){//takes the bytes and converts them to ints
			for(int col = 0; col < width; col++){
				adjust = temp[row][col];
				array[row][col] = (byte) adjust;
			}
		}
	}

	public static byte[][] blur(byte[][] array){//throw outofboundsexception
		//initializes variables for alteration
		int width = array[0].length;
		int height = array.length;
		int pixCount, total, colorTotal, colorAmt;
		byte[][] temp = new byte[height][width];

		for(int row = 0; row < height; row ++ ){//runs through the height of the array
			for(int col = 0; col < width; col++){//runs through the width of the array
				colorTotal = 0;//sum of all bytes in a 5 by 5 square around it
				pixCount = 0;//amount of bytes that where added together
				for(int k = -6; k <= 6; k+=3){//grabs the byte amount from 2 rows above to 2 rows below
					for(int m = -6; m <= 6; m+=3){//grabs the byte amount from 2 columns to the left to 2 columns to the right
						if((row + k) < 0 || (row + k) >= height || (col + m) < 0 || (col + m) >= width)//if the byte being looked at is off the array index
							continue;
						else{//when the byte does exist
							colorAmt = byteToInt(array[row + k][col + m]);//grabs the byte amount and adds it to the total
							colorTotal += colorAmt;
							pixCount++;
						}
					}
				}
				temp[row][col] = (byte)(colorTotal / pixCount);//averages colorData of 4 pixels
			}
		}
		array = temp;
		return array;
	}

	public static byte[][] verticalMirror(byte[][] array){
		//initializes variables for manipulation
		int height = array.length;
		int length = array[0].length;
		byte[][] temp = new byte[height][length];

		for(int row = height - 1, k = 0; row > 0; row--, k++){//switches top rows with bottom rows
			for(int col = 0; col < length; col++){
				temp[row][col] = array[row][col];
			}
		}
		return temp;
	}

	public static byte[][] shrink(byte[][] array){//check length and width
		//initializes variables for alteration
		int length = array[0].length;//width of original picture
		int height = array.length;//height of original picture
		int width = length / 3;
		int newWidth, newHeight, pixTotal;//the newWidth and newHeight are the dimensions of the shrunken picture, pixTotal and store are used to convert from byte to int to double and back again
		byte[][] temp;//where the shrunken picture will be stored
		byte data;//will pull the color data out at a given index
		double total;//will total color data for the four pixels that are being combined
		int cNext;

		//makes sure height and width are evenly divisble by 2
		if(height % 2 != 0)
			height -= 1;
		if(width % 2 != 0){
			width -= 1;
			length = width * 3;
		}

		//sets shrunken size for new array
		newHeight = (int) (height * .5);
		newWidth = (int) ((width * .5) * 3);
		temp = new byte[newHeight][newWidth];

		for(int row = 0, rowTemp = 0; row < height - 1; row += 2, rowTemp++){//goes through all rows except for the last one of original picture, but does every other row
			for(int col = 0, colTemp = 0; col < length; col++, colTemp++){//goes through each column of the original picture
				//cTemp and rTemp keep track of the row and column for the shrunken picture array
				total = 0;
				for(int k = 0; k <= 1; k += 1){//checks pixels below
					for(int m = 0; m <= 3; m += 3){
						try{
							data = array[row + k][col + m];
							total += byteToInt(data);
							}catch(ArrayIndexOutOfBoundsException e){
								e.printStackTrace();
							}
						}
				}
				total /= 4;//takes the average of the color data collected
				pixTotal = (int)(Math.round(total));//takes that total and rounds it to the nearest int
				temp[rowTemp][colTemp] = (byte) (pixTotal);//casts the int to a byte and stores it in the array
				//end, increment c if end of pixel is reached and we need to skip over the next one, watch for out of bounds
				cNext = col;
				cNext++;
				if(cNext % 3 == 0)
					col += 3;
			}
		}
		return temp;

	}

	public static byte[][] doubleSize(byte[][] array){
		//initializes variables for alteration
		int height = array.length;
		int length = array[0].length;
		int width = length / 3;
		int newHeight = height * 2;
		int newWidth = width * 2;
		int newLength = newWidth * 3;
		int colNext;//indicates when to skip over pixel in a row after doubling
		byte[][] temp = new byte[newHeight][newLength];

		for(int row = 0, rowTemp = 0; row < height; row++, rowTemp += 2){//runs through the height of the original picture and only every other of the new picture
			for(int col = 0, colTemp = 0; col < length; col++, colTemp++){//runs through the length of the original picture
				for(int k = 0; k <= 1; k++){
					for(int m = 0; m <= 3; m += 3){
						temp[rowTemp + k][colTemp + m] = array[row][col];//stores the color data for one pixel into four pixels in the new array
					}
				}

				colNext = colTemp;
				colNext++;
				if(colNext % 3 == 0)
					colTemp += 3;
			}
		}
		return temp;
	}

	public static byte[][] rotate(byte[][] array){//sent current width and height before rotation. In reality this transposes the matrix of pixels
		//initializes variables for alteration
		byte[] pixelColor = new byte[3];//will store color data for one pixel
		int length = array[0].length;
		int height = array.length;
		int width = length / 3;
		byte[][] temp = new byte[width][height * 3];//sets the temp array to the dimensions after rotation
		int tempRow = 0;//row number in temp array
		int pixIn = 0;//index for pixelColor array
		int pixelCol = 0;

		for(int row = 0; row < height; row++){//goes down the original height of the picture
			for(int col = 0; col < length; col++){//goes across the original width of the picture
				if(col  % 3 == 0)//resets the index for pixelColor  (pixIn)
					pixIn = 0;
				pixelColor[pixIn] = array[row][col];//stores pixel data from array
				pixIn++;
				if(pixIn == 2){//transposes matrix
					for(int index = 0, tempCol = 0; index < 3; index++, tempCol++){
						temp[tempRow][tempCol + (pixelCol * 3)] = pixelColor[index];
					}
					tempRow++;
				}
			}
			tempRow = 0;
			pixelCol++;
		}//transpose then mirror
		return temp;
	}

	public static byte[][] horizontalMirror(byte[][] array){
		//initializes variables for alteration
		int length=array[0].length;
		int height=array.length;
		byte[][] temp = new byte[height][length];
		byte[] pixelColor = new byte[3];//will store color data for one pixel
		int pixIn = 0;//index for pixelColor array
		int colorNum = 0;

		for(int row = 0; row < height; row++){//goes down the original height of the picture
			for(int col = length - 1; col >= 0; col--){//goes across the original width of the picture
				if((col + 1)  % 3 == 0)//resets the index for pixelColor  (pixIn)
					pixIn = 0;
				pixelColor[pixIn] = array[row][col];
				pixIn++;
				if(pixIn == 2){
					byte blue, green, red;//reverses the order of color data to the correct order
					red = pixelColor[0];
					green = pixelColor[1];
					blue = pixelColor[2];
					pixelColor[0] = blue;
					pixelColor[1] = green;
					pixelColor[2] = red;

					for(int index = 0; index < 3; index++){//stores data into array
						temp[row][colorNum] = pixelColor[index];
						colorNum++;
					}
				}
			}
			colorNum = 0;	
		}
		return temp;
	}

	public static void choice() {
		//timing variables
		double start;
		double run;
		//input variable
		char choice;
		
		byte[][] temp;

		Scanner console=new Scanner(System.in);

		do{//loops while the user does not enter 'q'
			System.out.print("What command would you like to perform(i, g, b, v, s, d, r, h, or q): ");
			String stringChoice=console.next().toLowerCase();
			choice=stringChoice.charAt(0);

			switch(choice) {

			case('i')://invert works
				start=System.nanoTime();

				invert(pic);

				run=(System.nanoTime()-start)*1e-9;//converts from nano seconds to seconds
				System.out.printf("Invert took %.2f seconds to run\n", run);
				break;

			case('g')://grayscale works
				start=System.nanoTime();

				grayScale(pic);

				run=(System.nanoTime()-start)*1e-9;
				System.out.printf("Grayscaling took %.2f seconds to run\n", run);
				break;

			case('b')://blur works
				start=System.nanoTime();

				pic= blur(pic);

				run=(System.nanoTime()-start)*1e-9;//converts from nano seconds to seconds
				System.out.printf("Blurring took %.2f seconds to run\n", run);
				break;

			case('v')://vertical Mirror works
				start=System.nanoTime();

				pic= verticalMirror(pic);

				run=(System.nanoTime()-start)*1e-9;
				System.out.printf("Vertical Mirroring took %.2f s to run\n",run);
				break;

			case('s')://shrink works
				start=System.nanoTime();

				//updates variables for shrunken picture
				pic =  shrink(pic);
				length = pic[0].length;
				picHeight = pic.length;
				picWidth = length / 3;
				padding = length % 4;
				
				//calls method to alter header variables
				alterHeader(header, widthIndex, picWidth);
				alterHeader(header, heightIndex , picHeight);

				run=(System.nanoTime()-start)*1e-9;//converts from nano seconds to seconds
				System.out.printf("Shrinking took %.2f s to run\n",run);
				break;

			case('d')://doubleSize works
				start=System.nanoTime();

				//updates variables for enlarged picture
				pic =  doubleSize(pic);
				length = pic[0].length;
				picHeight = pic.length;
				picWidth = length / 3;
				padding = length % 4;
				
				//calls method to alter header variables
				alterHeader(header, widthIndex, picWidth);
				alterHeader(header, heightIndex , picHeight);

				run=(System.nanoTime()-start)*1e-9;
				System.out.printf("Double Size took %.2f s to run\n",run);
				break;

			case('r')://rotate
				start=System.nanoTime();

				pic = rotate(pic);
				//updates variables for rotated picture
				int replace = picWidth;
				picWidth = picHeight;
				picHeight = replace;
				length = picWidth * 3;
				padding = length % 4;
				pic = horizontalMirror(pic);
				
				alterHeader(header, widthIndex, picWidth);
				alterHeader(header, heightIndex , picHeight);

				run=(System.nanoTime()-start)*1e-9;
				System.out.printf("Rotating took %.2f seconds to run\n", run);
				break;
				
			case('h')://horizontal Mirror works
				start=System.nanoTime();

				pic =  horizontalMirror(pic);

				run=(System.nanoTime()-start)*1e-9;
				System.out.printf("Horizontal Mirroring took %.2f seconds to run\n", run);
				break;
			}
		}while(choice!='q');//do-while loops through transformation block while the user does not input q
	}

	public static void print() throws IOException {
		
		picDataSize = picHeight * ((picWidth * 3) + (4 - padding));//updates final dataSize (color data)
		picSize = 54 + picDataSize + 2;//updates size of file
		Bitmap.alterHeader(header, sizeIndex, picSize);//store variable in header
		Bitmap.alterHeader(header, dataSizeIndex, picDataSize);//store variable in header
		
		//writes out file, adding padding and final 2 bytes at the end
		Manipulator.out.write(header);
		for(int j=0;j<pic.length;j++) {
			Manipulator.out.write(pic[j]);
			if(padding !=0) {
				for(int i=0;i<4-padding;i++){
					Manipulator.out.write(0x00);
				}
			}
		}
		Manipulator.out.write(0x00);
		Manipulator.out.write(0x00);
	}

	public static int[] toInt(byte[] color){
		int[] temp=new int[color.length];//converts byte array to int
		for(int j=0;j<color.length;j++){
			temp[j]=color[j];
		}
		return temp;
	}

	public static int byteToInt(byte num){
		//converts a single byte to an int
		int value;
		if (num < 0) {
			value = 256 + num;
		} else {
			value = num;
		}

		return value;
	}

	public static byte[] readBytes(int length) throws IOException{
		//method to read header
		byte[] array=new byte[length];
		int index=0;

		while(index<length) {
			array[index]=(byte) Manipulator.in.read();
			index++;
		}
		return array;
	}

	public static int readHeader(byte[] header, int index){
		//reads int out of header at a specified index
		int value = 0;
		byte[] temp = new byte[4];

		for(int i = 0; i < 4; i++)
			temp[i] = header[index + i];

		for ( int j = 0; j < temp.length; j++)
			value += ((int) temp[j] & 0xffL) << (8 * j);

		return value;
	}

	public static byte[] alterHeader(byte[] header, int index, int value){
		//stores given int as a byte array in the header at a specified index
		 
		
		byte[] temp = new byte[4];

		for(int i = 3; i >= 0; i--)//replaces intToByteArray
			temp[i] = (byte) (value >> (8 * i));

		for(int j = 0; j < 4; j++)
			header[index + j] = temp[j];

		return header;
	}
}
