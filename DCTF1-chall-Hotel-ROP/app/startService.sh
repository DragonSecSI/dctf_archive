#!/bin/bash
chown pilot:pilot /app/hotel_rop
chmod +x /app/hotel_rop
while true; do
    su pilot -c 'timeout -k 30s 1d socat TCP-LISTEN:7480,nodelay,reuseaddr,fork EXEC:"stdbuf -i0 -o0 -e0 ./hotel_rop"'
done