package de.bentzin.mpc;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Compiler {
    private final @NotNull BufferedReader reader;

    public Compiler(@NotNull BufferedReader reader) {
        this.reader = reader;
    }

    public byte @NotNull [] compile() {

    }
}
