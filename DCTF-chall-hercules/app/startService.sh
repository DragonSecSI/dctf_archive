#!/bin/bash
chown pilot:pilot /app/hercules
chmod +x /app/hercules
while true; do
	su pilot -c 'timeout -k 30 1d socat TCP-LISTEN:5569,nodelay,reuseaddr,fork EXEC:"stdbuf -i0 -o0 -e0 ./hercules"'
done
