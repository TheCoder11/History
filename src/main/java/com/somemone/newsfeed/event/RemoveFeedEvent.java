package com.somemone.newsfeed.event;

import com.somemone.newsfeed.object.Entry;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RemoveFeedEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private Entry entry;
    private String reason;
    private boolean isCancelled;

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public RemoveFeedEvent(Entry entry, String reason) {
        this.entry = entry;
        this.reason = reason;
        isCancelled = true;
    }

    public Entry getEntry() {
        return entry;
    }

    public String getReason() {
        return reason;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancel) {
        isCancelled = cancel;
    }


}
