from random import randint
import json
from pwn import remote


class lsfr:
    def __init__(self, state, coefs):
        self.state = state
        self.coefs = coefs

    def next(self):
        n = sum([self.state[i] * self.coefs[i] for i in range(10)]) % 5039
        self.state = self.state[1:] + [n]
        return n

def unique(n):
    return len(set("%04d" % n)) == 4

def next_guess(ret):
    global subtree, nums
    if ret == "first":
        guess = [a for a in tree.keys()][0]
        subtree = tree[guess]
        return guess
    else:
        if sum(ret) > 4:
            nums = [str(i) for i in range(10) if sum(ret) & 1 << i != 0]
            pfx = "".join([str(i) for i in range(3) if str(i) in nums])
            pfx = "-1" if len(pfx) == 0 else pfx
            lookup = "(%d, %s)" % (ret[0], pfx)
        else: lookup = str(ret)
        subtree = subtree[lookup]
        if type(subtree) is dict:
            guess = [a for a in subtree.keys()][0]
            subtree = subtree[guess]
        else: guess = subtree
        return guess.replace("A", nums[3]).replace("B", nums[2]).replace("C", nums[1]).replace("D", nums[0])

def predict(arr):
    mat = [arr[i:i+10] for i in range(10)]
    vec = arr[10:]
    for i in range(10):
        j = i
        while mat[j][i] == 0:
            j += 1
            if j >= 10: 
                print("not solvable")
                return False
            if mat[j][i] != 0: 
                mat[j], mat[i] = mat[i], mat[j]
                vec[j], vec[i] = vec[i], vec[j]
                break

        q = pow(mat[i][i], -1, 5039)
        for j in range(i, 10): mat[i][j] = (mat[i][j] * q) % 5039
        vec[i] = (vec[i] * q) % 5039
        for ix in range(i+1, 10):
            q = mat[ix][i]
            vec[ix] = (vec[ix] - vec[i] * q) % 5039
            for jx in range(i, 10):
                mat[ix][jx] = (mat[ix][jx] - mat[i][jx] * q) % 5039
    for i in range(9, -1, -1):
        for j in range(i):
            vec[j] = (vec[j] - vec[i] * mat[j][i]) % 5039
    return lsfr(arr[10:], vec)



def play():
    correct_pins = []
    i = 0
    conn = remote("localhost", 7777)
    recv = conn.recvuntil(">").decode("utf-8")
    print(recv)
    

    for _ in range(20):
        guess = next_guess("first")
        conn.sendline(guess)
        i += 1
        recv = conn.recvuntil(">").decode("utf-8")
        print(recv)
        while "Wrong" in recv:
            a = int(recv[recv.index("A") + 1])
            b = int(recv[recv.index("B")+1:recv.index("\n")])
            guess = next_guess((a,b))
            conn.sendline(guess)
            i += 1
            recv = conn.recvuntil(">").decode("utf-8")
            print(recv)
        correct_pins.append(all.index(guess))
    if i > 80:
        print("too many guesses")
        exit()
    rng = predict(correct_pins)
    for _ in range(179):
        conn.sendline(all[rng.next()])
        recv = conn.recvuntil(">").decode("utf-8")
        print(recv)
    conn.sendline(all[rng.next()])
    recv = conn.recvline().decode("utf-8")
    print(recv)

with open("./tree.json") as t:
    tree = json.load(t)

subtree = tree
nums = []

all = ["%04d" % n for n in range(10000) if unique(n)]
play()