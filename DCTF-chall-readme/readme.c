#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void vuln(){
    FILE *fp;
    int c;
    char arr[30];
    char name[30];
    fp = fopen("flag.txt","r");
    fgets(arr,28,fp);
    fclose(fp);
    puts("hello, what's your name?");
    fgets(name,30,stdin);
    printf("hello ");
    printf(name);
}




int main(){
    alarm(10);
    vuln();
    return 0;
}
