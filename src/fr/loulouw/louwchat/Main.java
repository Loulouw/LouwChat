package fr.loulouw.louwchat;


import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import fr.loulouw.louwchat.event.EventPlayerChat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static JavaPlugin javaPlugin;

    @Override
    public void onEnable(){
        Main.javaPlugin = this;

        saveDefaultConfig();
        chargementListener();


        getLogger().info("[*] LouwChat en cours de demarrage...");
        getLogger().info("[*] ====");

        getLogger().info("[*] Chargement des dependances... ");

        if (!Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays")) {
            getLogger().severe("*** HolographicDisplays est nécessaire au fonctionnement de LouwChat ***");
            getLogger().severe("*** Le plugin ne sera pas chargé ***");
            this.setEnabled(false);
            return;
        }

        if (!Bukkit.getPluginManager().isPluginEnabled("Essentials")) {
            getLogger().severe("*** Essentials est nécessaire au fonctionnement de LouwChat ***");
            getLogger().severe("*** Le plugin ne sera pas chargé ***");
            this.setEnabled(false);
            return;
        }

        getLogger().info("[*] dependances chargees");
        getLogger().info("[*] ====");

        getLogger().info("[*] LouwChat est demarre");
    }

    @Override
    public void onDisable(){
        for(Hologram h : HologramsAPI.getHolograms(this)){
            h.delete();
        }
    }

    private void chargementListener(){
        new EventPlayerChat();
    }

}
