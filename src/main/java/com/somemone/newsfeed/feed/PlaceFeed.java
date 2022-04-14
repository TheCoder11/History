package com.somemone.newsfeed.feed;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlaceFeed extends Feed{

    private Location location;

    public PlaceFeed(String title, Player player) {
        super(title, player.getUniqueId());
        location = player.getLocation();
    }

    public Location getLocation() {
        return location;
    }

}
