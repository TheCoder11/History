package com.somemone.newsfeed.command;

import com.somemone.newsfeed.feed.Feed;
import com.somemone.newsfeed.file.FileHandler;
import com.somemone.newsfeed.object.Entry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public class EntryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        ItemStack item = new ItemStack(Material.WRITABLE_BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(Entry.isEntry, PersistentDataType.INTEGER, 0);
        item.setItemMeta(meta);

        player.getInventory().addItem(item);
        player.sendMessage(ChatColor.GOLD + "  Use this book to write your entry. When you want to upload the book to a feed, then sign the book");

        return false;
    }
}
