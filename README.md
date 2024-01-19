# ModellProzessor

Compiler & Simulator for the ModellProzessor from the "TI Module" at FH Aachen University.

### CI Status / Official Mirror

[![pipeline status](https://git.fh-aachen.de/tb3838s/modellprozessor/badges/main/pipeline.svg)](https://git.fh-aachen.de/tb3838s/modellprozessor/-/commits/main)

There is an offical mirror of this repository at
the [FH Aachen GitLab](https://git.fh-aachen.de/tb3838s/modellprozessor).
The CI Status is generated from this mirror, so there is an additional delay of a few minutes. This is currently an
experimental setup.

## How to use

### MPC - ModellProzessor Compiler

The MPC is a very simple and basic compiler for the ModellProzessor.
It is written in Java and can be compiled from scratch using
the `JDK Development Kit` [[download]](https://www.oracle.com/de/java/technologies/downloads/#java21)!
To compile MPC use maven and run the install goal: [[install maven]](https://maven.apache.org/download.cgi)

```bash
mvn clean install -f mpc/pom.xml
```

Afterwards you can find the compiled jar file in the target folder.
To run the MPC use the following command:

```bash
java -jar mpc/target/mpc.jar input_file.asm
```

Replace input_file.asm with the path to your assembly file.

### mempop - ModellProzessor memory populator

After you compiled your assembly file you can use the mempop to populate the memory of the ModellProzessor.
This will puffer the binary file with zeros to the size of the memory and then write it to a file and insert your
data into the memory.
mempop is written in java and can be compiled from scratch using
the `JDK Development Kit` [[download]](https://www.oracle.com/de/java/technologies/downloads/#java21)!
To compile mpop use maven and run the install goal: [[install maven]](https://maven.apache.org/download.cgi)

```bash
mvn clean install -f mempop/pom.xml
```

Mempop accepts one or two arguments.
The first argument is the path to the binary file generated by the MPC.
The second argument is optional and is the path of the ".mem" file.
If you do not provide a second argument the
memory will be puffed with just zeros.

The ".mem" file needs to be in the following format:

```
4: 0
5: A
A: 3
D: D
```

You need to provide the address and the value in hex format. The address needs to be in the range of 0 to F.

To run the mempop use the following command:

```bash
java -jar mempop/target/mempop.jar input_file.progm [memory_file.mem]
```

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
