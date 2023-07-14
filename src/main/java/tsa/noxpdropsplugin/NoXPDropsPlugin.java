package tsa.noxpdropsplugin;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class NoXPDropsPlugin extends JavaPlugin implements Listener {

    private boolean disableXPDrops = false;
    private Permission skulkPermission = new Permission("noxpdrops.use");


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin deaktivieren
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        // Überprüfen, ob es sich um einen Skulkblock oder Skulkvein handelt
        if ((block.getType() == Material.SCULK || block.getType() == Material.SCULK_VEIN) && disableXPDrops) {
            event.setExpToDrop(0); // Setze die Anzahl der abzuwerfenden Erfahrungspunkte auf 0
            block.getWorld().spawn(block.getLocation(), ExperienceOrb.class).setExperience(0); // Spawnen eines Erfahrungspunkte-Orbs mit 0 XP
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("skulk")) {
            if (!sender.hasPermission(skulkPermission)) {
                sender.sendMessage("Du hast keine Berechtigung, diesen Befehl zu verwenden.");
                return true;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("on")) {
                    disableXPDrops = false;
                    sender.sendMessage("Erfahrungspunkt-Drops für Skulkblöcke und Skulkveins sind aktiviert.");
                    return true;
                } else if (args[0].equalsIgnoreCase("off")) {
                    disableXPDrops = true;
                    sender.sendMessage("Erfahrungspunkt-Drops für Skulkblöcke und Skulkveins sind deaktiviert.");
                    return true;
                }
            }
        }
        else if (command.getName().equalsIgnoreCase("skulkinfo")) {
            sender.sendMessage("Ich hab da so ein Plugin geschrieben, LG Kuro"); // :)
        }
        return false;
    }
}