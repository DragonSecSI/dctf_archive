#include <stdio.h>
#include <stdlib.h>


void vuln(){
    char arr[10];
    puts("plz don't rop me");  
    fgets(arr,0x100,stdin);
    puts("i don't think this will work");
}


int main(){
    alarm(10);
    vuln();
    return 0;
}
