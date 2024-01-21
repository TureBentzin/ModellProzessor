package de.bentzin.mps.parts;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public interface SignalEvent<T> {

    @Contract(value = "_, _ -> new", pure = true)
    static <T> @NotNull SignalEvent<T> defaultEvent(@NotNull T prior, @NotNull T next) {
        return new SignalEvent<>() {
            @Override
            public @NotNull T priorValue() {
                return prior;
            }

            @Override
            public @NotNull T newValue() {
                return next;
            }
        };
    }

    @NotNull T priorValue();

    @NotNull T newValue();
}
