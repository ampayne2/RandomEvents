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
import ninja.amp.almightynotch.event.WorldEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class FireworksEvent extends WorldEvent {
    public FireworksEvent() {
        super("Fireworks");
        setMoods(Mood.BORED, Mood.SLEEPY);
        setProbability(3);
        setDescription("Shoots fireworks around every player.");
        setOccurMessage(Message.FIREWORKS_EVENT);
        setMoodModifier(-5);
    }

    @Override
    public void trigger(AlmightyNotchPlugin plugin, World world) {
        // TODO: Shoot fireworks

        plugin.getMessenger().sendMessage(Bukkit.getServer(), Message.FIREWORKS_EVENT);
    }
}
