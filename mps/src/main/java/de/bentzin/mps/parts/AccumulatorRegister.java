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

        public static @NotNull HexAndCarry fromHex(@NotNull HexChar hex) {
            return new HexAndCarry(hex, false);
        }

        public static @NotNull Signal<HexAndCarry> fromHex(@NotNull Signal<HexChar> hex) {
            Signal<HexAndCarry> signal = new Signal<>(fromHex(hex.get()));
            hex.listen((what, event) -> signal.set(fromHex(what.get())));
            return signal;
        }

        public static @NotNull Signal<HexAndCarry> fromHex(@NotNull Signal<HexChar> hex, @NotNull Signal<BinarySignal> carry) {
            Signal<HexAndCarry> signal = new Signal<>(fromHex(hex.get()));
            hex.listen((what, event) -> signal.set(new HexAndCarry(what.get(), carry.get().isHigh())));
            carry.listen((what, event) -> signal.set(new HexAndCarry(hex.get(), what.get().isHigh())));
            return signal;
        }

        @Contract(" -> new")
        public static @NotNull HexAndCarry defaultHexAndCarry() {
            return new HexAndCarry(HexChar.x0, false);
        }
    }


}
