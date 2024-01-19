package de.bentzin.mpc;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Compiler {

    @NotNull
    public static Logger logger = Logger.getLogger(MPC.class.getName());


    private final @NotNull BufferedReader reader;

    public Compiler(@NotNull BufferedReader reader) {
        this.reader = reader;
    }

    public byte @NotNull [] compile() throws IOException {

        List<Command> commandList = new ArrayList<>();
        while (reader.ready()) {
            String line = reader.readLine();
            try {
                Command command = parseLine(line);
                commandList.add(command);
            } catch (CompilerException e) {
                logger.severe("Failed to compile!");
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }

        byte[] compiled = new byte[commandList.size()];
        for (int i = 0; i < commandList.size(); i++) {
            Command command = commandList.get(i);
            byte binary = command.toByte();
                logger.info("Compiled Command no. " + i + ": " + binary);
                compiled[i] = binary;
            System.console();
        }
        String dump = HexDumpUtil.formatHexDump(compiled, 0, compiled.length);
        logger.info("Successfully compiled: \n " + dump + "\n");
        return compiled;
    }


    private int linec = 0;

    private @NotNull Command parseLine(@NotNull String line) throws CompilerException {
        String[] split = line.split(" ");
        if (split.length == 2) {
            String command = split[0];
            String data = split[1];
            char data_char;
            AtomicReference<String> operator = new AtomicReference<>(null);
            Operator.INDEX.forEach((key, value) -> {
                if (operator.get() != null) return;
                String s = key.split("_")[0];
                if (s.equals(command)) {
                    operator.set(s);
                }
            });
            if (operator.get() == null) {
                throw CompilerException.unexpectedCommand(linec, command);
            } else {
                char operand = data.charAt(0);
                if (operand == '#' && data.length() == 2) {
                    operator.set(operator.get() + "_VALUE");
                    data_char = data.charAt(1);
                } else if (operand == '(' && data.charAt(data.length() - 1) == ')' && data.length() == 3) {
                    operator.set(operator.get() + "_ADDRESS");
                    data_char = data.charAt(1);
                } else if (data.length() == 1 && HexChar.INDEX.get(data.charAt(0)) != null) {
                    data_char = data.charAt(0);
                    //BR Case
                } else {
                    throw CompilerException.unknownData(linec, data);
                }
            }
            linec++;
            return new Command(Operator.INDEX.get(operator.get()), HexChar.INDEX.get(data_char));
        } else if (split.length == 1) {
            if (split[0].equals("NOP")) {
                linec++;
                return new Command(Operator.NOP, HexChar.x0);
            } else {
                throw CompilerException.unexpectedCommand(linec, split[0]);
            }

        } else {
            throw CompilerException.unexpectedCommand(linec, line);
        }

    }

}
