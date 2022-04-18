package com.somemone.newsfeed.command;

import com.somemone.newsfeed.inventory.AllFeedsInventory;
import com.somemone.newsfeed.inventory.SortTypesInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FeedsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        AllFeedsInventory inv = new AllFeedsInventory(player);
        player.openInventory(inv.drawPage(1));

        return true;
    }
}
