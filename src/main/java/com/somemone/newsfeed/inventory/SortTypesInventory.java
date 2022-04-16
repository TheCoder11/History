package com.somemone.newsfeed.inventory;

import com.somemone.newsfeed.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class SortTypesInventory implements InventoryWatcher {
    @Override
    public Inventory drawPage(int page) {
        Inventory inv = Bukkit.createInventory(null, 9, "Select Filter");
        inv.addItem(new ItemBuilder(Material.BOOKSHELF).setName(ChatColor.GREEN + "Default Sort").toItemStack());
        inv.addItem(new ItemBuilder(Material.BOOKSHELF).setName(ChatColor.GREEN + "Most Followed").toItemStack());
        inv.addItem(new ItemBuilder(Material.BOOKSHELF).setName(ChatColor.GREEN + "Your Follows").toItemStack());
        return inv;
    }
}
