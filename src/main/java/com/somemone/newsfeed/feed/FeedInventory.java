package com.somemone.newsfeed.feed;

import com.somemone.newsfeed.object.Entry;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class FeedInventory {

    public static final ItemStack BACK_BUTTON = new ItemStack(Material.RED_STAINED_GLASS_PANE);
    public static final ItemStack FORWARD_BUTTON = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);

    private Feed feed;

    public FeedInventory (Feed feed) {
        this.feed = feed;
    }

    public Inventory drawPage (int page) {

        Inventory inventory = Bukkit.createInventory(null, 54, feed.getTitle());
        int starter = (page - 1) * 45;
        for (int i = starter; i < feed.getEntries().size() || i < starter + 45; i++) {
            inventory.setItem(i, feed.getEntries().get(i).getBookForm());
        }
        inventory.setItem(49, BACK_BUTTON);
        inventory.setItem(50, FORWARD_BUTTON);

        return inventory;
    }

    // 46, 47, 48, 49, 50, 51, 52, 53, 54

}
