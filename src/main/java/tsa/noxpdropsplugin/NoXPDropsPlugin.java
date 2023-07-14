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

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("NoXPDropsPlugin wurde aktiviert.");
    }

    @Override
    public void onDisable() {
        getLogger().info("NoXPDropsPlugin wurde deaktiviert.");
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        // Überprüfen, ob es sich um einen Skulkblock oder Skulkvein handelt
        if ((block.getType() == Material.SCULK || block.getType() == Material.SCULK_VEIN)) {
            event.setExpToDrop(0); // Setze die Anzahl der abzuwerfenden Erfahrungspunkte auf 0
           // block.getWorld().spawn(block.getLocation(), ExperienceOrb.class).setExperience(0); // Spawnen eines Erfahrungspunkte-Orbs mit 0 XP
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("skulkinfo")) {
            sender.sendMessage("Ich hab da so ein Plugin geschrieben, LG Kuro"); // :)
        }
        return false;
    }
}