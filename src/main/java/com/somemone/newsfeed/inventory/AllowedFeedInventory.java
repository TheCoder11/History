package com.somemone.newsfeed.inventory;

import com.somemone.newsfeed.object.Feed;
import com.somemone.newsfeed.file.FileHandler;
import com.somemone.newsfeed.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllowedFeedInventory implements InventoryWatcher{

    private Player player;
    public AllowedFeedInventory (Player player) {
        this.player = player;
    }

    @Override
    public Inventory drawPage(int page) {
        List<Feed> feeds = new ArrayList<>();

        try {
            feeds = FileHandler.getFeeds(player.getUniqueId(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int starter = (page - 1) * 45;
        Inventory inv = Bukkit.createInventory(null, 54, "Your Feeds");

        for (int i = starter; i < starter + 45 || i < feeds.size(); i++) {
            inv.setItem(i, new ItemBuilder(Material.BOOKSHELF).setName(feeds.get(i).getTitle()).addLoreLine("Followers: " +
                    feeds.get(i).getFollowers().size()).toItemStack());
        }

        if (page > 0) {
            inv.setItem(49, FeedInventory.BACK_BUTTON);
        }
        if (feeds.size() > starter + 45) {
            inv.setItem(51, FeedInventory.FORWARD_BUTTON);
        }

        return inv;

    }
}
