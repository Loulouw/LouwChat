package fr.loulouw.louwchat;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static MessageDigest messageDigest;


    public static boolean isInteger(String value){
        boolean res = true;

        Pattern p = Pattern.compile("^[0-9]+$");
        Matcher m = p.matcher(value);
        if(!m.matches()) res = false;

        return res;
    }

    public static String hashMD5(String texte){
        if(messageDigest == null){
            try{
                messageDigest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        String res = "";
        if(texte != null && texte.length() != 0){
            messageDigest.update(texte.getBytes());
            byte[] hash = messageDigest.digest();
            StringBuffer hexString = new StringBuffer();
            for (byte aHash : hash) {
                if ((0xff & aHash) < 0x10) {
                    hexString.append("0" + Integer.toHexString((0xFF & aHash)));
                } else {
                    hexString.append(Integer.toHexString(0xFF & aHash));
                }
            }
            res = hexString.toString();
        }

        return res;
    }

}
