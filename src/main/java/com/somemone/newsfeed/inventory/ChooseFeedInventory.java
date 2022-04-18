package com.somemone.newsfeed.inventory;

import com.somemone.newsfeed.NewsFeed;
import com.somemone.newsfeed.object.Entry;
import com.somemone.newsfeed.object.Feed;
import com.somemone.newsfeed.file.FileHandler;
import com.somemone.newsfeed.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ChooseFeedInventory implements InventoryWatcher{

    private Player player;
    private Entry entry;
    private int currentPage;

    public ChooseFeedInventory(Player player, Entry entry) {
        this.player = player;
        currentPage = 1;
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
                    feeds.get(i).getFollowers().size()).addContainerTag(Feed.feedKey, PersistentDataType.STRING, feeds.get(i).getId()).toItemStack());
        }

        if (page > 0) {
            inv.setItem(49, FeedInventory.BACK_BUTTON);
        }
        if (feeds.size() > starter + 45) {
            inv.setItem(51, FeedInventory.FORWARD_BUTTON);
        }

        return inv;

    }

    @Override
    public void handleClick(InventoryClickEvent event) {
        if (event.getCurrentItem().equals(FeedInventory.BACK_BUTTON)) {
            currentPage--;
            player.openInventory(drawPage(currentPage));
        }
        else if (event.getCurrentItem().equals(FeedInventory.FORWARD_BUTTON)) {
            currentPage++;
            player.openInventory(drawPage(currentPage));
        }
        else if (event.getCurrentItem().getType().equals(Material.BOOKSHELF)) {

            UUID uuid = UUID.fromString(Objects.requireNonNull(event.getCurrentItem().getItemMeta().getPersistentDataContainer()
                    .get(Feed.feedKey, PersistentDataType.STRING)));

            Feed feed = new Feed("ERROR", UUID.randomUUID(), UUID.randomUUID());
            try {
                feed = FileHandler.getFeed(uuid);
            } catch (IOException e) {
                e.printStackTrace();
            }

            feed.addEntry(entry, player.getUniqueId());
            player.closeInventory();
            InventoryRegister.closeRegister(player);

        }
        event.setCancelled(true);
    }
}
