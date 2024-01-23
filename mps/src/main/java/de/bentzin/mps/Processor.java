package de.bentzin.mps;

import de.bentzin.mps.parts.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class Processor {

    private final byte @NotNull [] memory;
    //clock
    private final @NotNull BinarySignal clk = new BinarySignal(false);
    //control unit
    private @Nullable ControlUnit controlUnit;
    //multiplexer
    private @Nullable Multiplexer<HexChar, Signal<HexChar>> m1;
    private @Nullable Multiplexer<HexChar, Signal<HexChar>> m2;
    //accumulator
    private @Nullable AccumulatorRegister accumulatorRegister;
    private @Nullable AccumulatorMultiplexer accumulatorMultiplexer;
    private @Nullable ArithmeticLogicUnit arithmeticLogicUnit;
    //condition code register
    private @Nullable ConditionCodeRegister conditionCodeRegister;
    //program counter
    private @Nullable ProgramCounter programCounter;
    //RAM
    private @Nullable RAM ram;
    //instruction register
    private @Nullable Register<HexChar> instructionRegister;
    //data register
    private @Nullable Register<HexChar> dataRegister;

    public Processor(byte @NotNull [] memory) {
        this.memory = memory;
    }

    public void construct() {
        controlUnit = new ControlUnit();
        ram = new RAM(clk);

        instructionRegister = new Register<>("Instruction register", clk, ram.getCommandOut(), HexChar.x0);
        controlUnit.setInstructionIn(instructionRegister.getData_out());

        dataRegister = new Register<>("Data register", clk, ram.getDataOut(), HexChar.x0);

        m1 = new Multiplexer<>("M1", ram.getDataOut(), dataRegister.getData_out(), controlUnit.getM1(), () -> new Signal<>(HexChar.x0));

        programCounter = new ProgramCounter(clk, controlUnit.getInc(), controlUnit.getLoadCounter(), dataRegister.getData_out());

        m2 = new Multiplexer<>("M2", programCounter.getData_out(), dataRegister.getData_out(), controlUnit.getM2(), () -> new Signal<>(HexChar.x0));

        ram.setAddressIn(m2.getOutput());

        arithmeticLogicUnit = new ArithmeticLogicUnit();

        //Accumulator
        accumulatorMultiplexer = new AccumulatorMultiplexer(
                AccumulatorRegister.HexAndCarry.fromHex(m1.getOutput()),
                AccumulatorRegister.HexAndCarry.fromHex(arithmeticLogicUnit.getOutput()),
                controlUnit.getLd(),
                controlUnit.getE());

    }


}

