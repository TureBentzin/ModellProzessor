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
    //Multiplexer 1 (pre accumulator)
    private final @NotNull BinarySignal m1 = new BinarySignal(false);
    //Multiplexer 2 (pre RAM)
    private final @NotNull BinarySignal m2 = new BinarySignal(false);
    private @NotNull Signal<HexChar> instructionIn;
    private @NotNull BinarySignal zeroIn;
    private @NotNull BinarySignal carryIn;
    private @NotNull BinarySignal negativeIn;
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

    public ControlUnit(@NotNull Signal<HexChar> instructionIn, @NotNull BinarySignal zeroIn, @NotNull BinarySignal carryIn, @NotNull BinarySignal negativeIn) {
        this.instructionIn = instructionIn;
        this.zeroIn = zeroIn;
        this.carryIn = carryIn;
        this.negativeIn = negativeIn;

        instructionIn.listen(this::update);
        zeroIn.listen(this::update);
        carryIn.listen(this::update);
        negativeIn.listen(this::update);

    }

    public ControlUnit() {
        this.instructionIn = new Signal<>(HexChar.x0);
        this.zeroIn = new BinarySignal(false);
        this.carryIn = new BinarySignal(false);
        this.negativeIn = new BinarySignal(false);
    }

    public void update() {
        HexChar instruction = instructionIn.get();
        
    }

    public @NotNull Signal<HexChar> getInstructionIn() {
        return instructionIn;
    }

    public void setInstructionIn(@NotNull Signal<HexChar> instructionIn) {
        this.instructionIn = instructionIn;
    }

    public @NotNull BinarySignal getZeroIn() {
        return zeroIn;
    }

    public void setZeroIn(@NotNull BinarySignal zeroIn) {
        this.zeroIn = zeroIn;
    }

    public @NotNull BinarySignal getCarryIn() {
        return carryIn;
    }

    public void setCarryIn(@NotNull BinarySignal carryIn) {
        this.carryIn = carryIn;
    }

    public @NotNull BinarySignal getNegativeIn() {
        return negativeIn;
    }

    public void setNegativeIn(@NotNull BinarySignal negativeIn) {
        this.negativeIn = negativeIn;
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
