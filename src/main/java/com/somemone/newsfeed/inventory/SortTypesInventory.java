package com.somemone.newsfeed.inventory;

import com.somemone.newsfeed.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class SortTypesInventory implements InventoryWatcher {

    private AllFeedsInventory inv;
    private Player player;

    public SortTypesInventory (AllFeedsInventory inv, Player player) {
        this.inv = inv;
        this.player = player;
    }

    @Override
    public Inventory drawPage(int page) {
        Inventory inv = Bukkit.createInventory(null, 9, "Select Filter");
        inv.addItem(new ItemBuilder(Material.BOOKSHELF).setName(ChatColor.GREEN + "Default Sort").toItemStack());
        inv.addItem(new ItemBuilder(Material.BOOKSHELF).setName(ChatColor.GREEN + "Most Followed").toItemStack());
        inv.addItem(new ItemBuilder(Material.BOOKSHELF).setName(ChatColor.GREEN + "Your Follows").toItemStack());
        return inv;
    }

    @Override
    public void handleClick(InventoryClickEvent event) {
        String name = event.getCurrentItem().getItemMeta().getDisplayName();
        if (name.equals(ChatColor.GREEN + "Default Sort"))
            inv.setSort(SortType.DEFAULT);
        else if (name.equals(ChatColor.GREEN + "Most Followed"))
            inv.setSort(SortType.TOP_FOLLOWERS);
        else if (name.equals(ChatColor.GREEN + "Your Follows"))
            inv.setSort(SortType.FOLLOWED);

        if (event.getCurrentItem().getType().equals(Material.BOOKSHELF)) {

            InventoryRegister.putRegister(inv, player);
            player.openInventory(inv.drawPage(1));

        }
        event.setCancelled(true);
    }
}
