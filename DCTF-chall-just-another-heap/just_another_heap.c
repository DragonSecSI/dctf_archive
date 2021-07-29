#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>


int num_of_memories=0;
int is_recent_arr[10];
int is_important_arr[10];

u_int64_t offsets[10] = {0};
char * memories[10] = {NULL};
int size[10] = {0};

u_int64_t errnum = 42;
char * names[10];


void menu(){
	puts("[1] Create memory");
	puts("[2] Relive memory");
	puts("[3] Forget memory");
	puts("[4] Change your memory");
	puts("[5] List memories");
	puts("[6] Exit");
}

void read_long(u_int64_t * buff){
	printf("> ");
	scanf("%lu",buff);
	getc(stdin);
	fflush(stdin);
}

void read_int(int * buff){
	printf("> ");
	scanf("%d",buff);
	getc(stdin);
	fflush(stdin);
}

void read_string(char * buf, u_int64_t size){
	printf("> ");
	fgets(buf,size,stdin);
	// fflush(stdin);
}


void create_memory(){
	if(num_of_memories < 10){

		u_int64_t input;
		u_int64_t offset;
		char important[2];
		char recent[2];
		char * memory;
		int is_important=0;
		int is_recent=0;
		u_int64_t index= 0;
		
		printf("at what page would you like to write?\n");
		read_long(&index);

		if(memories[index] != NULL || index > 9){
			printf("there is already something written at that page.\n");
			return;
		}

		printf("name:\n");

		names[index] = (char*) malloc(0x20);
		read_string(names[index],16);
		names[index][strcspn(names[index], "\n")] = 0;

		printf("How long is your memory\n");
		
		read_long(&input);

		memory = (char *) malloc(input);

		puts("Sometimes our memories fade and we only remember parts of them.");
		read_long(&offset);
		puts("Would you like to leave some space at the beginning in case you remember later?");

		if((input < 0) || (offset > input)){
			puts("Invalid offset");
			return;
		}
		if(memory != NULL){
			for(int i = 0; i< offset; ++i){
				strncpy(memory+i,"_",1);
			}
		}

		memory += offset;

		fflush(stdin);
		printf("What would you like to write\n");
		read_string(memory,input-offset);


		printf("Would you say this memory is important to you? [Y/N]\n");
		read_string(important, 2);

		if(!strncmp(important, "Y",1)){
			is_important =1;
		
		}
		getc(stdin);

		printf("Would you say this memory was recent? [Y/N]\n");
		read_string(recent,2);
		if(!strncmp(recent,"Y",1)){
			is_recent=1;
		}

		memories[index] = memory;
		is_recent_arr[index] = is_recent;
		is_important_arr[index] = is_important;
		offsets[index] = offset;
		size[index] = input;
		num_of_memories++;

		printf("Memory created successfully\n\n");
	}else{
		printf("You already have to many memories stored in here. You don't want another one.\n");
		return;
	}
	puts("");
	fflush(stdin);


}


void forget_memory(){
	int index;
	printf("Which memory would you like to forget?\n");
	read_int(&index);

	if(memories[index] == NULL || index > 9 || index < 0){
		printf("Nothing there.\n");
		return;
	}

	free(memories[index] - offsets[index]);


	memories[index] = NULL;
	is_important_arr[index] = 0;
	is_recent_arr[index] = 0;

	offsets[index] = 0;
	size[index] = 0;

	num_of_memories--;

	fflush(stdin);
	puts("");

}


void relive_memory(){
	int index;

	printf("which memory would you like to relive?\n");
	read_int(&index);

	if(memories[index] == NULL || index > 9 || index <0){
		printf("Nothing there.\n");
		return;
	}

	printf("%s\n",memories[index]-offsets[index]);
	fflush(stdin);

	puts("");
}



void change_memory(){
	int index;
	int start=0;
	char beginning[2];


	printf("which memory would you like to change?\n");
	read_int(&index);

	if(memories[index] == NULL || index > 9 || index <0){
		printf("Nothing there.\n");
		return;
	}

	if(offsets[index] > 0){

		printf("I see that you have left some space when first writing this memory.\n");
		printf("Want to start at the begining? [Y/N]\n");
		read_string(beginning,2);

		if(!strncmp(beginning,"Y",1)){
			start = 1;
		}

		getc(stdin);
		printf("What would you like to write? \n");

		if(start){
			int a = read(0,memories[index]-offsets[index],(size_t)size[index]);
			strncpy(memories[index]-offsets[index]+a-1," ",1);
		}else{
			int b = read(0,memories[index],(size_t)((u_int64_t)size[index]-offsets[index]));
			strncpy(memories[index]+b-1," ",1);
		}

		fflush(stdin);

		printf("Changed, successfully\n");
		puts("");

	}

}
void list_memories(){
	char important[] ={"[ IMPORTANT ]  "};
	char recent[] ={"[ RECENT ] "};
	for(int i =0; i< 10; ++i){	
		if(memories[i] != NULL){
			printf("%d: %s ",i,names[i]);

			if(is_recent_arr[i])
				printf("%s",recent);
			if(is_important_arr[i])
				printf("%s",important);
			puts("");
		}
	}
	puts("");
	fflush(stdin);
}


int main(){
	puts(
	"________  _______________________________ _______________   ________  ____\n" 
	"\\______ \\ \\_   ___ \\__    ___/\\_   _____/ \\_____  \\   _  \\  \\_____  \\/_   |\n"
	"|    |  \\/    \\  \\/ |    |    |    __)    /  ____/  /_\\  \\  /  ____/ |   |\n"
	"|    `   \\     \\____|    |    |     \\    /       \\  \\_/   \\/       \\ |   |\n"
	"/_______  /\\______  /|____|    \\___  /    \\_______ \\_____  /\\_______ \\|___|\n"
	"	\\/        \\/               \\/             \\/     \\/         \\/  \n");
	
	
	alarm(0xa);
	while(1){
		int input;
		menu();
		read_int(&input);
		switch(input){
			case 1:
				create_memory();
				break;
			case 2:
				relive_memory();
				break;
			case 3:
				forget_memory();
				break;
			case 4:
				change_memory();
				break;
			case 5:
				list_memories();
				break;
			case 6:
				exit(errnum);
				break;
			default:
				printf("%d is invalid option!\n",input);
				exit(errnum);
		}
	}
	return 0;
}

