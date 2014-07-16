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
package me.ampayne2.almightynotch.event;

import me.ampayne2.almightynotch.EventList;
import me.ampayne2.almightynotch.event.mob.CreeperExplodeEvent;
import me.ampayne2.almightynotch.event.mob.DinnerboneEvent;
import me.ampayne2.almightynotch.event.mob.FrightenSheepEvent;
import me.ampayne2.almightynotch.event.mob.MobHordeEvent;
import me.ampayne2.almightynotch.event.player.AnvilFallEvent;
import me.ampayne2.almightynotch.event.player.FoodCookEvent;
import me.ampayne2.almightynotch.event.player.OreSmeltEvent;
import me.ampayne2.almightynotch.event.player.PvPEvent;
import me.ampayne2.almightynotch.event.player.SoundEvent;
import me.ampayne2.almightynotch.event.weather.GeomagneticReversalEvent;
import me.ampayne2.almightynotch.event.weather.SolarFlareEvent;
import me.ampayne2.almightynotch.event.world.ExpBottleEvent;
import me.ampayne2.almightynotch.event.world.FireworksEvent;

/**
 * Default {@link me.ampayne2.almightynotch.event.Event}s that come with the AlmightyNotch plugin.
 */
public enum DefaultEvent {
    // Location Events
    EXP_BOTTLE(new ExpBottleEvent()),
    FIREWORKS(new FireworksEvent()),
    MOB_HORDE(new MobHordeEvent()),

    // Player Events
    ANVIL_FALL(new AnvilFallEvent()),
    FOOD_COOK(new FoodCookEvent()),
    ORE_SMELT(new OreSmeltEvent()),

    // World Events
    CREEPER_EXPLODE(new CreeperExplodeEvent()),
    DINNERBONE(new DinnerboneEvent()),
    FRIGHTEN_SHEEP(new FrightenSheepEvent()),
    GEOMAGNETIC_REVERSAL(new GeomagneticReversalEvent()),
    SOLAR_FLARE(new SolarFlareEvent()),
    SOUND(new SoundEvent()),

    // Minigame Events
    PVP(new PvPEvent());

    private final Event event;

    private DefaultEvent(Event event) {
        this.event = event;
    }

    /**
     * Gets the {@link me.ampayne2.almightynotch.event.Event} instance of the DefaultEvent.
     *
     * @return The {@link me.ampayne2.almightynotch.event.Event} instance.
     */
    public Event getEvent() {
        return event;
    }

    /**
     * A list of all {@link me.ampayne2.almightynotch.event.Event}s.
     */
    private static final EventList eventList = new EventList();

    /**
     * Adds a custom {@link me.ampayne2.almightynotch.event.Event} to the {@link me.ampayne2.almightynotch.EventList}.
     *
     * @param event The {@link me.ampayne2.almightynotch.event.Event} to add.
     */
    public static void addCustomEvent(Event event) {
        eventList.add(event);
    }

    /**
     * Gets an {@link me.ampayne2.almightynotch.event.Event} by its name.
     *
     * @param name The name of the {@link me.ampayne2.almightynotch.event.Event}.
     * @return The {@link me.ampayne2.almightynotch.event.Event}.
     */
    public static Event byName(String name) {
        for (Event event : eventList) {
            if (event.getName().equalsIgnoreCase(name)) {
                return event;
            }
        }
        return null;
    }

    /**
     * Gets the {@link me.ampayne2.almightynotch.EventList} of all {@link me.ampayne2.almightynotch.event.Event}s.
     *
     * @return The {@link me.ampayne2.almightynotch.EventList} of all {@link me.ampayne2.almightynotch.event.Event}s.
     */
    public static EventList getEventList() {
        return eventList;
    }

    static {
        for (DefaultEvent defaultEvent : DefaultEvent.class.getEnumConstants()) {
            eventList.add(defaultEvent.getEvent());
        }
    }
}
