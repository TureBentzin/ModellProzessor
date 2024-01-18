package de.bentzin.mpc;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class Compiler {

    @Nullable
    public static Logger logger = Logger.getLogger(MPC.class.getName());


    private final @NotNull BufferedReader reader;

    public Compiler(@NotNull BufferedReader reader) {
        this.reader = reader;
    }

    public byte @NotNull [] compile() {

    }
}
