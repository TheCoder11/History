package com.somemone.newsfeed.inventory;

import com.somemone.newsfeed.object.Entry;
import com.somemone.newsfeed.object.Feed;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class FeedInventory implements InventoryWatcher {

    public static final ItemStack BACK_BUTTON = new ItemStack(Material.RED_STAINED_GLASS_PANE);
    public static final ItemStack FORWARD_BUTTON = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);

    public static final ItemStack REMOVE_FOLLOWER = new ItemStack(Material.REDSTONE_BLOCK);
    public static final ItemStack ADD_FOLLOWER = new ItemStack(Material.EMERALD_BLOCK);

    private Feed feed;
    private Player player;

    public FeedInventory (Feed feed, Player player) {
        this.feed = feed;
        this.player = player;
    }

    public Inventory drawPage (int page) {

        Inventory inventory = Bukkit.createInventory(null, 54, feed.getTitle());
        int starter = (page - 1) * 45;
        for (int i = starter; i < feed.getEntries().size() || i < starter + 45; i++) {
            inventory.setItem(i, feed.getEntries().get(i).getBookForm());
        }

        if (page > 0) {
            inventory.setItem(49, FeedInventory.BACK_BUTTON);
        }
        if (feed.getEntries().size() > starter + 45) {
            inventory.setItem(51, FeedInventory.FORWARD_BUTTON);
        }

        if (feed.isFollower(player.getUniqueId())) {
            inventory.setItem(53, REMOVE_FOLLOWER);
        } else {
            inventory.setItem(53, ADD_FOLLOWER);
        }

        return inventory;
    }

    public List<Entry> sortByNew () {
        List<Entry> entries = feed.getEntries();


    }

    public List<Entry> sortByDownloads() {

    }

    // 46, 47, 48, 49, 50, 51, 52, 53, 54

}
