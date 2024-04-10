package de.bentzin.mps;

import de.bentzin.mps.parts.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class Processor {
    //clock
    private final @NotNull BinarySignal clk = new BinarySignal(false);

    //control unit
    @ProcessorPart
    private @Nullable ControlUnit controlUnit;

    //multiplexer
    @ProcessorPart
    private @Nullable Multiplexer<HexChar, Signal<HexChar>> m1;
    @ProcessorPart
    private @Nullable Multiplexer<HexChar, Signal<HexChar>> m2;

    //accumulator
    @ProcessorPart
    private @Nullable AccumulatorRegister accumulatorRegister;
    @ProcessorPart
    private @Nullable AccumulatorMultiplexer accumulatorMultiplexer;
    @ProcessorPart
    private @Nullable ArithmeticLogicUnit arithmeticLogicUnit;


    //condition code register
    @ProcessorPart
    private @Nullable ConditionCodeRegister conditionCodeRegister;

    //program counter
    @ProcessorPart
    private @Nullable ProgramCounter programCounter;

    //RAM
    @ProcessorPart
    private @Nullable RAM ram;

    //instruction register
    @ProcessorPart
    private @Nullable Register<HexChar> instructionRegister;

    //data register
    @ProcessorPart
    private @Nullable Register<HexChar> dataRegister;

    public void construct() {
        controlUnit = new ControlUnit();
        ram = new RAM(clk);

        instructionRegister = new Register<>("Instruction register", clk, ram.getCommandOut(), HexChar.x0);
        controlUnit.setInstructionIn(instructionRegister.getData_out());

        dataRegister = new Register<>("Data register", clk, ram.getDataOut(), HexChar.x0);

        m1 = new Multiplexer<>("M1", ram.getDataOut(), dataRegister.getData_out(), controlUnit.getM1(), () -> new Signal<>(HexChar.x0));

        programCounter = new ProgramCounter(clk, controlUnit.getInc(), controlUnit.getLoadCounter(), m1.getOutput());
       // accumulatorRegister = new AccumulatorRegister(clk, );

    }

    private @NotNull Set<Part> parts() {
        Field[] declaredFields = this.getClass().getDeclaredFields();
        Set<Part> parts = new HashSet<>();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            if (declaredField.isAnnotationPresent(ProcessorPart.class)) {
                try {
                    Object o = declaredField.get(this);
                    if (o instanceof Part part) {
                        parts.add(part);

                    }
                } catch (IllegalAccessException ignored) {

                }
            }
        }
        return parts;
    }

    private void subscribeToClock() {
        clk.listen((booleanSignal, booleanSignalEvent) -> {
            for (Part part : parts()) {
                if (part instanceof ClockSensitive sensitive) {
                    sensitive.onClock(BinarySignalEvent.from(booleanSignalEvent));
                }
            }
        });
    }

    private void clkTick() {
        clk.set(!clk.get());
    }


}

