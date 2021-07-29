#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

char win_land[8];
int len = 0;

void loss(int a, int b) {
    if ((a + b) == 0xdeadc0de) {
        puts("Dis is da wae to be one of our finest guests!");
        if (a == 0x1337c0de) {
            puts("Now you can replace our manager!");
            //printf("%s\n", win_land);
            system(win_land);
            exit(0);
        }
    }
    return;
}

void california() {
    puts("Welcome to Hotel California");
    puts("You can sign out anytime you want, but you can never leave");

    win_land[len] = 0x2f;
    len++;
    win_land[len] = 0x62;
    len++;
    win_land[len] = 0x69;
    len++;
    win_land[len] = 0x6e;
    len++;

    return;
}

void silicon_valley() {
    puts("You want to work for Google?");
    
    win_land[len] = 0x2f;
    len++;
    win_land[len] = 0x73;
    len++;
    win_land[len] = 0x68;
    len++;
    win_land[len] = 0x00;
    len++;

    return;
}

void vuln() {
    char arr[20];
    int ans;
    puts("You come here often?");
    fgets(arr, 0x100, stdin);
    if (ans) {
        puts("I think you should come here more often.");
    } else {
        puts("Oh! You are already a regular visitor!");
    }
    return;
}

int main() {
    alarm(10);
    printf("Welcome to Hotel ROP, on main street %p\n", &main);
    vuln();
    return 0;
}