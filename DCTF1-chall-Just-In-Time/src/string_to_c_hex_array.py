#!/usr/bin/env python3
import binascii

# encrypted string:
# 1b263820796567487228246731624b757b226635604e7d74233333314e762f25603131462331
hex_string = "1b263820796567487228246731624b757b226635604e7d74233333314e762f25603131462331"

print("const char encrypted[] = \"", end="")
def print_c_hex_array(hex_string):
	for i in range(0, len(hex_string), 2):
		print("\\x" + hex_string[i] + hex_string[i+1], end="")
	print("\";")


def main():
	print_c_hex_array(hex_string)

if __name__ == "__main__":
	main()