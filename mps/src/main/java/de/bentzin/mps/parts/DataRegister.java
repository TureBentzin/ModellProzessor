package de.bentzin.mps.parts;

import de.bentzin.mps.HexChar;
import org.jetbrains.annotations.NotNull;

import static de.bentzin.mps.HexChar.x0;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class DataRegister extends ClockSensitive {

    private @NotNull Signal<HexChar> data_in;

    private @NotNull HexChar data = x0;

    private @NotNull Signal<HexChar> data_out = new Signal<>(data);

    public DataRegister(@NotNull BinarySignal clk, @NotNull Signal<HexChar> dataIn) {
        super(clk);
        data_in = dataIn;
    }

    @Override
    public void onClock(@NotNull BinarySignalEvent event) {
        if (event.equals(BinarySignalEvent.RISING)) {
            data = data_in.get();
            data_out.set(data);
        }
    }

    public @NotNull HexChar getData() {
        return data;
    }

    public void setData(@NotNull HexChar data) {
        this.data = data;
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

    @Override
    public @NotNull String getIdentifier() {
        return "Data Register";
    }
}
