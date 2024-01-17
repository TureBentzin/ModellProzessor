cmake_minimum_required(VERSION 3.26)
project(mpc)

set(CMAKE_CXX_STANDARD 23)

add_executable(mpc main.cpp
        console_io.h
        readFile.cpp
        readFile.h)
