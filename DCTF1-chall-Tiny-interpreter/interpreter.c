#include <stdio.h>
#include <string.h>
typedef struct instruction{
    char instr;
    unsigned short args[3];
} instruction;

short regs[8];
short ip;

char* mem;

void set(instruction* ins){
    regs[ins->args[0]] = regs[ins->args[1]];
    ip += 7;
}
void add(instruction* ins){
    regs[ins->args[0]] = regs[ins->args[1]] + regs[ins->args[2]];
    ip += 7;
}
void addi(instruction* ins){
    regs[ins->args[0]] = regs[ins->args[1]] + ins->args[2];
    ip += 7;
}
void sub(instruction* ins){
    regs[ins->args[0]] = regs[ins->args[1]] - regs[ins->args[2]];
    ip += 7;
}
void subi(instruction* ins){
    regs[ins->args[0]] = regs[ins->args[1]] - ins->args[2];
    ip += 7;
}
void mul(instruction* ins){
    regs[ins->args[0]] = regs[ins->args[1]] * regs[ins->args[2]];
    ip += 7;
}
void muli(instruction* ins){
    regs[ins->args[0]] = regs[ins->args[1]] * ins->args[2];
    ip += 7;
}
void div(instruction* ins){
    regs[ins->args[0]] = regs[ins->args[1]] / regs[ins->args[2]];
    ip += 7;
}
void divi(instruction* ins){
    regs[ins->args[0]] = regs[ins->args[1]] / ins->args[2];
    ip += 7;
}
void and(instruction* ins){
    regs[ins->args[0]] = regs[ins->args[1]] & regs[ins->args[2]];
    ip += 7;
}
void andi(instruction* ins){
    regs[ins->args[0]] = regs[ins->args[1]] & ins->args[2];
    ip += 7;
}
void or(instruction* ins){
    regs[ins->args[0]] = regs[ins->args[1]] | regs[ins->args[2]];
    ip += 7;
}
void ori(instruction* ins){
    regs[ins->args[0]] = regs[ins->args[1]] | ins->args[2];
    ip += 7;
}
void xor(instruction* ins){
    regs[ins->args[0]] = regs[ins->args[1]] ^ regs[ins->args[2]];
    // printf("xor: %d = %d ^ %d\n", regs[ins->args[0]], regs[ins->args[1]], regs[ins->args[2]]);
    ip += 7;
}
void xori(instruction* ins){
    regs[ins->args[0]] = regs[ins->args[1]] ^ ins->args[2];
    ip += 7;
}
void not(instruction* ins){
    regs[ins->args[0]] = !regs[ins->args[1]];
    ip += 7;
}
void neg(instruction* ins){
    regs[ins->args[0]] = ~regs[ins->args[1]];
    ip += 7;
}
void sto(instruction* ins){
    mem[regs[ins->args[0]]] = regs[ins->args[1]];
    printf("%c\n", regs[ins->args[1]]);
    ip += 7;
}
void stoi(instruction* ins){
    mem[ins->args[0]] = regs[ins->args[1]];
    ip += 7;
}
void load(instruction* ins){
    regs[ins->args[0]] = mem[regs[ins->args[1]]];
    ip += 7;
}
void loadi(instruction* ins){
    regs[ins->args[0]] = mem[ins->args[1]];
    // printf("load %d: %d\n", ins->args[0], regs[ins->args[0]]);
    ip += 7;
}
void put(instruction* ins){
    putc(regs[ins->args[0]], stdout);
    ip += 7;
}
void read(instruction* ins){
    gets(&mem[ins->args[0]]);
    ip += 7;
}
void jmp(instruction* ins){
    ip = ins->args[0];
}
void jeq(instruction* ins){
    if(regs[ins->args[1]] == regs[ins->args[2]])
        ip = ins->args[0];
    else
        ip += 7;
}
void jne(instruction* ins){
    if(regs[ins->args[1]] == regs[ins->args[2]])
        ip += 7;
    else
        ip = ins->args[0];
}
void stop(instruction* ins){
    //printf("%s\n", &mem[78]);
    exit(ins->args[0]);
}

instruction* readIns(){
    instruction* out = malloc(sizeof(instruction));
    out->instr = mem[ip];
    for(int i = 0; i < 3; i++){
        out->args[i] = mem[ip + 1 + 2*i] + (mem[ip + 2 + 2*i] << 8);
    }
    // printf("%d %d %d %d\n", out->instr, out->args[0], out->args[1], out->args[2]);
    return out;
}

void (*instructions[27])() = {set, add, addi, sub, subi, mul, muli, div, divi, and, andi, or, ori, xor, xori, not, neg, sto, stoi, load, loadi, put, read, jmp, jeq, jne, stop};

void execute(){
    instruction* i;
    while(1){
        i = readIns();
        if(i->instr < 27){
            instructions[i->instr](i);
        } else{
            printf("Illegal instruction.\n");
            exit(0);
        }
    }
}

int main(int argc, char* argv[]){
    if(argc != 2){
        printf("Usage:\n./interpreter <program you want to run>\n");
        return 0;
    }

    long length;
    FILE * f = fopen (argv[1], "rb");

    if (f){
        fseek(f, 0, SEEK_END);
        length = ftell (f);
        fseek(f, 0, SEEK_SET);
        mem = malloc(length);
        if(mem){
            fread (mem, 1, length, f);
        }
        else{
            printf("Not enough memory.\n");
        }
        fclose (f);
    }
    else{
        printf("Unable to open file.\n");
    }
    execute();
}