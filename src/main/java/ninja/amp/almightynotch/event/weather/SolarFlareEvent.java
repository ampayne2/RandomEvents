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
package ninja.amp.almightynotch.event.weather;

import ninja.amp.almightynotch.AlmightyNotchPlugin;
import ninja.amp.almightynotch.Message;
import ninja.amp.almightynotch.Mood;
import ninja.amp.almightynotch.event.WorldEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;

public class SolarFlareEvent extends WorldEvent {
    private int fireTicks = 5;

    public SolarFlareEvent() {
        super("SolarFlare");
        setMoods(Mood.DISPLEASED);
        setProbability(3);
        setDescription("Sets all mobs in the world on fire.");
        setOccurMessage(Message.SOLAR_FLARE_EVENT);
        setMoodModifier(20);
    }

    @Override
    public void load(ConfigurationSection section) {
        super.load(section);
        fireTicks = section.getInt("FireTicks", 100);
    }

    @Override
    public void trigger(AlmightyNotchPlugin plugin, World world) {
        for (LivingEntity entity : world.getLivingEntities()) {
            entity.setFireTicks(fireTicks);
        }
        plugin.getMessenger().sendMessage(Bukkit.getServer(), getOccurMessage());
    }

    @Override
    public boolean canOccur(AlmightyNotchPlugin plugin, World world) {
        long time = world.getTime();
        return time < 12300 || time > 23850 && !world.hasStorm();
    }
}
