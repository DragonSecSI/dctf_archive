import sys
import base64

ot = b''

with open("very_important.zip", "rb") as fl:
    with open("encrypted1", "wb") as wf:
        while True:
            byte = fl.read(1)
            if byte == b'':
                break
            ot += int.to_bytes((int.from_bytes(byte, byteorder=sys.byteorder) ^ 0x6A), len(byte), byteorder=sys.byteorder)

        wf.write(base64.encodebytes(ot))