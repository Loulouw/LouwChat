package fr.loulouw.louwchat.event;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import fr.loulouw.louwchat.Main;
import fr.loulouw.louwchat.PlayerHologram;
import fr.loulouw.louwchat.Utils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import pl.betoncraft.betonquest.api.PlayerConversationEndEvent;
import pl.betoncraft.betonquest.api.PlayerConversationStartEvent;

import java.math.BigDecimal;
import java.util.HashMap;

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
        if (message.substring(0, 1).equals("!")) {
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

    private static void showMessage(Player p, String message) {
        if (playerHologram.containsKey(p)) {
            playerHologram.get(p).addText(message);
        } else {
            playerHologram.put(p, new PlayerHologram(p, message));
        }
    }

}
