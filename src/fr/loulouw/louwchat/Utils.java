package fr.loulouw.louwchat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static boolean isInteger(String value){
        boolean res = true;

        Pattern p = Pattern.compile("^[0-9]+$");
        Matcher m = p.matcher(value);
        if(!m.matches()) res = false;

        return res;
    }

}
