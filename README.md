# ModellProzessor

Compiler & Simulator for the ModellProzessor from the "TI Module" at FH Aachen University.

## How to use

### MPC - ModellProzessor Compiler

The MPC is a very simple and basic compiler for the ModellProzessor.
It is written in Java and can be compiled from scratch using the `JDK Development Kit` [[download]](https://www.oracle.com/de/java/technologies/downloads/#java21)!
To compile MPC use maven and run the install goal: [[install maven]](https://maven.apache.org/download.cgi)

```bash
mvn clean install
```

Afterwards you can find the compiled jar file in the target folder.
To run the MPC use the following command:

```bash
java -jar mpc.jar input_file.asm
```

Replace input_file.asm with the path to your assembly file.

## Information
Currently MPC does not support labels, so you have to use
the absolute address for jumps and calls.
Please define addresses as a hex value. That means address '13' would be a 'D'. Please use uppercase letters for hex
values and commands.
A list of supported commands can be found in the Supported_Commands.md file (this follows the documentation of the TI
Module and the ModellProzessor used).
The output of the MPC is a binary file with the same name as the input file but with the extension .bin.

Please note that this is a very basic compiler and does not support any complex error handling. If you use it, please
make sure that your assembly file is correct.
This was done for fun and should never be used in production (at least in the state it is right now).
But this is great if you want to study the ModellProzessor outside the TI Module and its Praktikum.
