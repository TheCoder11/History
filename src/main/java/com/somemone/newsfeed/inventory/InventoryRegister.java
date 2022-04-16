package com.somemone.newsfeed.inventory;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class InventoryRegister {

    private static HashMap<Player, InventoryWatcher> inventoryWatchers = new HashMap<Player, InventoryWatcher>();

    public static void addRegister (InventoryWatcher watcher, Player player) {inventoryWatchers.put(player, watcher);}

    public static void closeRegister (Player player) {inventoryWatchers.remove(player);}

    public static void getCurrentInventory (Player player) {inventoryWatchers.get(player);}

    public static HashMap<Player, InventoryWatcher> getInventoryWatchers (Player player) {return inventoryWatchers;}

}
