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

    private @NotNull Signal<HexChar> commandOut = new Signal<>(HexChar.x0);
    private @NotNull Signal<HexChar> dataOut = new Signal<>(HexChar.x0);

    public RAM(@NotNull BinarySignal clk, @NotNull Signal<HexChar> dataIn, @NotNull Signal<HexChar> addressIn, @NotNull BinarySignal write, @NotNull RAMPair @NotNull [] data) {
        super(clk);
        this.dataIn = dataIn;
        this.addressIn = addressIn;
        this.write = write;
        this.data = data;
    }

    public RAM(@NotNull BinarySignal clk) {
        super(clk);
    }


    @Override
    public void onClock(@NotNull BinarySignalEvent event) {
        if (event.equals(BinarySignalEvent.FALLING)) {
            if (write.get()) {
                access(addressIn.get()).setData(dataIn.get());
            }
            commandOut.set(access(addressIn.get()).getData());
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

    public @NotNull Signal<HexChar> getDataOut() {
        return dataOut;
    }

    public @NotNull Signal<HexChar> getDataIn() {
        return dataIn;
    }

    public void setDataIn(@NotNull Signal<HexChar> dataIn) {
        this.dataIn = dataIn;
    }

    public void setAddressIn(@NotNull Signal<HexChar> addressIn) {
        this.addressIn = addressIn;
    }

    public void setWrite(@NotNull BinarySignal write) {
        this.write = write;
    }

    public void setData(RAMPair @NotNull [] data) {
        this.data = data;
    }

    public void setCommandOut(@NotNull Signal<HexChar> commandOut) {
        this.commandOut = commandOut;
    }

    public void setDataOut(@NotNull Signal<HexChar> dataOut) {
        this.dataOut = dataOut;
    }

    public @NotNull Signal<HexChar> getCommandOut() {
        return commandOut;
    }

}

