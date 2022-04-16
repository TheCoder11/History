package com.somemone.newsfeed.object;

import com.somemone.newsfeed.object.Feed;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlaceFeed extends Feed {

    private Location location;

    public PlaceFeed(String title, Player player) {
        super(title, player.getUniqueId());
        location = player.getLocation();
    }

    public Location getLocation() {
        return location;
    }

}
