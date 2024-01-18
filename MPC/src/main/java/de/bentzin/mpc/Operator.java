package de.bentzin.mpc;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class Operator implements Predicate<HexChar> {

    public static final @NotNull Operator NOP = new Operator(HexChar.x0);
    public static final @NotNull Operator LDA_VALUE = new Operator(HexChar.x1);
    public static final @NotNull Operator LDA_ADDRESS = new Operator(HexChar.x2);

    public static final @NotNull Operator STA_ADDRESS = new Operator(HexChar.x3);

    public static final @NotNull Operator ADD_VALUE = new Operator(HexChar.x4);
    public static final @NotNull Operator ADD_ADDRESS = new Operator(HexChar.x5);

    public static final @NotNull Operator SUB_VALUE = new Operator(HexChar.x6);
    public static final @NotNull Operator SUB_ADDRESS = new Operator(HexChar.x7);

    public static final @NotNull Operator JMP = new Operator(HexChar.x8);
    public static final @NotNull Operator BRZ = new Operator(HexChar.x9);
    public static final @NotNull Operator BRC = new Operator(HexChar.xA);
    public static final @NotNull Operator BRN = new Operator(HexChar.xB);

    public static final @NotNull BiMap<String, Operator> INDEX = HashBiMap.create(16);

    static {
        //populate INDEX
        INDEX.put("NOP", NOP);
        INDEX.put("LDA_VALUE", LDA_VALUE);
        INDEX.put("LDA_ADDRESS", LDA_ADDRESS);
        INDEX.put("STA_ADDRESS", STA_ADDRESS);
        INDEX.put("ADD_VALUE", ADD_VALUE);
        INDEX.put("ADD_ADDRESS", ADD_ADDRESS);
        INDEX.put("SUB_VALUE", SUB_VALUE);
        INDEX.put("SUB_ADDRESS", SUB_ADDRESS);
        INDEX.put("JMP", JMP);
        INDEX.put("BRZ", BRZ);
        INDEX.put("BRC", BRC);
        INDEX.put("BRN", BRN);

    }

    private final @NotNull HexChar operator;

    public Operator(@NotNull HexChar operator) {
        this.operator = operator;
    }


    public @NotNull HexChar getOperator() {
        return operator;
    }

    public byte @NotNull [] toByteArray() {
        return operator.toBytes();
    }

    @Override
    public boolean test(@NotNull HexChar hexChar) {
        return hexChar.getIntValue() == operator.getIntValue();
    }
}
