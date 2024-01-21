package de.bentzin.mps;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class RAMPair {
    private @NotNull HexChar command;
    private @NotNull HexChar data;

    public RAMPair(@NotNull HexChar command, @NotNull HexChar data) {
        this.command = command;
        this.data = data;
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull RAMPair defaultRAMPair() {
        return new RAMPair(HexChar.x0, HexChar.x0);
    }

    @Contract("_ -> new")
    public static @NotNull RAMPair readFromByte(byte b) {
        char commandChar = (char) ((b & 0xF0) >> 4);
        char dataChar = (char) (b & 0x0F);

        HexChar command = HexChar.INDEX.get(commandChar);
        HexChar data = HexChar.INDEX.get(dataChar);
        if (command == null) {
            throw new IllegalArgumentException("Command char " + commandChar + " is not a valid hex char or is not supported");
        }
        if (data == null) {
            throw new IllegalArgumentException("Data char " + dataChar + " is not a valid hex char");
        }
        return new RAMPair(command, data);
    }

    public @NotNull HexChar getData() {
        return data;
    }

    public void setData(@NotNull HexChar data) {
        this.data = data;
    }

    public @NotNull HexChar getCommand() {
        return command;
    }

    public void setCommand(@NotNull HexChar command) {
        this.command = command;
    }
}
