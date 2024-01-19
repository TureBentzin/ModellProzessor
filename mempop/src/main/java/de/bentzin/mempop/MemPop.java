package de.bentzin.mempop;


import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ture Bentzin
 * @since 19-01-2024
 */
public class MemPop {


    public static void main(@NotNull String @NotNull [] args) {
        File progm;
        Map<Integer, Integer> mmap = new HashMap<>();
        if (args.length == 2) {
            if (!args[0].endsWith(".progm")) {
                System.err.println("Please provide a .progm file!");
                System.exit(1);
            }
            if (!args[1].endsWith(".mem")) {
                System.err.println("Please provide a .mem file!");
                System.exit(1);
            }
            progm = new File(args[0]);
            if (!progm.isFile() && !progm.exists()) {
                System.err.println("Cant find programm file: " + args[0]);
                System.exit(-1);
            }

            File mem = new File(args[1]);
            if (!mem.isFile() && !mem.exists()) {
                System.err.println("Cant find memory file: " + args[1]);
                System.exit(-1);
            }

            try {
                mmap = MemFileUtil.readMem(mem);
            } catch (IOException e) {
                System.err.println(e.toString());
                System.exit(-1);
            }
        }
        if (args.length == 1) {
            if (!args[0].endsWith(".progm")) {
                System.err.println("Please provide a .progm file!");
                System.exit(1);
            }
            progm = new File(args[0]);
            mmap = new HashMap<>();
        } else {
            System.err.println("memPop requires at least one argument!");
            System.err.println("-> memPop <progm> (mem)");
            System.exit(1);
            throw new IllegalStateException("Reached end of main without exiting!");
        }

        //read bytes array from progm file
        byte[] programm;
        try {
            programm = Files.readAllBytes(progm.toPath());
        } catch (IOException e) {
            System.err.println(e.toString());
            System.exit(-1);
            return;
        }

        byte[] memory = new byte[16];
        if (programm.length > 16) {
            System.err.println("Programm is too big! Max size is 16 bytes!");
            System.exit(-1);
        }
        //Insert programm into memory
        System.arraycopy(programm, 0, memory, 0, programm.length);

        //Insert memory from mem file into memory
        for (Map.Entry<Integer, Integer> entry : mmap.entrySet()) {
            int address = entry.getKey();
            int value = entry.getValue();
            System.out.println("Inserting " + value + " at address " + address);
            memory[address] = (byte) value;
        }
        System.out.println("Finished populating memory: \n" + HexDumpUtil.formatHexDump(memory, 0, memory.length));
        File out = new File(args[0].substring(0, args[0].lastIndexOf(".")) + ".bin");
        try {
            Files.write(out.toPath(), memory);
        } catch (IOException e) {
            System.err.println(e.toString());
            System.exit(-1);
        }

    }

}