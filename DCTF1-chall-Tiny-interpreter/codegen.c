#include <stdio.h>
//dctf{Interpreter_written_in_C_is_a_great_idea}
char arr[] = {
 20, 0, 0, 77, 0, 0, 0,  //load 0, #x
 4, 1, 0, 0, 0, 1, 0,  
 2, 2, 0, 0, 0, 39, 0,  

 19, 3, 0, 0, 0, 0, 0,
 19, 4, 0, 1, 0, 0, 0,
 13, 5, 0, 3, 0, 4, 0,
 17, 0, 0, 5, 0, 0, 0,

 2, 0, 0, 0, 0, 1, 0,   //++
 2, 1, 0, 1, 0, 1, 0,  //++
 24, 21, 0, 1, 0, 2, 0,
 25, 0, 0, 0, 0, 0, 0,
 83, 'd', 'c', 't', 'f', '{', 
 50, 39, 26, 17, 23, 2, 2, 23, 17, 17, 23, 45, 40, 5, 27, 29, 0, 17, 11, 49, 54, 7, 49, 28, 28, 54, 26, 44, 62, 62, 56, 21, 23, 4, 21, 43, 54, 13, 1, 4, '}'
  };

int main(){
    FILE* f = fopen("bin", "wb");
    for(int i = 0; i < 124; i++){
        fputc(arr[i], f);
        //printf("%d: %d\n", i, arr[i]);
    }
}