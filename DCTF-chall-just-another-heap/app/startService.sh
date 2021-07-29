#!/bin/bash
chown pilot:pilot /app/just_another_heap
chmod +x /app/just_another_heap
while true; do
	su pilot -c 'timeout -k 30 1d socat TCP-LISTEN:7481,nodelay,reuseaddr,fork EXEC:"stdbuf -i0 -o0 -e0 ./just_another_heap"'
done
