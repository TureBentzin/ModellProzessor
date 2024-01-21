package de.bentzin.mps.parts;

import org.jetbrains.annotations.NotNull;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class DataRegister implements ClockSensitive {
    @Override
    public void onClock(@NotNull ClockEdge edge) {

    }

    @Override
    public @NotNull String getIdentifier() {
        return "Data Register";
    }
}
