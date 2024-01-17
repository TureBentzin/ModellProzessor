//
// Created by ture on 17.01.2024.
//

#ifndef MPC_CONSOLE_IO_H
#define MPC_CONSOLE_IO_H

#pragma once

#include <cstdio>
#include "string"


using std::string, std::cout, std::endl;

const char prefix[] = "[MPC]: ";

namespace console {


    void println(const char format[]...) {
        printf("%s %s", __TIME__, prefix);
        printf(format);
    }

    void print(const char *format...) {
        printf("%s %s", __TIME__, prefix);
        printf(format);
    }


}


#endif //MPC_CONSOLE_IO_H
