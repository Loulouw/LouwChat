package fr.loulouw.louwchat.event;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import fr.loulouw.louwchat.Eg;
import fr.loulouw.louwchat.Main;
import fr.loulouw.louwchat.PlayerHologram;
import fr.loulouw.louwchat.Utils;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Sound;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import static org.bukkit.Bukkit.getServer;


public class EventPlayerChat implements Listener {


    public static HashMap<Player, PlayerHologram> playerHologram;
    public HashMap<Player, Boolean> playerQuest;
    public static BigDecimal prixAll;
    public static int nombreSeconde;

    public EventPlayerChat() {
        getServer().getPluginManager().registerEvents(this, Main.javaPlugin);
        playerHologram = new HashMap<>();
        playerQuest = new HashMap<>();
        prixAll = BigDecimal.valueOf(Main.javaPlugin.getConfig().getDouble("prixAll"));
        nombreSeconde = Main.javaPlugin.getConfig().getInt("tempsParMessage");

    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerChat(PlayerChatEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage();
        boolean cancel = true;

        /*if (playerQuest.containsKey(p) && playerQuest.get(p) && Utils.isInteger(message)) {
            cancel = false;
        } else*/

        if (Eg.ee1.equals(Utils.hashMD5(message))) {
            funcEe1(p);
        } else if (message.substring(0, 1).equals("!")) {
            message = message.substring(1, message.length());
            e.setMessage(message);
            if (!p.hasPermission("louwchat.all")) {
                try {
                    BigDecimal money = Economy.getMoneyExact(p.getName());
                    if (money.compareTo(prixAll) > 0) {
                        Economy.substract(p.getName(), prixAll);
                        p.sendMessage(ChatColor.GREEN + "Moins " + ChatColor.GOLD + prixAll.doubleValue() + ChatColor.GREEN + "$ pour l'envoi du message.");
                        showMessage(p, message);
                        cancel = false;
                    } else {
                        p.sendMessage(ChatColor.RED + "Vous n'avez pas assez d'argent pour parler en général. (Nécessaire : " + ChatColor.GOLD + prixAll.doubleValue() + ChatColor.RED + " )");
                    }
                } catch (UserDoesNotExistException | NoLoanPermittedException e2) {
                    e2.printStackTrace();
                }
            } else {
                showMessage(p, message);
                cancel = false;
            }

        } else {
            showMessage(p, message);
        }


        e.setCancelled(cancel);
    }

    private void funcEe1(Player p) {
        PotionEffect p1 = new PotionEffect(PotionEffectType.SLOW_DIGGING, 15 * (Eg.ee1f().size() + 1), 2);
        PotionEffect p2 = new PotionEffect(PotionEffectType.CONFUSION, 15 * (Eg.ee1f().size() + 1), 4);
        PotionEffect p3 = new PotionEffect(PotionEffectType.SLOW, 15 * (Eg.ee1f().size() + 1), 4);


        p.addPotionEffect(p1);
        p.addPotionEffect(p2);
        p.addPotionEffect(p3);

        new BukkitRunnable() {

            int index = 0;
            int temp = 3;

            @Override
            public void run() {
                if (index < Eg.ee1f().size()) {
                    if(temp==0 || ThreadLocalRandom.current().nextInt(1,3) == 1){
                        temp = 3;
                        Firework f = p.getWorld().spawn(p.getLocation(), Firework.class);
                        FireworkMeta fm = f.getFireworkMeta();
                        fm.addEffect(FireworkEffect.builder()
                                .flicker(false)
                                .trail(true)
                                .with(Utils.getRandomFireWorkEffectType())
                                .withColor(Utils.getRandomColor())
                                .withFade(Utils.getRandomColor())
                                .build());
                        fm.setPower(0);
                        f.setFireworkMeta(fm);
                    }
                    String txt = Eg.ee1f().get(index);
                    p.sendMessage(Utils.getRandomChatColor() + "" + ChatColor.BOLD + txt.substring(0,txt.length()-1));
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 1);
                    index++;
                    temp--;
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(Main.javaPlugin, 0L, 15L);
    }

    private static void showMessage(Player p, String message) {
        if (playerHologram.containsKey(p)) {
            playerHologram.get(p).addText(message);
        } else {
            playerHologram.put(p, new PlayerHologram(p, message));
        }
    }

}
