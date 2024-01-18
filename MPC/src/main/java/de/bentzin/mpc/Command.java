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

    public byte @NotNull [] toByteArray() {
        byte[] command = new byte[8];
        byte[] data = hexData.toBytes();
        byte[] operator = hexCommand.toByteArray();
        System.arraycopy(operator, 0, command, 0, operator.length);
        System.arraycopy(data, 0, command, operator.length, data.length);
        return command;
    }
}
