package fr.loulouw.louwchat;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import fr.loulouw.louwchat.event.EventPlayerChat;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerHologram {

    private PersonnalList<String> listeMessages;
    private Hologram hologram;
    private Player p;
    private boolean canDelete = false;
    private double hauteurAjoute = 2.1;

    public PlayerHologram(Player p, String message) {
        listeMessages = new PersonnalList<>(this);
        this.p = p;
        hologram = HologramsAPI.createHologram(Main.javaPlugin, p.getLocation().add(0, hauteurAjoute, 0));
        addText(message);
        moveHolo();
    }

    public void changement(){
        if (listeMessages.size() != 0) {
            if (listeMessages.size() > 3) {
                listeMessages.remove(0);
            }
            hologram.clearLines();
            hauteurAjoute = 2.3 + listeMessages.size() * 0.27;
            for (String s : listeMessages.getList()) hologram.appendTextLine(s);
        } else {
            canDelete = true;
        }
    }

    public void addText(final String message) {
        listeMessages.add(message);
        new BukkitRunnable() {
            @Override
            public void run() {
                listeMessages.remove(message);
            }
        }.runTaskLater(Main.javaPlugin, 20 * EventPlayerChat.nombreSeconde);
    }

    private void moveHolo() {
        new BukkitRunnable() {
            @Override
            public void run() {

                hologram.teleport(p.getLocation().add(0.0, hauteurAjoute, 0.0));
                if (canDelete) {
                    hologram.delete();
                    EventPlayerChat.playerHologram.remove(p);
                    cancel();
                }
            }
        }.runTaskTimer(Main.javaPlugin, 0L, 0L);
    }

}
