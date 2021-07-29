#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
void vuln(){
    char surname[100];

    while(1){
        puts("I won't ask you, what your name is. It's getting kinda old at this point");
        scanf("%100s",surname);
        puts("you entered");
        printf(surname);
        puts("");
        puts("");
    }
	

}

int main(){
	alarm(10);
    vuln();
    return 0;
}
