package me.popcwrn.unbreakablepatch;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void OnInventoryOpen(InventoryOpenEvent event) {
        for (ItemStack itemStack : event.getPlayer().getInventory().getContents()) {
            if (itemStack != null && itemStack.getItemMeta().isUnbreakable() || itemStack.getDurability() > itemStack.getType().getMaxDurability()|| itemStack.getDurability() < 0) {
                itemStack.setDurability(itemStack.getType().getMaxDurability());
                itemStack.getItemMeta().setUnbreakable(false);
            }
        }
    }
}