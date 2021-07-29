from Crypto.Cipher import DES3
from time import time
from pwn import remote

def decoder(c_time: int, ciphertext: str):
    key = str(c_time).zfill(16).encode("utf-8")
    cipher = DES3.new(key, DES3.MODE_CFB, b"00000000")

    return cipher.decrypt(bytes.fromhex(ciphertext))

conn = remote("dctf-chall-just-take-your-time.westeurope.azurecontainer.io", 9999)

conn.recvline()
quest = conn.recvline().decode("utf-8").replace(" ", "").replace("=", "").split("*")
# solve the trivial challenge
conn.sendline(str(int(quest[0]) * int(quest[1])))
conn.recvline()

ciphertext = conn.recvline().decode("utf-8")
# get current time, which is the encryption key
curr = int(time())

conn.sendline(decoder(curr, ciphertext))
x = conn.recvline().decode("utf-8")
print(x.rstrip())

if "wrong" in x:
    conn.sendline(decoder(curr - 1, ciphertext))
    x = conn.recvline().decode("utf-8")
    print(x.rstrip())

else:
    x = conn.recvline().decode("utf-8")
    print(x.rstrip())
    exit(0)

if "wrong" in x:
    conn.sendline(decoder(curr + 1, ciphertext))
    x = conn.recvline().decode("utf-8")
    print(x.rstrip())

else:
    x = conn.recvline().decode("utf-8")
    print(x.rstrip())
    exit(0)

x = conn.recvline().decode("utf-8")
print(x.rstrip())
