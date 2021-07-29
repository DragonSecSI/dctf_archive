#!/bin/bash
chown pilot:pilot /app/readme
chmod +x /app/readme
while true; do
	su pilot -c 'timeout -k 30s 1d socat TCP-LISTEN:7481,nodelay,reuseaddr,fork EXEC:"stdbuf -i0 -o0 -e0 ./readme"'
done
