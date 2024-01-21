package de.bentzin.mps;


import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Files;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class MPS {

    public static final @NotNull Logger LOGGER = Logger.getLogger(MPS.class.getName());

    private static void setupLogging() {
        Logger mainLogger = Logger.getLogger("de.bentzin.mps");
        mainLogger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter() {
            private static final @NotNull String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

            @Override
            public synchronized @NotNull String format(@NotNull LogRecord lr) {
                return String.format(format,
                        new Date(lr.getMillis()),
                        lr.getLevel().getLocalizedName(),
                        lr.getMessage()
                );
            }
        });
        mainLogger.addHandler(handler);
    }

    public static void main(@NotNull String @NotNull [] args) {
        setupLogging();

        LOGGER.info("Welcome to MPS!");
        if (args.length == 1) {
            LOGGER.info("Loading binary: " + args[0]);
            File binary = new File(args[0]);
            if (binary.isFile() && binary.exists()) {
                LOGGER.info("Binary found!");
                LOGGER.info("Loading binary into memory...");
                try {
                    byte[] bytes = Files.readAllBytes(binary.toPath());
                    LOGGER.info("Binary loaded!");
                    LOGGER.info("Binary size: " + bytes.length + " bytes");
                    LOGGER.info("Binary hexdump:");
                    LOGGER.info(HexDumpUtil.formatHexDump(bytes, 0, bytes.length));
                } catch (Exception e) {
                    LOGGER.warning("Failed to load binary!");
                    LOGGER.warning(e.toString());
                }
            }else {
                LOGGER.severe("Failed to find binary: " + args[0]);
                System.exit(-1);
            }

        } else {
            LOGGER.warning("MPS requires one argument!");
            LOGGER.warning("-> MPS <binary>");
        }
    }
}
