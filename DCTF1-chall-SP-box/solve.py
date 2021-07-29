from string import ascii_letters, digits
from math import ceil, log
from pwn import remote

def decrypt(message):
    global SboxR
    message = list(message)
    rounds = int(2 * ceil(log(len(message), 2))) 
    x = int(len(message)/2)
    #S_boxR = {v : k for k, v in S_box.items()}
    for round in range(rounds):
        message = [SboxR[c] for c in message]
        
        if round < (rounds-1):
            tmp1 = message[:x]
            tmp2 = message[x:]
            for i in range(len(message)):
                if i % 2 == 1: message[i] = tmp1[int(i/2)]
                if i % 2 == 0: message[i] = tmp2[int(i/2)]
    return ''.join(message)


def find(x):
    for k in ALPHABET:
        if not any(k in a for a in cycles) and k != x: return k


def collapse(h):
    global SboxR
    l = len(h) // 2 + 1
    for ii in range(len(h)):
        SboxR[h[(ii+l) % len(h)]] = h[ii]

def join(a, b, off):
    global SboxR
    l = len(a)
    for ii in range(l):
        SboxR[b[(ii+off) % l]] = a[ii]
        SboxR[a[(ii-off+1) % l]] = b[ii]

ALPHABET = ascii_letters + digits + "_!@#$%.'\"+:;<=}{"

conn = remote("localhost", 8888)
ret = conn.recvuntil(">").decode("utf-8")
print(ret)
cipher = ret.split("\n")[1]
cycles = list()
li = 0
ri = 1
l = find("")
r = find(l)
if r == None: r == "_"
cycles.append(list(l))
cycles.append(list(r))
right = True
left = True
while left or right:
    conn.sendline(r+l)
    ret = conn.recvuntil(">").decode("utf-8")
    print(ret)
    tmp = ret.split("\n")[1]
    r = tmp[1]
    l = tmp[0]
    if l in cycles[li]:
        l = find(r)
        if l == None: 
            l = "_"
            left = False
        cycles.append(list(l))
        li = len(cycles) - 1
    else: cycles[li].append(l)
    if r in cycles[ri]:
        r = find(l)
        if r == None: 
            r = "_"
            right = False
        cycles.append(list(r))
        ri = len(cycles) - 1
    else: cycles[ri].append(r)

for f in cycles:
    for s in cycles:
        if f != s and f in cycles and s in cycles and set(f).intersection(set(s)) != set():
            cycles.remove(s if len(s) < len(f) else f)
            break

SboxR = {}
dupes = []
for i in range(len(cycles)-1):
    for j in range(i+1, len(cycles)):
        if len(cycles[i]) == len(cycles[j]):
            dupes.append([i, j, len(cycles[i])])

for h in cycles:
    collapse(h)
lens = [len(a) for a in cycles]
dupeLens = set([x for x in lens if lens.count(x) > 1])
prod = 1
for d in dupeLens: prod = prod * d
if len([x for x in lens if lens.count(x) > 2]) > 0:
    print("no go")
    exit()

dupNum = len(dupes)
offsets = [0]*(dupNum+1)
for i in range(prod):
    for k in range(dupNum):
        join(cycles[dupes[k][0]], cycles[dupes[k][1]], offsets[k])
    plain = decrypt(cipher)
    conn.sendline(plain)
    ret = conn.recvline().decode("utf-8")
    print(ret)
    if "flag" in ret:
        print(conn.recvline().decode("utf-8")) 
        break
    ret = conn.recvuntil(">").decode("utf-8")
    print(ret)
    offsets[0] += 1
    for k in range(dupNum):
        if offsets[k] >= dupes[k][2]:
            offsets[k] = 0
            offsets[k+1] += 1

