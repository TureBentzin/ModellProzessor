package de.bentzin.mps.parts;

import de.bentzin.mps.HexChar;
import de.bentzin.mps.RAMPair;
import org.jetbrains.annotations.NotNull;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class RAM extends ClockSensitive {
    private @NotNull Signal<HexChar> dataIn;
    private @NotNull Signal<HexChar> addressIn;

    private @NotNull BinarySignal write;

    private @NotNull RAMPair @NotNull [] data;

    private @NotNull Signal<HexChar> dataOut = new Signal<>(HexChar.x0);
    private @NotNull Signal<HexChar> addressOut = new Signal<>(HexChar.x0);

    public RAM(@NotNull BinarySignal clk, @NotNull Signal<HexChar> dataIn, @NotNull Signal<HexChar> addressIn, @NotNull BinarySignal write, @NotNull RAMPair @NotNull [] data) {
        super(clk);
        this.dataIn = dataIn;
        this.addressIn = addressIn;
        this.write = write;
        this.data = data;
    }


    @Override
    void onClock(@NotNull BinarySignalEvent event) {
        if (event.equals(BinarySignalEvent.FALLING)) {
            if (write.get()) {
                access(addressIn.get()).setData(dataIn.get());
            }
            dataOut.set(access(addressIn.get()).getData());
        }
    }

    @Override
    public @NotNull String getIdentifier() {
        return "RAM";
    }

    private @NotNull RAMPair access(@NotNull HexChar address) {
        return data[address.getIntValue()];
    }

    public @NotNull BinarySignal getWrite() {
        return write;
    }

    public RAMPair @NotNull [] getData() {
        return data;
    }

    public @NotNull Signal<HexChar> getAddressIn() {
        return addressIn;
    }

    public @NotNull Signal<HexChar> getAddressOut() {
        return addressOut;
    }

    public @NotNull Signal<HexChar> getDataIn() {
        return dataIn;
    }

    public @NotNull Signal<HexChar> getDataOut() {
        return dataOut;
    }

}

