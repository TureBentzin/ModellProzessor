package de.bentzin.mps.parts;

import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class Multiplexer<T, S extends Signal<T>> {
    private final boolean inverted;
    private final @NotNull S output;
    private final @NotNull BinarySignal select;
    private @NotNull S input0;
    private @NotNull S input1;


    public Multiplexer(@NotNull S input0, @NotNull S input1, @NotNull BinarySignal select, @NotNull Supplier<S> outputFactory) {
        this(input0, input1, select, outputFactory, false);
    }

    public Multiplexer(@NotNull S input0, @NotNull S input1, @NotNull BinarySignal select, @NotNull Supplier<S> outputFactory, boolean inverted) {
        this.input0 = input0;
        this.input1 = input1;
        output = outputFactory.get();
        this.inverted = inverted;
        this.select = select;
        select.listen((signal, signalEvent) -> updateOutput());
        input0.listen((tSignal, tSignalEvent) -> updateOutput());
        input1.listen((tSignal, tSignalEvent) -> updateOutput());
    }

    public @NotNull S getInput0() {
        return input0;
    }

    public void setInput0(@NotNull S input0) {
        this.input0 = input0;
    }

    public @NotNull S getInput1() {
        return input1;
    }

    public void setInput1(@NotNull S input1) {
        this.input1 = input1;
    }

    public @NotNull S getOutput() {
        return output;
    }

    private void updateOutput() {
        if (inverted) {
            output.set(select.get() ? input0.get() : input1.get());
        } else {
            output.set(select.get() ? input1.get() : input0.get());
        }
    }


}
