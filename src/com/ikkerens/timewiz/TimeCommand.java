package com.ikkerens.timewiz;

import java.util.HashMap;
import java.util.Map;

import com.mbserver.Minebuilder;
import com.mbserver.game.Player;
import com.mbserver.game.world.World;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;

public class TimeCommand implements CommandExecutor {
    private static final Map< String, Float > PRESETS;

    static {
        PRESETS = new HashMap< String, Float >();
        PRESETS.put( "day", 20f );
        PRESETS.put( "night", 270f );
    }

    private final Config                      config;

    public TimeCommand( final Config config ) {
        this.config = config;
    }

    @Override
    public void execute( final String command, final CommandSender sender, final String[] args, final String label ) {
        if ( !sender.hasPermission( "mbserver.worlds.settime" ) ) {
            sender.sendMessage( "You do not have the required permission to use /" + label );
            return;
        }

        if ( ( args.length == 0 ) || ( args.length > 3 ) ) {
            sender.sendMessage( "Usage: /" + label + " [-l] [world] <time 0-360|day|night>" );
            return;
        }

        int it = -1;

        boolean lock = false;
        // Check if the lock parameter is present
        if ( args[ ++it ].equalsIgnoreCase( "-l" ) )
            lock = true;
        else
            it--;

        try {
            if ( it == args.length )
                throw new IllegalArgumentException();

            World targetWorld = Minebuilder.getServer().getWorld( args[ ++it ] );
            if ( targetWorld == null ) {
                if ( !( sender instanceof Player ) ) {
                    sender.sendMessage( "Needs a world specified or executed as a player." );
                    return;
                }

                targetWorld = ( (Player) sender ).getLocation().getWorld();
                it--;
            }

            if ( it == args.length )
                throw new IllegalArgumentException();

            final String time = args[ ++it ];
            final float newTime;
            if ( PRESETS.containsKey( time ) )
                newTime = PRESETS.get( time );
            else
                newTime = Float.parseFloat( time );

            this.config.setWorldLock( targetWorld, lock );
            targetWorld.setTime( newTime );

            sender.sendMessage( "Time of world '" + targetWorld.getWorldName() + "' set to " + newTime + "." );
        } catch ( final NumberFormatException e ) {
            sender.sendMessage( "Usage: /" + label + " [world] <time 0-360|day|night>" );
        } catch ( final IllegalArgumentException e ) {
            sender.sendMessage( "Usage: /" + label + " [world] <time 0-360|day|night>" );
        }
    }
}
