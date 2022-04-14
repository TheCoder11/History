package com.somemone.newsfeed.feed;

import com.somemone.newsfeed.event.RemoveFeedEvent;
import com.somemone.newsfeed.event.UpdateFeedEvent;
import com.somemone.newsfeed.object.Entry;
import org.bukkit.Bukkit;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Feed {

    private String title;
    private List<Entry> entries;
    private List<UUID> allowedAuthors;
    private UUID owner;

    private UUID id;

    public Feed (String title, UUID author) {
        this.title = title;
        this.entries = new ArrayList<>();
        this.allowedAuthors = new ArrayList<>();
        allowedAuthors.add(author);
        owner = author;

        id = UUID.randomUUID();
    }

    public Feed (String title, UUID author, UUID id) {
        this.title = title;
        this.entries = new ArrayList<>();
        this.allowedAuthors = new ArrayList<>();
        allowedAuthors.add(author);
        owner = author;

        this.id = id;
    }

    public boolean addEntry (Entry entry, UUID sender) {
        if (allowedAuthors.contains(sender)) {
            UpdateFeedEvent e = new UpdateFeedEvent(entry, sender);
            Bukkit.getPluginManager().callEvent( e );
            if (e.isCancelled()) {
                entries.add(entry);
                return true;
            }
        }
        return false;
    }

    public void removeEntry (Entry entry, String reason) {
        RemoveFeedEvent e = new RemoveFeedEvent(entry, reason);
        Bukkit.getPluginManager().callEvent( e );
        if (e.isCancelled())
            entries.remove(entry);
    }

    public UUID getId() {return id;}

    public UUID getOwner() {return owner;}

    public void setOwner (UUID owner) {this.owner = owner;}

    public List<Entry> getEntries() {
        return entries;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addAuthor (UUID author) {
        allowedAuthors.add(author);
    }

    public void removeAuthor (UUID author) {
        allowedAuthors.remove(author);
    }

    public List<UUID> getAuthors () {
        return allowedAuthors;
    }

}
