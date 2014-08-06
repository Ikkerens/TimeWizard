package com.ikkerens.timewiz;

import com.mbserver.api.events.Listener;
import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.WorldTimeEvent;

public class TimeHandler implements Listener {
    private Config config;

    public TimeHandler( final Config config ) {
        this.config = config;
    }

    @EventHandler
    public void onWorldTime( final WorldTimeEvent event ) {
        System.out.println( event.getWorld().getWorldName() + " set time to " + event.getTime() );
        if ( this.config.isWorldLocked( event.getWorld() ) ) {
            event.setCancelled( true );
            System.out.println("Cancelled");
        }
    }
}
