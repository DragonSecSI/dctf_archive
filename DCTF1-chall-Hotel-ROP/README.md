# DCTF1-chall-Hotel_ROP

A pwn challange by AlwaysGuilty.

Competitors get binary file `hotel_rop`, IP, and port of the server it is running on.\
The challenge is dockerized. 

## Compilation

Compile source with `gcc -pie -o <path/to/hotel_rop> <path/to/Hotel_ROP.c>`

## Protection

Binary is not stripped and has PIE, NX and partial RELRO enabled.

## Solution

Upon reversing the Elf file, we find functions `main`, `vuln`, `california`, `silicon_valley`, and `loss`. The last three are dead code, they never get called.

The vulnerability is obvious; we can do a buffer overflow in `vuln` with `fgets`. What else can we find?\
Functions `california` and `silicon_valley` put some bytes in a global char array. Looks like they fill that array with `"/bin/sh\0"`. Taking a look at function `loss`, we see that we need to call it with right arguments, so that it can call `system` with `"/bin/sh\0"` as the argument.

This is the intended solution to this challenge. The alternative is a little bit simpler, we do a ROP chain\
`vuln -> california -> silicon_valley -> system`, ignoring `loss` arguments. 

Because PIE is enabled, we need to calculate all the function addresses first, knowing only their offsets (last byte and a half), and address of `main`.

Payload should then look something like this:
* 40 bytes of padding, for example 40 A's
* Address of `california`
* Address of `silicon_valley`
* `pop rdi ret` ROP gadget and `0x1337c0de` for the argument
* `pop rsi r15 ret` ROP gadget with `0xcb760000` and `0x0` for the arguments.
* Adress of `loss`

This way we can set up `loss` call with correct arguments, resulting in shell. 

`sol.py` contains a working solution.