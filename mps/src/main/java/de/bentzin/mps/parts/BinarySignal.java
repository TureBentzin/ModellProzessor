package de.bentzin.mps.parts;

import org.jetbrains.annotations.NotNull;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class BinarySignal extends Signal<Boolean> {
    public BinarySignal(@NotNull Boolean value) {
        super(value);
    }
}
