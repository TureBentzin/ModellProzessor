package de.bentzin.mps.parts;

import de.bentzin.mps.HexChar;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public record ArithmeticResult(HexChar result, boolean carry) {

    public ArithmeticResult {
        if (result == null) {
            throw new IllegalArgumentException("result must not be null");
        }
    }
}
