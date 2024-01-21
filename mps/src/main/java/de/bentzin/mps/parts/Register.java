package de.bentzin.mps.parts;

import org.jetbrains.annotations.NotNull;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class Register<T> extends ClockSensitive {

    private final @NotNull String identifier;

    private @NotNull Signal<T> data_in;

    private @NotNull T data;

    private @NotNull Signal<@NotNull T> data_out;

    public Register(@NotNull String identifier, @NotNull BinarySignal clk, @NotNull Signal<T> dataIn, @NotNull T defaultValue) {
        super(clk);
        data_in = dataIn;
        data = defaultValue;
        data_out = new Signal<>(data);

        this.identifier = identifier;
    }

    @Override
    public void onClock(@NotNull BinarySignalEvent event) {
        if (event.equals(BinarySignalEvent.RISING)) {
            data = data_in.get();
            data_out.set(data);
        }
    }

    public @NotNull T getData() {
        return data;
    }

    public void setData(@NotNull T data) {
        this.data = data;
    }

    public @NotNull Signal<T> getData_in() {
        return data_in;
    }

    public void setData_in(@NotNull Signal<T> data_in) {
        this.data_in = data_in;
    }

    public @NotNull Signal<@NotNull T> getData_out() {
        return data_out;
    }

    public void setData_out(@NotNull Signal<@NotNull T> data_out) {
        this.data_out = data_out;
    }

    @Override
    public @NotNull String getIdentifier() {
        return identifier;
    }
}
