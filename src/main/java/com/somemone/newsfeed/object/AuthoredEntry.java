package com.somemone.newsfeed.object;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.UUID;

public class AuthoredEntry extends Entry {

    private final UUID author;

    public AuthoredEntry(String title, String content, UUID author) {
        super(title, content);
        this.author = author;
    }

    public AuthoredEntry(String content, UUID author) {
        super(content);
        this.author = author;
    }

    public ItemStack getBookForm() {
        ItemStack book = super.getBookForm();
        BookMeta meta = (BookMeta) book.getItemMeta();

        meta.setAuthor(Bukkit.getOfflinePlayer(author).getName());
        book.setItemMeta(meta);
        return book;
    }

    public UUID getAuthor() { return author; }

}
