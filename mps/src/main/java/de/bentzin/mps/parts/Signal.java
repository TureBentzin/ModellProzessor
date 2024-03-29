package de.bentzin.mps.parts;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class Signal<T> {
    private final @NotNull List<BiConsumer<Signal<T>, SignalEvent<T>>> updateListeners = new ArrayList<>();
    private @NotNull T value;

    public Signal(@NotNull T value) {
        this.value = value;
    }

    public Signal() {

    }

    public void listen(@NotNull BiConsumer<Signal<T>, SignalEvent<T>> listener) {
        updateListeners.add(listener);
    }

    public void listen(@NotNull Runnable listener) {
        updateListeners.add((signal, event) -> listener.run());
    }

    public @NotNull T get() {
        return value;
    }

    protected @NotNull SignalEvent<T> event(@NotNull T prior, @NotNull T next) {
        return SignalEvent.defaultEvent(prior, next);
    }

    public void set(@NotNull T value) {
        this.value = value;
        SignalEvent<T> event = event(this.value, value);
        updateListeners.forEach(signalSignalEventBiConsumer -> signalSignalEventBiConsumer.accept(this, event));
    }
}
