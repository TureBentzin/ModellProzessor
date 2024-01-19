package de.bentzin.mpc;

import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class Command {

    @NotNull
    public static Logger logger = Logger.getLogger(MPC.class.getName());
    private final @NotNull Operator hexCommand;
    private final @NotNull HexChar hexData;

    public Command(@NotNull Operator hexCommand, @NotNull HexChar hexData) {
        this.hexCommand = hexCommand;
        this.hexData = hexData;

    }

    public @NotNull Operator getHexCommand() {
        return hexCommand;
    }

    public @NotNull HexChar getHexData() {
        return hexData;
    }

    public byte toByte() {
        byte command;
        byte data = hexData.toByte();
        byte operator = hexCommand.toByte();
        command = (byte) (operator << 4 | data);
        return command;
    }
}
