package com.somemone.newsfeed.inventory;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public interface InventoryWatcher {

    public Inventory drawPage(int page);

    public void handleClick (InventoryClickEvent event);

}
