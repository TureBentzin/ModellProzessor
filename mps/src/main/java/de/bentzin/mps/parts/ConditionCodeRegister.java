package de.bentzin.mps.parts;

import org.jetbrains.annotations.NotNull;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class ConditionCodeRegister implements ClockSensitive {
    private @NotNull BinarySignal negativeIn;
    private @NotNull BinarySignal zeroIn;
    private @NotNull BinarySignal carryIn;

    private boolean negative = false;
    private boolean zero = false;
    private boolean carry = false;


    private @NotNull BinarySignal negativeOut = new BinarySignal(false);
    private @NotNull BinarySignal zeroOut = new BinarySignal(false);
    private @NotNull BinarySignal carryOut = new BinarySignal(false);


    public ConditionCodeRegister(@NotNull BinarySignal negativeIn, @NotNull BinarySignal zeroIn, @NotNull BinarySignal carryIn) {
        this.negativeIn = negativeIn;
        this.zeroIn = zeroIn;
        this.carryIn = carryIn;
    }

    public @NotNull Signal<Boolean> getCarryIn() {
        return carryIn;
    }

    public void setCarryIn(@NotNull BinarySignal carryIn) {
        this.carryIn = carryIn;
    }

    public @NotNull Signal<Boolean> getNegativeIn() {
        return negativeIn;
    }

    public void setNegativeIn(@NotNull BinarySignal negativeIn) {
        this.negativeIn = negativeIn;
    }

    public @NotNull Signal<Boolean> getZeroIn() {
        return zeroIn;
    }

    public void setZeroIn(@NotNull BinarySignal zeroIn) {
        this.zeroIn = zeroIn;
    }

    @Override
    public void onClock(@NotNull ClockEdge edge) {
        if (edge == ClockEdge.RISING) {
            negative = negativeIn.get();
            zero = zeroIn.get();
            carry = carryIn.get();

            negativeOut.set(negative);
            zeroOut.set(zero);
            carryOut.set(carry);
        }

    }

    public @NotNull BinarySignal getCarryOut() {
        return carryOut;
    }

    public @NotNull BinarySignal getNegativeOut() {
        return negativeOut;
    }

    public @NotNull BinarySignal getZeroOut() {
        return zeroOut;
    }

    public void setCarry(boolean carry) {
        this.carry = carry;
    }

    public void setCarryOut(@NotNull BinarySignal carryOut) {
        this.carryOut = carryOut;
    }

    public void setNegative(boolean negative) {
        this.negative = negative;
    }

    public void setNegativeOut(@NotNull BinarySignal negativeOut) {
        this.negativeOut = negativeOut;
    }

    public void setZero(boolean zero) {
        this.zero = zero;
    }

    public void setZeroOut(@NotNull BinarySignal zeroOut) {
        this.zeroOut = zeroOut;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "ConditionCodeRegister";
    }
}
