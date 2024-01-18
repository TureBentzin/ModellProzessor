package de.bentzin.mpc;

import java.awt.*;
import java.util.HexFormat;

public class Command {
    private final char hexCommand;
    private final char hexData;

    public Command(char hexCommand, char hexData) {
        this.hexCommand = hexCommand;
        this.hexData = hexData;

    }

    protected static boolean verifyHex(char c) {
        
    }
    public char getHexCommand() {
        return hexCommand;
    }

    public char getHexData() {
        return hexData;
    }
}
