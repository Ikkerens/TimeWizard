package com.ikkerens.timewiz;

import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.WorldTimeEvent;

public class TimeHandler implements Listener {
    private final Config config;

    public TimeHandler( final Config config ) {
        this.config = config;
    }

    @EventHandler
    public void onWorldTime( final WorldTimeEvent event ) {
        if ( this.config.isWorldLocked( event.getWorld() ) )
            event.setCancelled( true );
    }
}
