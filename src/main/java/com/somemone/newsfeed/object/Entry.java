package com.somemone.newsfeed.object;

import com.somemone.newsfeed.NewsFeed;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.time.LocalDateTime;
import java.util.UUID;

public class Entry {

    public static NamespacedKey isEntry = new NamespacedKey(NewsFeed.getPlugin(NewsFeed.class), "is-entry-book");

    private String title;
    private String content;
    private final UUID uuid;
    private final LocalDateTime time;
    private int downloads;

    public Entry (String title, String content) {
        this.title = title;
        this.content = content;
        this.uuid = UUID.randomUUID();
        this.time = LocalDateTime.now();
        downloads = 0;
    }

    public Entry (String content) {
        this.title = "";
        this.content = content;
        this.uuid = UUID.randomUUID();
        this.time = LocalDateTime.now();
        downloads = 0;
    }

    public ItemStack getBookForm () {
        ItemStack book = new ItemStack(Material.WRITABLE_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();

        for (int i = 0; i * 256 < content.length(); i ++) {
            String cut = "";
            if ((i + 1) * 256 >= content.length()) {
                cut = content.substring(i * 256);
            } else {
                cut = content.substring(i * 256, (i + 1) * 256);
            }
            meta.addPages(Component.text(cut));
        }
        meta.setTitle(title);
        meta.setAuthor("Server");

        book.setItemMeta(meta);

        return book;
    }

    public String getTitle() { return title; }

    public String getContent() {
        return content;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public void addDownload () {downloads++;}

    public int getDownloads () {return downloads;}
}
