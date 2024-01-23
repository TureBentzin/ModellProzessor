package de.bentzin.mps.parts;

import de.bentzin.mps.HexChar;
import org.jetbrains.annotations.NotNull;

import static de.bentzin.mps.HexChar.x0;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class ArithmeticLogicUnit implements Part {
    private @NotNull Signal<HexChar> inputX;
    private @NotNull Signal<HexChar> inputY;
    private @NotNull BinarySignal subtract;

    private @NotNull Signal<HexChar> output = new Signal<>(x0);
    private @NotNull BinarySignal carry = new BinarySignal(false);

    public ArithmeticLogicUnit(@NotNull Signal<HexChar> inputX, @NotNull Signal<HexChar> inputY, @NotNull BinarySignal subtract) {
        this.inputX = inputX;
        this.inputY = inputY;
        this.subtract = subtract;

        //listen
        inputY.listen((hexCharSignal, hexCharSignalEvent) -> calculate());
        inputX.listen((hexCharSignal, hexCharSignalEvent) -> calculate());
        subtract.listen((binarySignal, binarySignalEvent) -> calculate());
    }

    public ArithmeticLogicUnit() {
    }

    private void calculate() {
        HexChar x = inputX.get();
        HexChar y = inputY.get();
        HexChar result;
        boolean carry;
        ArithmeticResult res;
        if (subtract.isLow()) {
            //addition
            res = x.add(y);
        } else {
            //subtraction
            res = x.subtract(y);
        }
        result = res.result();
        carry = res.carry();
        output.set(result);
        this.carry.set(carry);
    }

    public @NotNull BinarySignal getCarry() {
        return carry;
    }

    public void setCarry(@NotNull BinarySignal carry) {
        this.carry = carry;
    }

    public @NotNull BinarySignal getSubtract() {
        return subtract;
    }

    public void setSubtract(@NotNull BinarySignal subtract) {
        this.subtract = subtract;
    }

    public @NotNull Signal<HexChar> getInputX() {
        return inputX;
    }

    public void setInputX(@NotNull Signal<HexChar> inputX) {
        this.inputX = inputX;
    }

    public @NotNull Signal<HexChar> getInputY() {
        return inputY;
    }

    public void setInputY(@NotNull Signal<HexChar> inputY) {
        this.inputY = inputY;
    }

    public @NotNull Signal<HexChar> getOutput() {
        return output;
    }

    public void setOutput(@NotNull Signal<HexChar> output) {
        this.output = output;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "Arithmetic Logic Unit";
    }
}

