package com.somemone.newsfeed.event;

import com.somemone.newsfeed.object.Entry;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class UpdateFeedEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private Entry entry;
    private UUID sender;
    private boolean isCancelled;

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public UpdateFeedEvent (Entry entry, UUID sender) {
        this.entry = entry;
        this.sender = sender;
        isCancelled = false;
    }

    public Entry getEntry() {
        return entry;
    }

    public UUID getSender() {
        return sender;
    }

    public boolean isCancelled() {
        return this.isCancelled;
    }

    public void setCancelled(boolean cancel) {
        isCancelled = cancel;
    }
}
