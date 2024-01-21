package de.bentzin.mps;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import de.bentzin.mps.parts.ArithmeticResult;
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

    protected static byte hexCharToByteArray(char hexChar) {
        int intValue = hexCharToInt(hexChar);
        return (byte) intValue;
    }

    public char getChar() {
        return c;
    }

    public int getIntValue() {
        return intValue;
    }

    public byte toByte() {
        return hexCharToByteArray(c);
    }

    public @NotNull ArithmeticResult add(@NotNull HexChar other) {
        int sum = this.intValue + other.intValue;
        if (sum > 15) {
            sum -= 16; //overflow
        }
        // return INDEX.get((char) sum);
        return new ArithmeticResult(INDEX.get((char) sum), sum > 15);
    }

    public @NotNull ArithmeticResult subtract(@NotNull HexChar other) {
        int difference = this.intValue - other.intValue;
        if (difference < 0) {
            difference += 16; //underflow
        }
        // return INDEX.get((char) difference);
        return new ArithmeticResult(INDEX.get((char) difference), difference < 0);
    }

    public @NotNull HexChar increment() {
        return add(x1).result()
    }

    public @NotNull HexChar decrement() {
        return subtract(x1).result();
    }

    public @NotNull HexChar and(@NotNull HexChar other) {
        int result = this.intValue & other.intValue;
        return INDEX.get((char) result);
    }

    public @NotNull HexChar or(@NotNull HexChar other) {
        int result = this.intValue | other.intValue;
        return INDEX.get((char) result);
    }

    public @NotNull HexChar xor(@NotNull HexChar other) {
        int result = this.intValue ^ other.intValue;
        return INDEX.get((char) result);
    }

    public @NotNull HexChar not() {
        int result = ~this.intValue;
        return INDEX.get((char) result);
    }

    public @NotNull HexChar shiftLeft() {
        int result = this.intValue << 1;
        return INDEX.get((char) result);
    }

    public @NotNull HexChar shiftRight() {
        int result = this.intValue >> 1;
        return INDEX.get((char) result);
    }

    public @NotNull HexChar shiftRight(byte amount) {
        int result = this.intValue >>> amount;
        return INDEX.get((char) result);
    }

    public @NotNull HexChar shiftLeft(byte amount) {
        int result = this.intValue << amount;
        return INDEX.get((char) result);
    }

    public @NotNull HexChar shiftRight(@NotNull HexChar amount) {
        int result = this.intValue >>> amount.getIntValue();
        return INDEX.get((char) result);
    }

    public @NotNull HexChar shiftLeft(@NotNull HexChar amount) {
        int result = this.intValue << amount.getIntValue();
        return INDEX.get((char) result);
    }

    public boolean isZero() {
        return this == x0;
    }

    /*
     * Returns true if the value of this HexChar is negative.
     * This is the case if the value is greater than 7.
     * This is if the binary representation of the value starts with a 1.
     */
    public boolean mightBeNegative() {
        return this == x8 || this == x9 || this == xA || this == xB || this == xC || this == xD || this == xE || this == xF;
    }

    @Override
    public boolean equals(@NotNull Object obj) {
        return this == obj;
    }
}
