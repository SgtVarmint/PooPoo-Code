import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.io.*;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
 
public class DHAES {
 
    private static SecretKeySpec secretKey;
    private static byte[] key;
    
    
 //file input stream
    /**
     * call decrypt and encrypt with gen'd Key and byte array of read in file
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException{
    	FileInputStream in=null;
    	FileOutputStream out = new FileOutputStream("AES.txt");
    	//makes BigIntegers
    	BigInteger p = new BigInteger("521419622856657689423872613771");
    	BigInteger q = new BigInteger("153312796669816512924567214991");
    	BigInteger A = new BigInteger("116092797986921127517735587169");
    	
    	BigInteger b = new BigInteger("256");
    	    	    	
    	//math for key and shit
    	BigInteger B = q.modPow(b, p);

    	BigInteger key = A.modPow(b, p);
    	
    	//reads in the wanted files
    	File file = new File("data.aes");
    	byte[] data=null;
    	try{
    		in = new FileInputStream(file);
    		data = new byte[(int) file.length()];
    		in.read(data);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	byte[] newData = decrypt(data, key.toString());
    	
    	out.write(newData);
    	
    	in.close();
    	out.close();
    }
    
    public static void setKey(String myKey){
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            /*
             * AES is 16 bytes
             * DES is 8 bytes
             */
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static byte[] encrypt(byte[] strToEncrypt, String secret){
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

 
    public static byte[] decrypt(byte[] strToDecrypt, String secret){
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