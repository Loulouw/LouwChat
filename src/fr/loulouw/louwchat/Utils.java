package fr.loulouw.louwchat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Firework;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static MessageDigest messageDigest;
    private static ObservableList<ChatColor> chatColors;
    private static ObservableList<FireworkEffect.Type> fireworkEffectType;
    private static ObservableList<Color> colors;

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

    private static int getRandomOfList(ObservableList o){
        return ThreadLocalRandom.current().nextInt(0,o.size()-1);
    }

    public static ChatColor getRandomChatColor(){
        if(chatColors == null){
            chatColors = FXCollections.observableArrayList();
            chatColors.addAll(ChatColor.AQUA,ChatColor.BLACK,ChatColor.BLUE,ChatColor.DARK_AQUA,ChatColor.DARK_BLUE,ChatColor.DARK_GRAY,ChatColor.DARK_GREEN,ChatColor.DARK_PURPLE,ChatColor.DARK_RED,ChatColor.GOLD,ChatColor.GRAY,ChatColor.GREEN,ChatColor.LIGHT_PURPLE,ChatColor.RED,ChatColor.WHITE,ChatColor.YELLOW);
        }
        return chatColors.get(getRandomOfList(chatColors));
    }

    public static FireworkEffect.Type getRandomFireWorkEffectType(){
        if(fireworkEffectType==null){
            fireworkEffectType = FXCollections.observableArrayList();
            fireworkEffectType.addAll(FireworkEffect.Type.BALL,FireworkEffect.Type.BALL_LARGE,FireworkEffect.Type.BURST,FireworkEffect.Type.CREEPER,FireworkEffect.Type.STAR);
        }
        return fireworkEffectType.get(getRandomOfList(fireworkEffectType));
    }

    public static Color getRandomColor(){
        if(colors==null){
            colors = FXCollections.observableArrayList();
            colors.addAll(Color.AQUA,Color.BLACK,Color.BLUE,Color.FUCHSIA,Color.GRAY,Color.GREEN,Color.LIME,Color.PURPLE,Color.MAROON,Color.NAVY,Color.OLIVE,Color.ORANGE,Color.RED,Color.SILVER,Color.TEAL,Color.WHITE,Color.YELLOW);
        }
        return colors.get(getRandomOfList(colors));
    }

}
