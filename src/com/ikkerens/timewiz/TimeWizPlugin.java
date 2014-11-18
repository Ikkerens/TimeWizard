package com.ikkerens.timewiz;

import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;
import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.WorldSaveEvent;

@Manifest( name = "TimeWizard", config = Config.class )
public class TimeWizPlugin extends MBServerPlugin implements Listener {
    @Override
    public void onEnable() {
        final Config config = this.getConfig();
        this.saveConfig();

        this.getPluginManager().registerCommand( "settime", new String[] { "time" }, new TimeCommand( config ) );
        this.getPluginManager().registerEventHandler( new TimeHandler( config ) );
        this.getPluginManager().registerEventHandler( this );
    }

    @EventHandler
    public void onSave( final WorldSaveEvent event ) {
        if ( ( event == null ) || ( event.getWorld() == this.getServer().getMainWorld() ) )
            this.onDisable();
    }

    @Override
    public void onDisable() {
        this.saveConfig();
    }
}
