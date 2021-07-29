#include <stdio.h>
#include <string.h>
#include <stdlib.h>






// FLAG
// dctf{df77dbe0c407dd4a188e12013ccb009f}

const int FLAG_LEN = 39;

char *xor_cipher(const char *data, char *key) {
	char *xor_data = malloc (sizeof(char) * FLAG_LEN + 1);

	for (int i = 0; i < FLAG_LEN; i++) {
		xor_data[i] = (data[i] ^ key[i % 7]);
	}
	xor_data[38] = '\0';
	return xor_data;
}

char *get_key(const char *fname) {
	char * key = (char *) malloc(8 * sizeof(char));
	
	FILE *fileptr = fopen(fname, "rb");
	fread(key, 8, 1, fileptr);
	fclose(fileptr);

	key[7] = '\0';
	return key;
}


char *get_dctf(char *buf) {
	char *dctf = (char *) malloc (5 * sizeof (char));

	for (int i = 0; i < 5; i++) {
		dctf[i] = buf[i];
	}
	return dctf;
}




void print_hex(char *s) {
	{
		while(*s) {
			printf("%02x", (unsigned int) *s++);
		}
		printf("\n");
	}
}

void fake_function0(char *buf) {
	int buf_len = strlen(buf);

	for (int i = 0; i < buf_len; i++) {
		buf[i] = 255 % (buf[i] + 0x25);
	}
	buf[0] = buf[0] + 0x25;
	buf[1] = buf[1] + 0x45;
	buf[2] = buf[2] + 0x2f;
	buf[3] = buf[3] + 0x36;
	buf[4] = buf[4] + 0x1a;
	buf[37] = buf[37] + 0x2a;
}


void fake_function1(char *buf) {
	int buf_len = strlen(buf);

	for (int i = 0; i < buf_len; i++) {
		buf[i] = 255 % (buf[i] + 0x30);
	}
}


int main(int argc, char **argv) {

	char *key = (char *) malloc(8 * sizeof(char));
	strncpy(key, get_key(argv[0]), 8);
	const char encrypted[] = "\x1b\x26\x38\x20\x79\x65\x67\x48\x72\x28\x24\x67\x31\x62\x4b\x75\x7b\x22\x66\x35\x60\x4e\x7d\x74\x23\x33\x33\x31\x4e\x76\x2f\x25\x60\x31\x31\x46\x23\x31";
	char *encrypted_fake = (char *) malloc (FLAG_LEN * sizeof (char));
	strncpy(encrypted_fake, encrypted, FLAG_LEN);
	fake_function0(encrypted_fake);
	printf("%s\n", "Decryption finished.");
	char *flag = (char *) malloc(FLAG_LEN * sizeof(char));
	strncpy(flag, xor_cipher(encrypted, key), FLAG_LEN);
	flag = xor_cipher(flag, key);
	fake_function1(flag);

	// freeing malloc pointers
	free(encrypted_fake);
	free(flag);
	free(key);
	return 0;
}
