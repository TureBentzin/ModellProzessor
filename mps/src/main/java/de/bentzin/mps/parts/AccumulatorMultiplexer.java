package de.bentzin.mps.parts;

import de.bentzin.mps.parts.AccumulatorRegister.HexAndCarry;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class AccumulatorMultiplexer extends Multiplexer<HexAndCarry, Signal<HexAndCarry>> {
    public AccumulatorMultiplexer(@NotNull Signal<HexAndCarry> input0,
                                  @NotNull Signal<HexAndCarry> input1,
                                  @NotNull BinarySignal select, @NotNull Supplier<Signal<HexAndCarry>> outputFactory) {
        super("Accumulator multiplexer", input0, input1, select, outputFactory);
    }
}
