package de.bentzin.mps.parts;

import org.jetbrains.annotations.NotNull;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public interface ClockSensitive extends Part {

    void onClock(@NotNull ClockEdge edge);

    enum ClockEdge {
        RISING,
        FALLING
    }
}
