#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

void vuln() {
    char arr[20];
    int a = 0x01234567;
    int b = 0x89ABCDEF;
    
    puts("Is this a real life, or is it just a fanta sea?");
    puts("Am I dreaming?");
    fgets(arr, 100, stdin);
    if (b == 0x1337c0de) {
        system("/bin/sh");
    } else if (a != 0x01234567) {
        puts("Pinch me harder!");
    } else {
        puts("Pinch me!");
    }
}

int main() {
    alarm(10);
    vuln();
    return 0;
}