import java.io.File;
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

import ciphers.VigenereCipher;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
 
public class BadAES {
 
    private static SecretKeySpec secretKey;

    public static void main(String[] args) {
    	
    }
    
    private static byte[] key;
 
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