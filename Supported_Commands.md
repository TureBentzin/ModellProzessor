# Supported Commands

The ModellProzessor only supports a few basic commands and has very limited memory. It has no IO capabilities and no
stack. The ModellProzessor is a very simple processor and is only meant to be used for educational purposes.

## Limitations

The processor has a very limited memory. It has 16 memory addresses and each address can only hold 4 bits. This means
that each address can only hold a value from 0 to F.

Value parameter "#n" should be in hex format. This means that the value 13 would be a D. Please use uppercase letters
for hex
values.

The same applies to addresses provided in brackets "(n)". The address 13 would be a D.

## Commands:

#### LDA \#n

Loads the value n into the accumulator.

#### LDA (n)

Loads the value from the address n into the accumulator.

#### STA (n)

Stores the value from the accumulator into the address n.

#### ADD \#n

Adds the value n to the accumulator.

#### ADD (n)

Adds the value from the address n to the accumulator.

#### SUB \#n

Subtracts the value n from the accumulator.

#### SUB (n)

Subtracts the value from the address n from the accumulator.

#### JMP n

Jumps to the address n.

#### BRZ n

Jumps to the address n if the condition code registers zero flag is high.

#### BRN n

Jumps to the address n if the condition code registers negative flag is high.

#### BRC n

Jumps to the address n if the condition code registers carry flag is high.
