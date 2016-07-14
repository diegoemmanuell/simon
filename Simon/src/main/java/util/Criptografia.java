package util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Criptografia{

     private static SecretKey        skey;

     private static KeySpec          ks;

     private static PBEParameterSpec ps;

     private static final String     ALGORITHM = "PBEWithMD5AndDES";

     private static BASE64Encoder    enc       = new BASE64Encoder();

     private static BASE64Decoder    dec       = new BASE64Decoder();

     public static final String encrypt(String text) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {

          Cipher cipher = Cipher.getInstance(ALGORITHM);
          cipher.init(1, skey, ps);
          return enc.encode(cipher.doFinal(text.getBytes()));
     }

     public static final String decrypt(String text) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {

          Cipher cipher = Cipher.getInstance(ALGORITHM);
          cipher.init(2, skey, ps);
          String ret = null;
          try {
               ret = new String(cipher.doFinal(dec.decodeBuffer(text)));
          } catch (Exception ex) {
               System.out.println(ex.getCause() + " " + ex.getMessage());
          }
          return ret;
     }

     static {
          try {
               SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);

               ps = new PBEParameterSpec(new byte[] { 3, 1, 4, 1, 5, 9, 2, 6 }, 20);

               ks = new PBEKeySpec("EAlGeEen3/dDdDeEEmiNnuU/YkO".toCharArray());

               skey = skf.generateSecret(ks);
          } catch (NoSuchAlgorithmException ex) {
               ex.printStackTrace();
          } catch (InvalidKeySpecException ex) {
               ex.printStackTrace();
          }
     }
}