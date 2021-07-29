#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int encrypt(char flag[], long long vector[]) {
    for (int i = 5; i < strlen(flag)-1; i++) {
        int mic = vector[i % 3] % 95;
        
        int chr = flag[i];
        chr += mic;
        if (chr > 126) {
            chr -= 95;
        }
        flag[i] = chr;

    }
    return 0;
}

long long scalar(long long v1[], long long v2[]) {
    long long sum = 0;
    for (int i = 0; i < 3; i++) {
        sum += v1[i] * v2[i];
    }
    return sum;
}

int multiplication(long long M[3][3], long long vector[]) {
    long long result[3];
    for (int i = 0; i < 3; i++) {
        result[i] = scalar(M[i], vector);
    }

    for (int i = 0; i < 3; i++) {
        vector[i] = result[i];
    }

    return 0;     
}

int A(long long M[3][3]) {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            long long v = M[i][j];
            if (j == 1 && v > 0) {
                M[i][j] *= -1;
            } else if (j != 1 && v < 0) {
                M[i][j] *= -1;
            }
        }
    }
    return 0;
}

int B(long long M[3][3]) {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            long long v = M[i][j];
            if (v < 0) {
                M[i][j] *= -1;
            }
        }
    }
    return 0;
}

int C(long long M[3][3]) {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            long long v = M[i][j];
            if (j == 0 && v > 0) {
                M[i][j] *= -1;
            } else if (j != 0 && v < 0) {
                M[i][j] *= -1;
            }
        }
    }
    return 0;
}

int process(int times, char flag[]) {
    long long pybase[] = {3, 4, 5};
    int kek = times;
    long long* vector;
    vector = pybase;

    long long M[3][3] = {
       {1, 2, 2},
       {2, 1, 2},
       {2, 2, 3}
    };
    
    int buffer;
    FILE* fd = fopen("/dev/urandom", "r");
    fread(&buffer, sizeof(int), 1, fd);
    fclose(fd);


    srand(buffer);
    while (times > 0) {
        int r = rand() % 3;  
        if (r == 0) {
            A(M);
        } else if (r == 1) {
            B(M);
        } else {
            C(M);
        }
        multiplication(M, vector);
        encrypt(flag, vector);
        times--;               
    }
    printf("%s\n", flag);
    printf("(%llu, %llu, %llu)\n", vector[0], vector[1], vector[2]);
    
    return 0;

}

int main() {
    alarm(2);
    char flag[] = "dctf{x_p3de_h3rc00lem}";
    int times = 25;
    process(times, flag);
    return 0;
}
