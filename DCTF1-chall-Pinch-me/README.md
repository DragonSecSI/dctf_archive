# DCTF1-chall-Pinch_me

A simple pwn challenge AlwaysGuilty.

Competitors get binary file `pinch_me`, address, and port of the server it is running on.\
The challenge is dockerized. 

## Compilation

Compile with `gcc -no-pie -o <path/to/pinch_me> <path/me/pinch_me.c>`

## Protection

Binary is not stripped and has NX and partial RELRO enabled.

## Solution

The first step of solving a pwn challange is to reverse it. For that you can use Ghidra or IDA or any program that lets you reverse ELF binaries. This way we can find that `pinch_me` has a couple of functions, `main`, and `vuln`.

A tip for beginners: use Python PwnTools to make your life easier solving pwn challenges.

In `vuln`, we can see that it has a couple of local variables, one char array of 24 Bytes and two ints. It asks us for the input, which can be up to 100 Bytes, so buffer overflow is an option here. We can exploit that to overwrite one of the integers with `0x1337c0de`, so that `system("/bin/sh")` gets called.

A working solution is in `sol.py`.