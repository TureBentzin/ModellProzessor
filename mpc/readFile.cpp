//
// Created by ture on 17.01.2024.
//

#include "readFile.h"

std::string mpcfile::readFile(const char *filename) {
    std::ifstream t(filename);
    std::stringstream buffer;
    buffer << t.rdbuf();
    return buffer.str();
}
