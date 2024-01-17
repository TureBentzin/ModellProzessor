#include <iostream>

#include "console_io.h"

int main(int argc, char *argv[]) {
    //console::println("Hello World!", console::color::yellow);
    console::color::Modifier yellow(console::color::FG_RED);
    cout << "Test: " << yellow << "YELLOW";
    return 0;
}
