package de.bentzin.mps;

import de.bentzin.mps.parts.ControlUnit;
import de.bentzin.mps.parts.Signal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Ture Bentzin
 * @since 21-01-2024
 */
public class ControlUnitException extends RuntimeException {
    private @Nullable ControlUnit controlUnit;

    public ControlUnitException(@NotNull String message, @Nullable ControlUnit controlUnit) {
        super(message);
        this.controlUnit = controlUnit;
    }

    public ControlUnitException(@NotNull String message) {
        super(message);
    }

    @Override
    public @NotNull String getMessage() {
        StringBuilder builder = new StringBuilder(super.getMessage());
        if (controlUnit != null) {
            Signal<HexChar> instructionIn = controlUnit.getInstructionIn();
            builder.append("\n (Instruction: ").append(instructionIn.get()).append(")");
        }
        return builder.toString();
    }
}
