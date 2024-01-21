package de.bentzin.mps.parts;

import org.jetbrains.annotations.NotNull;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class Signal<T> {
    private @NotNull T value;

    public Signal(@NotNull T value) {
        this.value = value;
    }

    public @NotNull T get() {
        return value;
    }

    public void set(@NotNull T value) {
        this.value = value;
    }
}
