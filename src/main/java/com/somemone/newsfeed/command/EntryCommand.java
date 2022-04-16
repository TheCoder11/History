package com.somemone.newsfeed.command;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.jetbrains.annotations.NotNull;

public class EntryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        ItemStack item = new ItemStack(Material.WRITABLE_BOOK);
        BookMeta meta = (BookMeta) item.getItemMeta();
        meta.addPages(Component.text("Write your Entry Page here. Make sure you have a feed to write to."));
        item.setItemMeta(meta);

        player.openBook( item );

        return false;
    }
}
