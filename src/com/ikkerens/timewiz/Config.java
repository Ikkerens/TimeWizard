package com.ikkerens.timewiz;

import java.util.HashMap;
import java.util.Map;

import com.mbserver.api.game.World;

public class Config {
    private final Map< String, Boolean > world_time_lock;

    public Config() {
        this.world_time_lock = new HashMap< String, Boolean >();
    }

    public void setWorldLock( final World world, final boolean lock ) {
        this.world_time_lock.put( world.getWorldName(), lock );
    }

    public boolean isWorldLocked( final World world ) {
        final Boolean lock = this.world_time_lock.get( world.getWorldName() );
        return lock == null ? false : lock;
    }
}
