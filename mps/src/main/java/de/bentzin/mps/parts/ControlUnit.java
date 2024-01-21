package de.bentzin.mps.parts;

/*
This is probably not implemented like the original processor.
This is based on educated guesses and the information from the TI Modules "VHDL Praktikum 3".
This simulator does not include the "Instruction decoder". If you want to patch this bahavior in, you can do so by
opening a pull request on GitHub. I will gladly accept it.

In this implementation, the clock is managed by the "Processor" class.
 */

import de.bentzin.mps.HexChar;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class ControlUnit {
    private final Signal<HexChar> instruction;
    private final BinarySignal zero;
    private final BinarySignal carry;
    private final BinarySignal negative;

    //Multiplexer 1 (pre accumulator)
    private final BinarySignal m1 = new BinarySignal(false);

    //Multiplexer 2 (pre RAM)
    private final BinarySignal m2 = new BinarySignal(false);

    //Accumulator enable
    private BinarySignal e = new BinarySignal(false);

    //Accumulator load (accumulator multiplexer)
    private BinarySignal ld = new BinarySignal(false);

    //ALU subtract
    private BinarySignal sub = new BinarySignal(false);

    //RAM write enable
    private BinarySignal we = new BinarySignal(false);

}
