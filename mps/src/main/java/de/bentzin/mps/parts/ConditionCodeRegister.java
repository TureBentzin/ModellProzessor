package de.bentzin.mps.parts;

import de.bentzin.mps.HexChar;
import org.jetbrains.annotations.NotNull;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class ConditionCodeRegister extends ClockSensitive {
    private @NotNull Signal<HexChar> dataIn;
    private @NotNull BinarySignal carryIn;

    private boolean negative = false;
    private boolean zero = false;
    private boolean carry = false;


    private @NotNull BinarySignal negativeOut = new BinarySignal(false);
    private @NotNull BinarySignal zeroOut = new BinarySignal(false);
    private @NotNull BinarySignal carryOut = new BinarySignal(false);


    public ConditionCodeRegister(@NotNull BinarySignal clk, @NotNull Signal<HexChar> dataIn, @NotNull BinarySignal carryIn) {
        super(clk);
        this.dataIn = dataIn;
        this.carryIn = carryIn;
    }

    public @NotNull Signal<Boolean> getCarryIn() {
        return carryIn;
    }

    public void setCarryIn(@NotNull BinarySignal carryIn) {
        this.carryIn = carryIn;
    }

    @Override
    public void onClock(BinarySignalEvent event) {
        if (event.equals(BinarySignalEvent.RISING)) {
            negative = dataIn.get().mightBeNegative();
            zero = dataIn.get().isZero();
            carry = carryIn.get();

            negativeOut.set(negative);
            zeroOut.set(zero);
            carryOut.set(carry);
        }

    }

    public @NotNull BinarySignal getCarryOut() {
        return carryOut;
    }

    public void setCarryOut(@NotNull BinarySignal carryOut) {
        this.carryOut = carryOut;
    }

    public @NotNull BinarySignal getNegativeOut() {
        return negativeOut;
    }

    public void setNegativeOut(@NotNull BinarySignal negativeOut) {
        this.negativeOut = negativeOut;
    }

    public @NotNull BinarySignal getZeroOut() {
        return zeroOut;
    }

    public void setZeroOut(@NotNull BinarySignal zeroOut) {
        this.zeroOut = zeroOut;
    }

    public void setCarry(boolean carry) {
        this.carry = carry;
    }

    public void setNegative(boolean negative) {
        this.negative = negative;
    }

    public void setZero(boolean zero) {
        this.zero = zero;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "Condition code register";
    }
}
