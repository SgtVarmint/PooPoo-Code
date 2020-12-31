import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

//import ciphers.VigenereCipher;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/*
 * Brute force attack method
 */
public class BadAES {

	private static SecretKeySpec secretKey;
	private static byte[] key;

	public static void main(String[] args) throws IOException {
		byte[] data = null;
		FileInputStream in = null;
		FileOutputStream out=new FileOutputStream("badAes.txt");
		File file = new File("bad.aes");
		
		//FileInputStream to read in the file
		try{
			in = new FileInputStream(file);
			data = new byte[(int)file.length()];
			in.read(data);
		}catch(Exception e){
			e.printStackTrace();
		}

		boolean found = false;
		byte[] keyByte = new byte[4];
		//gif files start with GIF89, answer checks for that string of characters
		String check = "GIF89";
		String temp1 = "", key="";
		byte[] decrypted = null;

		long startTime = System.currentTimeMillis();
		//four loops to check each combo of 4 length lowerCase keyWords
		for(int firstChar = 'a'; firstChar <= 'z' && !found; firstChar++) 				
			for(int secondChar = 'a'; secondChar <= 'z' && !found; secondChar++) 			
				for(int thirdChar = 'a'; thirdChar <= 'z' && !found; thirdChar++) 		
					for(int fourthChar = 'a'; fourthChar <= 'z' && !found; fourthChar++) {
						//sets the key to the char in each for loop
						keyByte[0] = (byte) firstChar;
						keyByte[1] = (byte) secondChar;
						keyByte[2] = (byte) thirdChar;
						keyByte[3] = (byte) fourthChar;
						
						//empties the key before use
						key="";
						//puts each char into the key String
						for(int i=0;i<keyByte.length;i++)
							key += (char)keyByte[i];
					
						decrypted = decrypt(data, key);

						
						if(decrypted != null) {
							//used to check the temp1 array for the first GIF89 chars in the decrypted file
							for(int j=0;j<check.length();j++)
								temp1 += (char) decrypted[j];
							
							//if it finds the correct key
							if(temp1.equals(check)) {
								found = true;
								System.out.printf("\nKey: %s\nFile was correctly decrypted.",key);
							}
							//if it hasn't found it, it empties the string
							else
								temp1="";
						}						
					}
		//just used to time how long it takes to brute force
		long endTime = System.currentTimeMillis() - startTime;
		System.out.printf("\nIt took %d seconds to complete.\n",(endTime/1000)%60);
		out.write(decrypted);
		out.close();
	}


	public static void setKey(String myKey) 
	{
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");


			sha = MessageDigest.getInstance("SHA-1");

			key = Arrays.copyOf(key, 16);

			for (byte item: key) System.out.print(item + " ");
			System.out.println();

			secretKey = new SecretKeySpec(key, "AES");

		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}



	public static byte[] encrypt(byte[] strToEncrypt, String secret)
	{
		try
		{
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return cipher.doFinal(strToEncrypt);
		}
		catch (Exception e)
		{
			System.out.println("Error while encrypting bytes: " + e.toString());
		}
		return null;
	}


	public static byte[] decrypt(byte[] strToDecrypt, String secret)
	{
		try
		{
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return cipher.doFinal(strToDecrypt);
		}
		catch (Exception e)
		{
			System.out.println("Error while decrypting bytes: " + e.toString());
		}
		return null;
	}


}