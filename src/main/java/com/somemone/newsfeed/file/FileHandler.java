package com.somemone.newsfeed.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.somemone.newsfeed.NewsFeed;
import com.somemone.newsfeed.object.Feed;

import javax.annotation.Nullable;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileHandler {

    @Nullable
    public static Feed getFeed (UUID id) throws IOException {

        File file = new File(NewsFeed.dataFolder, "/feeds/" + id.toString() + ".json");

        if (!file.exists()) return null;

        String content = "";
        for (String line : Files.readAllLines(file.getAbsoluteFile().toPath()))
            content += line + "\n";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Feed feed = gson.fromJson(content, (Type) Feed.class);

        return feed;

    }


    public static List<Feed> getFeeds(UUID player, boolean owner) throws IOException {
        File directory = new File(NewsFeed.dataFolder, "/feeds");
        File[] feedFiles = directory.listFiles();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        List<Feed> feeds = new ArrayList<>();

        for (File file : feedFiles) {
            String content = "";
            for (String line : Files.readAllLines(file.getAbsoluteFile().toPath()))
                content += line + "\n";

            Feed feed = gson.fromJson(content, (Type) Feed.class);

            if (owner) {
                if (feed.getOwner().equals(player)) feeds.add(feed);
            } else {
                if (feed.getAuthors().contains(player)) feeds.add(feed);
            }

        }

        return feeds;
    }

    public static void saveFeed (Feed feed) throws IOException {
        File file = new File(NewsFeed.dataFolder, "/feeds/" + feed.getId() + ".json");

        if (!file.exists()) file.createNewFile();

        FileWriter writer = new FileWriter(file);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String content = gson.toJson(feed);

        writer.write(content);
        writer.close();

    }

    public static List<Feed> getAllFeeds() throws IOException {
        File directory = new File(NewsFeed.dataFolder, "/feeds");
        File[] feedFiles = directory.listFiles();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        List<Feed> feeds = new ArrayList<Feed>();
        for (File file : feedFiles) {
            String content = "";
            for (String line : Files.readAllLines(file.getParentFile().toPath()))
                content += line + "\n";

            feeds.add(gson.fromJson(content, (Type) Feed.class));
        }
        return feeds;
    }

}
