package com.somemone.newsfeed;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class NewsFeed extends JavaPlugin {

    public static File dataFolder;

    @Override
    public void onEnable() {
        dataFolder = this.getDataFolder();



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
