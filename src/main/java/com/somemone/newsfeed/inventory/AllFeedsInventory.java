package com.somemone.newsfeed.inventory;

import com.somemone.newsfeed.object.Feed;
import com.somemone.newsfeed.file.FileHandler;
import com.somemone.newsfeed.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AllFeedsInventory implements InventoryWatcher {

    private Player player;
    private SortType currentSort;
    private List<Feed> feeds;

    public AllFeedsInventory (Player player) {
        this.player = player;
    }

    public void setSort(SortType sort) {
        currentSort = sort;
    }

    public Inventory drawPage(int page) {

        Inventory inv = Bukkit.createInventory(null, 54, "Feeds");
        feeds = new ArrayList<Feed>();

        switch (currentSort) {
            case DEFAULT:
                feeds = sortByNewPost();
                break;
            case TOP_FOLLOWERS:
                feeds = sortByFollowers();
                break;
            case FOLLOWED:
                feeds = sortByFollowed(player);
                break;
        }

        int starter = (page - 1) * 45;
        for (int i = starter; i < feeds.size() || i < starter + 45; i++) {
            inv.setItem(i, new ItemBuilder(Material.BOOKSHELF).setName(ChatColor.GREEN + feeds.get(i).getTitle()).toItemStack());
        }

        if (page > 0) {
            inv.setItem(49, FeedInventory.BACK_BUTTON);
        }
        if (feeds.size() > starter + 45) {
            inv.setItem(51, FeedInventory.FORWARD_BUTTON);
        }

        return inv;

    }

    public List<Feed> sortByFollowers() {
        List<Feed> feeds = new ArrayList<>();
        try {
            feeds = FileHandler.getAllFeeds();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 1; i < feeds.size(); i++) {

            for (int j = i; j >= 0; j--) {
                if (feeds.get(i).getFollowers().size() < feeds.get(j).getFollowers().size()) {
                    Feed feed = feeds.remove(i);
                    feeds.add(j, feed);
                }
            }

        }

        return feeds;

    }

    public List<Feed> sortByNewPost() {
        List<Feed> feeds = new ArrayList<>();
        try {
            feeds = FileHandler.getAllFeeds();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 1; i < feeds.size(); i++) {
            for (int j = i; j >= 0; j--) {

                LocalDateTime readingFeedDate = feeds.get(i).getEntries().get( feeds.get(i).getEntries().size() - 1 ).getTime();
                LocalDateTime selectedFeedDate = feeds.get(j).getEntries().get( feeds.get(j).getEntries().size() - 1 ).getTime();

                if (readingFeedDate.isBefore(selectedFeedDate)) {
                    Feed feed = feeds.remove(i);
                    feeds.add(j, feed);
                }

            }
        }

        return feeds;
    }

    public List<Feed> sortByFollowed(Player player) {
        List<Feed> feeds = sortByNewPost();

        for (int i = 0; i < feeds.size(); i++) {
            if (feeds.get(i).isFollower(player.getUniqueId())) {
                feeds.remove(feeds.get(i));
                i--;
            }
        }

        return feeds;
    }


}
