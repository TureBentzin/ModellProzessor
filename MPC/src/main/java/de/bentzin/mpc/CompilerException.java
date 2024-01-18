package de.bentzin.mpc;

import org.jetbrains.annotations.NotNull;

public class CompilerException extends Exception {


    public static CompilerException unexpectedCommand(int line, String command) {
        return new CompilerException("Unexpected command \"" + command + "\"", line);
    }

    public static CompilerException unknownCommand(int line, String command) {
        return new CompilerException("Unknown command \"" + command + "\"", line);
    }

    public static CompilerException unknownData(int line, String data) {
        return new CompilerException("Unknown data \"" + data + "\"", line);
    }

    private final int line;

    public CompilerException(@NotNull String message, int line) {
        super(message);
        this.line = line;
    }

    @Override
    public @NotNull String getMessage() {
        return super.getMessage() + " :: in line " + line;
    }
}
