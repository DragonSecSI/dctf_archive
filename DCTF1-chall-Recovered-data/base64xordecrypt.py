import sys
import base64

with open("encrypted1", "rb") as fl:
    with open("flag.zip", "wb") as wf:
        data = base64.decodebytes(fl.read())
        for byte in data:
            if byte == b'':
                break
            wf.write((byte ^ 0x6A).to_bytes(1, byteorder=sys.byteorder))