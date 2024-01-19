package de.bentzin.mempop;


import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * @author Ture Bentzin
 * @since 19-01-2024
 */
public class MemPop {
    public static void main(@NotNull String @NotNull [] args) {
        if(args.length == 2) {
            if(!args[0].endsWith(".progm")){
                System.err.println("Please provide a .progm file!");
                System.exit(1);
            }
            if(!args[1].endsWith(".mem")){
                System.err.println("Please provide a .mem file!");
                System.exit(1);
            }
            File progm = new File(args[0]);
            File mem = new File(args[1]);

        }else {
            System.err.println("Please provide a .progm file and a .mem file!");
        }
        System.out.println("Hello world!");
    }
}