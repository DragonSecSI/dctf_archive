#!/bin/bash
chown pilot:pilot /app/pinch_me
chmod +x /app/pinch_me
while true; do
    su pilot -c 'timeout -k 30s 1d socat TCP-LISTEN:7480,nodelay,reuseaddr,fork EXEC:"stdbuf -i0 -o0 -e0 ./pinch_me"'
done