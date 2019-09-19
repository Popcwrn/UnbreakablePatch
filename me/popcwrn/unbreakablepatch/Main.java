package me.popcwrn.unbreakablepatch;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onInventoryOpened(InventoryOpenEvent event) {
        this.revertUnbreakables(event.getInventory());
        this.revertUnbreakables(event.getPlayer().getInventory());
    }

    @EventHandler
    public void onInventoryClosed(InventoryCloseEvent event) {
        this.revertUnbreakables(event.getInventory());
        this.revertUnbreakables(event.getPlayer().getInventory());
    }

    @EventHandler
    public void onItemSpawned(ItemSpawnEvent event) {
        this.revertUnbreakable(event.getEntity().getItemStack());
    }

    private void revertUnbreakables(Inventory inventory) {
        inventory.iterator().forEachRemaining(this::revertUnbreakable);
    }

    private void revertUnbreakable(ItemStack itemStack) {
        if (itemStack != null && itemStack.getType().isItem()) {
            if (itemStack.getDurability() > itemStack.getType().getMaxDurability() || itemStack.getDurability() < 0 || itemStack.getItemMeta().isUnbreakable()) {
                itemStack.setDurability((short) 0);
                itemStack.getItemMeta().setUnbreakable(false);
            }
        }
    }
}