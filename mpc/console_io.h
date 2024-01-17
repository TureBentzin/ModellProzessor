//
// Created by ture on 17.01.2024.
//

#ifndef MPC_CONSOLE_IO_H
#define MPC_CONSOLE_IO_H

#pragma once

#include "iostream"
#include "string"


using std::string, std::cout, std::endl;

const char prefix[] = "[MPC]: ";

namespace console {

    namespace color {
        enum Code {
            FG_RED = 31,
            FG_GREEN = 32,
            FG_BLUE = 34,
            FG_DEFAULT = 39,
            BG_RED = 41,
            BG_GREEN = 42,
            BG_BLUE = 44,
            BG_DEFAULT = 49
        };

        class Modifier {
            Code code;
        public:
            Modifier(Code pCode) : code(pCode) {}

            friend std::ostream &
            operator<<(std::ostream &os, const Modifier &mod) {
                return os << "\033[" << mod.code << "m";
            }
        };
    }

    /*
         void println(const string &s, unsigned char color = color::white) {
        cout << color << prefix << s << endl;
    }

    void print(const string &s, unsigned char color = color::white) {
        cout << color << prefix << s;
    }
     */


}


#endif //MPC_CONSOLE_IO_H
