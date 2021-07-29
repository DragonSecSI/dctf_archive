cipher = open("cipher.txt", "r").read().split()

letters = [{c[i] for c in cipher} for i in range(len(cipher[0]))]

flag = ""

for letter in letters:
    for char in "ABCDEFGHIJKLMNOPQRSTUVWXYZ":
        if char not in letter: flag = flag + char

print(flag)