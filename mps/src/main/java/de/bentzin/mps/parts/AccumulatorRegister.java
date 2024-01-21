package de.bentzin.mps.parts;

import de.bentzin.mps.HexChar;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class AccumulatorRegister extends Register<AccumulatorRegister.HexAndCarry> {

    public AccumulatorRegister(@NotNull BinarySignal clk, @NotNull Signal<HexAndCarry> dataIn) {
        super("Accumulator Register", clk, dataIn, HexAndCarry.defaultHexAndCarry());
    }

    public record HexAndCarry(HexChar hex, boolean carry) {
        @Contract(" -> new")
        static @NotNull HexAndCarry defaultHexAndCarry() {
            return new HexAndCarry(HexChar.x0, false);
        }
    }


}
