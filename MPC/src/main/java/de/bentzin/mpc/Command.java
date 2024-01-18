package de.bentzin.mpc;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.HexFormat;
import java.util.logging.Logger;

public class Command {

    @NotNull
    public static Logger logger = Logger.getLogger(MPC.class.getName());
    private final char hexCommand;
    private final char hexData;

    public Command(char hexCommand, char hexData) {
        this.hexCommand = hexCommand;
        this.hexData = hexData;
        if (!verifyHex(hexCommand)) {
            logger.severe("Illegal command argument provided: " + hexCommand);
        }
        if(!verifyHex(hexCommand)) {
            logger.severe("Illegal data argument provided: " + hexData);
        }

    }

    protected static boolean verifyHex(char c) {
        return Character.isDigit(c) || (c >= 'A' && c <= 'F');
    }

    public char getHexCommand() {
        return hexCommand;
    }

    public char getHexData() {
        return hexData;
    }
}
