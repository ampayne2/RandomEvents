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
package ninja.amp.almightynotch.event.world;

import ninja.amp.almightynotch.AlmightyNotchPlugin;
import ninja.amp.almightynotch.Message;
import ninja.amp.almightynotch.Mood;
import ninja.amp.almightynotch.event.LocationEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ExpBottleEvent extends LocationEvent {
    public ExpBottleEvent() {
        super("ExpBottle");
        setMoods(Mood.GENEROUS, Mood.ECSTATIC);
        setProbability(4);
        setDescription("Drops exp bottles at a random location.");
        setOccurMessage(Message.EXP_BOTTLE_EVENT);
        setMoodModifier(-15);
    }

    @Override
    public void trigger(AlmightyNotchPlugin plugin, Location location) {
        // TODO: Drop exp bottles

        plugin.getMessenger().sendMessage(Bukkit.getServer(), getOccurMessage(), String.valueOf(location.getBlockX()), String.valueOf(location.getBlockZ()));
    }
}
