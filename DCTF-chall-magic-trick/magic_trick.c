#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void win(){
	puts("You are a real magician");
	system("cat flag.txt");
	exit(1);
}



void magic(){
	long long unsigned int in1;
	long long unsigned int in2;
	long long unsigned int * where;
	puts("What do you want to write");
	scanf("%llu",&in1);
	puts("Where do you want to write it");
	scanf("%llu",&in2);
	puts("thanks");
	where = in2;
	*where = in1;


}


int main(){
	alarm(0xa);
	puts("How about a magic trick?");
	puts("");
	magic();
	return 0;
}
