package com.somemone.newsfeed.command;

import com.somemone.newsfeed.file.FileHandler;
import com.somemone.newsfeed.object.Feed;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.List;

public class FeedCommand implements CommandExecutor, TabCompleter {

    // TODO: /feed <name> add <player> -- Add new player to the feed
    // TODO: /feed <name> remove <player> -- Remove player from

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if (args.length == 0) return false;

        if (args[0].equals("create")) {
            if (args.length < 2) return false;

            String title = "";
            for (int i = 1; i < args.length; i++)
                title += args[i] + " ";

            Feed feed = new Feed(title, player.getUniqueId());

            try {
                FileHandler.saveFeed(feed);
            } catch (IOException e) {
                e.printStackTrace();
            }

            sender.sendMessage(ChatColor.GREEN + "Created feed " + title);
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return null;
    }
}
