package de.bentzin.mps.parts;

import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class Multiplexer<T, S extends Signal<T>> {
    private @NotNull S input0;
    private @NotNull S input1;
    private @NotNull S output;
    private @NotNull BinarySignal select;
    private boolean inverted;


    public Multiplexer(@NotNull S input0, @NotNull S input1, @NotNull Supplier<S> outputFactory) {
        this.input0 = input0;
        this.input1 = input1;
        output = outputFactory.get();
    }

    public S getInput0() {
        return input0;
    }

    public S getInput1() {
        return input1;
    }

    public S getOutput() {
        return output;
    }

}
