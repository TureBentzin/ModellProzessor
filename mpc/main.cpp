#include <iostream>
#include "readFile.h"

const char MPC_VERSION[] = "1.0";

using namespace mpcfile;


int main(int argc, char *argv[]) {
    printf("Welcome to the MPC Compiler v.%s\n", MPC_VERSION);
    for (int i = 0; i < argc; ++i) {
        printf("%s,", argv[i]);
    }
    printf("\n");
    std::string source;
    if (argc >= 2) {
        source = readFile(argv[1]);
    } else {
        printf("Please provide a file to compile!");
        return -1;
    }

    printf("Compiling file to buffer: \n");
    printf("%s", source.c_str());
    return 0;
}
