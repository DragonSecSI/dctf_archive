#include <stdio.h>
#include <stdlib.h>
#include <time.h>

long triangle(int line, int i) {
    if (i > line) {
        return 0;
    } else if (line == 1 && i == 1) {
        return 1;
    } else if (i == 1) {
        return triangle(line-1, line-1);
    } else {
        return (triangle(line, i-1) + triangle(line-1, i-1));
    }
}

int process(int line) {
    int flag = 1;
    long value;
    long entry;
    for (int i = 1; i <= line; i++) {
        value = triangle(line, i);
        scanf("%ld", &entry);
        if (entry != value) {
            flag = 0;
        }
    }    
    if (flag == 1) {
        system("cat flag.txt");
    } else {
        puts("Better luck next time.");
    }
    return 0;
}

int main() {
    srand(time(NULL));
    int line = 8 + rand() % 5;
    printf("%d\n", line);
    process(line);
    return 0;
}