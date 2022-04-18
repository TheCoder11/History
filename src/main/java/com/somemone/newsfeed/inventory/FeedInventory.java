package com.somemone.newsfeed.inventory;

import com.somemone.newsfeed.NewsFeed;
import com.somemone.newsfeed.object.Entry;
import com.somemone.newsfeed.object.Feed;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FeedInventory implements InventoryWatcher {

    public static final ItemStack BACK_TO_FEEDS = new ItemStack(Material.ARROW);

    public static final ItemStack BACK_BUTTON = new ItemStack(Material.RED_STAINED_GLASS_PANE);
    public static final ItemStack FORWARD_BUTTON = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);

    public static final ItemStack REMOVE_FOLLOWER = new ItemStack(Material.REDSTONE_BLOCK);
    public static final ItemStack ADD_FOLLOWER = new ItemStack(Material.EMERALD_BLOCK);

    public static final ItemStack SORT_BY_NEW = new ItemStack(Material.CLOCK);
    public static final ItemStack SORT_BY_DOWNLOADS = new ItemStack(Material.PAINTING);

    private String sortType; // either "new" or "downloads"
    private Feed feed;
    private Player player;
    private int currentPage;

    public FeedInventory (Feed feed, Player player) {
        this.feed = feed;
        this.player = player;
        currentPage = 1;
    }

    public Inventory drawPage (int page) {

        Inventory inventory = Bukkit.createInventory(null, 54, feed.getTitle());
        int starter = (page - 1) * 45;
        List<Entry> entries = feed.getEntries();

        if (sortType.equals("downloads")) {
            entries = sortByDownloads();
        }

        for (int i = starter; i < entries.size() || i < starter + 45; i++) {
            inventory.setItem(i, entries.get(i).getBookForm());
        }

        inventory.setItem(45, BACK_TO_FEEDS);

        if (page > 0) {
            inventory.setItem(49, FeedInventory.BACK_BUTTON);
        }
        if (feed.getEntries().size() > starter + 45) {
            inventory.setItem(51, FeedInventory.FORWARD_BUTTON);
        }

        if (feed.isFollower(player.getUniqueId())) {
            inventory.setItem(47, REMOVE_FOLLOWER);
        } else {
            inventory.setItem(47, ADD_FOLLOWER);
        }

        if (sortType.equals("new")) {
            inventory.setItem(53, SORT_BY_DOWNLOADS);
        } else if (sortType.equals("downloads")) {
            inventory.setItem(53, SORT_BY_NEW);
        }

        return inventory;
    }

    @Override
    public void handleClick(InventoryClickEvent event) {
        if (event.getCurrentItem().equals(BACK_BUTTON)) {
            currentPage--;
        }
        else if (event.getCurrentItem().equals(FORWARD_BUTTON)) {
            currentPage++;
        }
        else if (event.getCurrentItem().equals(ADD_FOLLOWER)) {
            feed.addFollower(player.getUniqueId());
        }
        else if (event.getCurrentItem().equals(REMOVE_FOLLOWER)) {
            feed.removeFollower(player.getUniqueId());
        }
        else if (event.getCurrentItem().equals(SORT_BY_DOWNLOADS)) {
            sortType = "downloads";
        }
        else if (event.getCurrentItem().equals(SORT_BY_NEW)) {
            sortType = "new";
        }
        else if (event.getCurrentItem().equals(BACK_TO_FEEDS)) {
            AllFeedsInventory inv = new AllFeedsInventory(player);
            InventoryRegister.putRegister(inv, player);
            player.openInventory(inv.drawPage(1));
            return;
        }
        else if (event.getCurrentItem().getType().equals(Material.WRITTEN_BOOK)) {
            for (Entry entry : feed.getEntries()) {
                if (entry.getBookForm().equals(event.getCurrentItem())) {

                    entry.addDownload();
                    player.getInventory().addItem(event.getCurrentItem());
                    break;

                }
            }

            player.closeInventory();
            InventoryRegister.closeRegister(player);
            return;
        } else {
            return;
        }

        player.openInventory(drawPage(currentPage));
        event.setCancelled(true);
    }

    /*
    public List<Entry> sortByNew () {
        List<Entry> entries = feed.getEntries();

        Collections.sort(entries, new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return o1.getTime().compareTo(o2.getTime());
            }
        });

        return entries;
    }
     */

    public List<Entry> sortByDownloads() {

        List<Entry> entries = feed.getEntries();

        Collections.sort(entries, new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return new Integer(o1.getDownloads()).compareTo(o2.getDownloads());
            }
        });

        return entries;

    }

    // 46, 47, 48, 49, 50, 51, 52, 53, 54

}
