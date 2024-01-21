package de.bentzin.mps.parts;

import org.jetbrains.annotations.NotNull;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public enum BinarySignalEvent implements SignalEvent<Boolean> {
    RISING(false, true),
    FALLING(true, false),
    UPDATE_HIGH(true, true),
    UPDATE_LOW(false, false);

    private final @NotNull Boolean priorValue;
    private final @NotNull Boolean newValue;

    BinarySignalEvent(@NotNull Boolean priorValue, @NotNull Boolean newValue) {
        this.priorValue = priorValue;
        this.newValue = newValue;
    }

    public static @NotNull BinarySignalEvent from(@NotNull Boolean prior, @NotNull Boolean next) {
        if (prior && next) {
            return UPDATE_HIGH;
        } else if (prior) {
            return FALLING;
        } else if (next) {
            return RISING;
        } else {
            return UPDATE_LOW;
        }
    }

    public static @NotNull BinarySignalEvent from(@NotNull SignalEvent<Boolean> event) {
        return from(event.priorValue(), event.newValue());
    }

    @Override
    public @NotNull Boolean priorValue() {
        return priorValue;
    }

    @Override
    public @NotNull Boolean newValue() {
        return newValue;
    }
}
