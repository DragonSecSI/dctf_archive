from base64 import b64encode
from secret import flag
flag = str.encode(flag)
for _ in range(42):
    flag = b64encode(flag)
print(flag.decode())