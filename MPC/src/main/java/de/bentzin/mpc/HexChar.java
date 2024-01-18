package de.bentzin.mpc;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.jetbrains.annotations.NotNull;

public class HexChar {

    public static final @NotNull HexChar x0 = new HexChar('0');
    public static final @NotNull HexChar x1 = new HexChar('1');
    public static final @NotNull HexChar x2 = new HexChar('2');
    public static final @NotNull HexChar x3 = new HexChar('3');
    public static final @NotNull HexChar x4 = new HexChar('4');
    public static final @NotNull HexChar x5 = new HexChar('5');

    public static final @NotNull HexChar x6 = new HexChar('6');

    public static final @NotNull HexChar x7 = new HexChar('7');

    public static final @NotNull HexChar x8 = new HexChar('8');

    public static final @NotNull HexChar x9 = new HexChar('9');

    public static final @NotNull HexChar xA = new HexChar('A');

    public static final @NotNull HexChar xB = new HexChar('B');

    public static final @NotNull HexChar xC = new HexChar('C');

    public static final @NotNull HexChar xD = new HexChar('D');

    public static final @NotNull HexChar xE = new HexChar('E');

    public static final @NotNull HexChar xF = new HexChar('F');

    public static final @NotNull BiMap<Character, HexChar> INDEX = HashBiMap.create(16);

    static {
        //populate INDEX
        INDEX.put('0', x0);
        INDEX.put('1', x1);
        INDEX.put('2', x2);
        INDEX.put('3', x3);
        INDEX.put('4', x4);
        INDEX.put('5', x5);
        INDEX.put('6', x6);
        INDEX.put('7', x7);
        INDEX.put('8', x8);
        INDEX.put('9', x9);
        INDEX.put('A', xA);
        INDEX.put('B', xB);
        INDEX.put('C', xC);
        INDEX.put('D', xD);
        INDEX.put('E', xE);
        INDEX.put('F', xF);
    }


    private final char c;
    private final int intValue;

    private HexChar(char c) {
        this.c = c;
        this.intValue = hexCharToInt(c);
    }

    protected static int hexCharToInt(char hexChar) {
        return Character.digit(hexChar, 16);
    }

    protected static byte @NotNull [] hexCharToByteArray(char hexChar) {
        int intValue = hexCharToInt(hexChar);
        return new byte[]{(byte) intValue};
    }

    public char getChar() {
        return c;
    }

    public int getIntValue() {
        return intValue;
    }

    public byte @NotNull [] toBytes() {
        return hexCharToByteArray(c);
    }

    @Override
    public boolean equals(@NotNull Object obj) {
        return this == obj;
    }
}
