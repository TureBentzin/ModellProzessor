package de.bentzin.mps.parts;

import de.bentzin.mps.HexChar;
import org.jetbrains.annotations.NotNull;


/*
The documention for the two input signals is missing so while this text is above the code, i assume there is only one signal with the job of
enabling incrementing the counter.
 */

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class ProgramCounter extends ClockSensitive {

    private @NotNull Signal<HexChar> data_in;
    private @NotNull BinarySignal enable_increment;
    private @NotNull BinarySignal load;

    private @NotNull HexChar data = HexChar.x0;

    private @NotNull Signal<HexChar> data_out = new Signal<>(data);

    public ProgramCounter(@NotNull BinarySignal clock, @NotNull BinarySignal enable_increment, @NotNull BinarySignal load, @NotNull Signal<HexChar> data_in) {
        super(clock);
        this.data_in = data_in;
        this.enable_increment = enable_increment;
        this.load = load;
    }

    @Override
    public void onClock(@NotNull BinarySignalEvent event) {
        if (event.equals(BinarySignalEvent.FALLING)) {
            if (load.get()) {
                data = data_in.get();
            }
            if (enable_increment.get()) {
                data = data.increment();
            }
            data_out.set(data);
        }
    }

    public @NotNull Signal<HexChar> getData_in() {
        return data_in;
    }

    public void setData_in(@NotNull Signal<HexChar> data_in) {
        this.data_in = data_in;
    }

    public @NotNull Signal<HexChar> getData_out() {
        return data_out;
    }

    public void setData_out(@NotNull Signal<HexChar> data_out) {
        this.data_out = data_out;
    }

    public @NotNull HexChar getData() {
        return data;
    }

    public @NotNull BinarySignal getEnable_increment() {
        return enable_increment;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "Program Counter";
    }
}
