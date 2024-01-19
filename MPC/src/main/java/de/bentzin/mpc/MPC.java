package de.bentzin.mpc;

import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.Date;
import java.util.logging.*;

public class MPC {

    @Nullable
    public static Logger logger;


    public static void main(String[] args) {
        setupLogging();
        logger = Logger.getLogger(MPC.class.getName());
        logger.log(Level.INFO, "Welcome to the MPC Compiler!");
        if (args.length == 0) {
            logger.info("Please provide an .asm file to compile!");
        } else if (args.length == 1) {
            logger.info("Searching for file: " + args[0]);
            File file = new File(args[0]);
            if (file.isFile() && file.exists()) {
                logger.info("Reading file: " + args[0]);
                String name = file.getName().substring(0, file.getName().lastIndexOf("."));
                if (file.getName().endsWith(".asm")) {
                    logger.warning("File does not end with .asm: " + args[0]);
                }
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    InputStreamReader reader = new InputStreamReader(fileInputStream);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    Compiler compiler = new Compiler(bufferedReader);
                    byte[] compile = compiler.compile(); //Compile the file
                    try (FileOutputStream fileOutputStream = new FileOutputStream(name + ".progm")) {
                        fileOutputStream.write(compile);
                    }


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else {
                logger.severe("Failed to read file: " + args[0]);
            }
        }
    }

    private static void setupLogging() {
        Logger mainLogger = Logger.getLogger("de.bentzin.mpc");
        mainLogger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter() {
            private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

            @Override
            public synchronized String format(LogRecord lr) {
                return String.format(format,
                        new Date(lr.getMillis()),
                        lr.getLevel().getLocalizedName(),
                        lr.getMessage()
                );
            }
        });
        mainLogger.addHandler(handler);
    }
}