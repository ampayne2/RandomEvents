/*
 * This file is part of AlmightyNotch.
 *
 * Copyright (c) 2014 <http://dev.bukkit.org/server-mods/almightynotch//>
 *
 * AlmightyNotch is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AlmightyNotch is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with AlmightyNotch.  If not, see <http://www.gnu.org/licenses/>.
 */
package ninja.amp.almightynotch.event;

import ninja.amp.almightynotch.AlmightyNotchPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * An event that can occur to a player.
 */
public abstract class PlayerEvent extends Event<Player> {
    private static final EventHandler handler = new PlayerEventHandler();

    public PlayerEvent(String name) {
        super(name, handler);
    }

    /**
     * Handles attempting to trigger a {@link PlayerEvent}.
     */
    public static class PlayerEventHandler extends EventHandler<PlayerEvent> {
        @Override
        public boolean triggerEvent(AlmightyNotchPlugin plugin, PlayerEvent event) {
            List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
            while (!players.isEmpty()) {
                Player player = players.get(RANDOM.nextInt(players.size()));
                if (event.canOccur(plugin, player)) {
                    event.trigger(plugin, player);
                    plugin.getMessenger().debug("Triggered PlayerEvent " + event.getName() + " for player " + player.getName());
                    if (event.getMoodModifier() != 0) {
                        plugin.getNotch().modifyMoodLevel(event.getMoodModifier());
                    }
                    return true;
                } else {
                    players.remove(player);
                    plugin.getMessenger().debug("Couldn't trigger PlayerEvent " + event.getName() + " for player " + player.getName());
                }
            }
            return false;
        }
    }
}
