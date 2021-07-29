#!/bin/bash
chown pilot:pilot /app/bell
chmod +x /app/bell
while true; do
	su pilot -c 'timeout -k 30 1d socat TCP-LISTEN:5311,nodelay,reuseaddr,fork EXEC:"stdbuf -i0 -o0 -e0 ./bell"'
done
