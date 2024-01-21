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

    public boolean isHigh() {
        return get();
    }

    public boolean isLow() {
        return !get();
    }

    public int toInt() {
        return get() ? 1 : 0;
    }

    public byte toByte() {
        return (byte) toInt();
    }
}
