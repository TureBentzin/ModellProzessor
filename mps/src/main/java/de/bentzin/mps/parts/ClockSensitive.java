package de.bentzin.mps.parts;

import org.jetbrains.annotations.NotNull;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public abstract class ClockSensitive implements Part {

    public ClockSensitive(@NotNull BinarySignal clock) {
        clock.listen((booleanSignal, booleanSignalEvent) -> onClock(BinarySignalEvent.from(booleanSignalEvent)));
    }


    public abstract void onClock(@NotNull BinarySignalEvent event);
}
