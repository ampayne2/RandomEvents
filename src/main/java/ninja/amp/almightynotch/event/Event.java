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
import ninja.amp.almightynotch.Mood;
import ninja.amp.amplib.messenger.Message;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * The base class for an event.
 */
public abstract class Event<T> {
    private final String name;
    private final EventHandler eventHandler;
    private Set<Mood> moods = new HashSet<>();
    private String description;
    private Message occurMessage;
    private int moodModifier = 0;
    private int probability = 0;
    protected static final Random RANDOM = new Random();

    public Event(String name, EventHandler eventHandler) {
        this.name = name;
        this.eventHandler = eventHandler;
    }

    /**
     * Gets the name of the random event.
     *
     * @return The random event's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the {@link Event.EventHandler} of the random event.
     *
     * @return The random event's {@link Event.EventHandler}.
     */
    public EventHandler getHandler() {
        return eventHandler;
    }

    /**
     * Gets the {@link ninja.amp.almightynotch.Mood}s the event can occur during.
     *
     * @return The {@link ninja.amp.almightynotch.Mood}s of the event.
     */
    public Set<Mood> getMoods() {
        return moods;
    }

    /**
     * Sets the {@link ninja.amp.almightynotch.Mood}s the event can occur during.
     *
     * @param moods The {@link ninja.amp.almightynotch.Mood}s.
     */
    public void setMoods(Mood... moods) {
        this.moods = new HashSet<>(Arrays.asList(moods));
        for (Mood mood : moods) {
            mood.addEvent(this);
        }
    }

    /**
     * Checks if the event can occur during a certain mood.
     *
     * @param mood The {@link ninja.amp.almightynotch.Mood}.
     * @return True if the event has the mood, else false.
     */
    public boolean hasMood(Mood mood) {
        return moods.contains(mood);
    }

    /**
     * Gets the description of the random event.
     *
     * @return The random event's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the random event.
     *
     * @param description The description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the occur message of the event.
     *
     * @return The random event's occur message.
     */
    public Message getOccurMessage() {
        return occurMessage;
    }

    /**
     * Sets the occur message of the event.
     *
     * @param occurMessage The occur message.
     */
    public void setOccurMessage(Message occurMessage) {
        this.occurMessage = occurMessage;
    }

    /**
     * Gets the mood modifier (the change to Almighty Notch's mood when triggered) of the event.
     *
     * @return The event's mood modifier.
     */
    public int getMoodModifier() {
        return moodModifier;
    }

    /**
     * Sets the mood modifier (the change to Almighty Notch's mood when triggered) of the event.
     *
     * @param moodModifier The mood modifier.
     */
    public void setMoodModifier(int moodModifier) {
        this.moodModifier = moodModifier;
    }

    /**
     * Gets the probability of the event occuring.
     *
     * @return The random event's probability.
     */
    public int getProbability() {
        return probability;
    }

    /**
     * Sets the probability of the event.
     *
     * @param probability The probability.
     */
    public void setProbability(int probability) {
        this.probability = probability;
    }

    /**
     * Triggers the Event.
     *
     * @param plugin The {@link ninja.amp.almightynotch.AlmightyNotchPlugin} instance.
     * @param target The target of the Event.
     */
    public abstract void trigger(AlmightyNotchPlugin plugin, T target);

    /**
     * Checks if the Event can occur to the target.
     *
     * @param plugin The {@link ninja.amp.almightynotch.AlmightyNotchPlugin} instance.
     * @param target The target of the Event.
     * @return True if the Event can occur, else false.
     */
    public boolean canOccur(AlmightyNotchPlugin plugin, T target) {
        return true;
    }

    /**
     * Loads event settings from the ConfigurationSection
     *
     * @param section The ConfigurationSection.
     */
    public void load(ConfigurationSection section) {
        probability = section.getInt("Probability", probability);
        moodModifier = section.getInt("MoodModifier", moodModifier);
    }

    /**
     * Saves event settings to a ConfigurationSection.
     *
     * @param section The ConfigurationSection.
     */
    public void save(ConfigurationSection section) {
        section.set("Enabled", true);
        section.set("Probability", probability);
        section.set("MoodModifier", moodModifier);
    }

    /**
     * Removes the event from its moods and the event list.
     */
    public void remove() {
        for (Mood mood : moods) {
            mood.removeEvent(this);
        }
        DefaultEvent.getEventList().remove(this);
    }

    /**
     * Handles attempting to trigger a type of {@link ninja.amp.almightynotch.event.Event}.
     */
    public static abstract class EventHandler<T extends Event> {

        /**
         * Attempts to trigger an {@link ninja.amp.almightynotch.event.Event}.
         *
         * @param plugin The {@link ninja.amp.almightynotch.AlmightyNotchPlugin} instance.
         * @param event  The {@link ninja.amp.almightynotch.event.Event} to trigger.
         * @return True if the {@link ninja.amp.almightynotch.event.Event} was triggered, else false.
         */
        public abstract boolean triggerEvent(AlmightyNotchPlugin plugin, T event);
    }
}
