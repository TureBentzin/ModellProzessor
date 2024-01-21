package de.bentzin.mps.parts;

import de.bentzin.mps.HexChar;
import org.jetbrains.annotations.NotNull;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class HexRegister extends Register<HexChar> {

    public HexRegister(@NotNull String identifier, @NotNull BinarySignal clk, @NotNull Signal<HexChar> dataIn) {
        super(identifier, clk, dataIn, HexChar.x0);
    }
}
