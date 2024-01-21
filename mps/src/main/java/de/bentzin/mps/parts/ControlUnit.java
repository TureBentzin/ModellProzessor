package de.bentzin.mps.parts;

/*
This is probably not implemented like the original processor.
This is based on educated guesses and the information from the TI Modules "VHDL Praktikum 3".
This simulator does not include the "Instruction decoder". If you want to patch this bahavior in, you can do so by
opening a pull request on GitHub. I will gladly accept it.

In this implementation, the clock is managed by the "Processor" class.
 */

import de.bentzin.mps.HexChar;
import org.jetbrains.annotations.NotNull;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class ControlUnit implements Part {
    private final @NotNull Signal<HexChar> instruction;
    private final @NotNull BinarySignal zero;
    private final @NotNull BinarySignal carry;
    private final @NotNull BinarySignal negative;
    //Multiplexer 1 (pre accumulator)
    private final @NotNull BinarySignal m1 = new BinarySignal(false);
    //Multiplexer 2 (pre RAM)
    private final @NotNull BinarySignal m2 = new BinarySignal(false);
    //Accumulator enable
    private @NotNull BinarySignal e = new BinarySignal(false);
    //Accumulator load (accumulator multiplexer)
    private @NotNull BinarySignal ld = new BinarySignal(false);
    //ALU subtract
    private @NotNull BinarySignal sub = new BinarySignal(false);
    //RAM write enable
    private @NotNull BinarySignal we = new BinarySignal(false);
    //Program counter increment
    private @NotNull BinarySignal inc = new BinarySignal(false);

    public ControlUnit(@NotNull Signal<HexChar> instruction, @NotNull BinarySignal zero, @NotNull BinarySignal carry, @NotNull BinarySignal negative) {
        this.instruction = instruction;
        this.zero = zero;
        this.carry = carry;
        this.negative = negative;
    }

    public @NotNull Signal<HexChar> getInstruction() {
        return instruction;
    }

    public @NotNull BinarySignal getZero() {
        return zero;
    }

    public @NotNull BinarySignal getCarry() {
        return carry;
    }

    public @NotNull BinarySignal getNegative() {
        return negative;
    }

    public @NotNull BinarySignal getM1() {
        return m1;
    }

    public @NotNull BinarySignal getM2() {
        return m2;
    }

    public @NotNull BinarySignal getE() {
        return e;
    }

    public void setE(@NotNull BinarySignal e) {
        this.e = e;
    }

    public @NotNull BinarySignal getLd() {
        return ld;
    }

    public void setLd(@NotNull BinarySignal ld) {
        this.ld = ld;
    }

    public @NotNull BinarySignal getSub() {
        return sub;
    }

    public void setSub(@NotNull BinarySignal sub) {
        this.sub = sub;
    }

    public @NotNull BinarySignal getWe() {
        return we;
    }

    public void setWe(@NotNull BinarySignal we) {
        this.we = we;
    }

    public @NotNull BinarySignal getInc() {
        return inc;
    }

    public void setInc(@NotNull BinarySignal inc) {
        this.inc = inc;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "Control Unit";
    }
}
