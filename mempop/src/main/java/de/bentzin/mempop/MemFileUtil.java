package de.bentzin.mempop;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ture Bentzin
 * @since 19-01-2024
 */
public class MemFileUtil {

    private MemFileUtil() {
    }


    public static @NotNull Map<Integer, Integer> readMem(@NotNull File mem) throws IOException {
        Map<Integer, Integer> memory = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(mem))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(" ");
                if (split.length != 2) {
                    System.err.println("Failed to populate memory! .mem file is invalid!");
                    throw new IOException("Invalid line: " + line);
                }
                int address = Integer.parseInt(split[0], 16);
                int value = Integer.parseInt(split[1], 16);
                memory.put(address, value);
            }
        }
        return memory;
    }
}
