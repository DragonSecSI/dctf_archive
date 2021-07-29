#include <stdio.h>
#include <stdlib.h>

void win(int a, int b){
    puts("you made it to win land, no free handouts this time, try harder");
    if (a == 0xdeadbeef){
        puts("one down, one to go!");
        if (b == 0x1337c0de){
            puts("2/2 bro good job");
            system("/bin/sh");
            exit(0);
        }
    }
}
void shell(){
	puts("spawning /bin/sh process");
	puts("wush!");
	printf("$> ");
	puts("If this is not good enough, you will just have to try harder :)");
}

void vuln(){
    char arr[50];
    int passcode;
    puts("tell me a joke");  
    fgets(arr,0x100,stdin);
    if (passcode == 0xdeadc0de){
        puts("very good, here is a shell for you. ");
	    shell();
    }else{
        puts("will this work?");
    }
}

int main(){
    alarm(10);
    vuln();
    return 0;
}
