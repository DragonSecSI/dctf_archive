#include <stdio.h>
#include <stdlib.h>
#include <time.h>

//dctf{1_gu3ss_1t_wasnt_h1dden_w33l_enough}
unsigned char text[1516] = "What the fuck did you just fucking say about me, you little bitch? I'll have you know I graduated top of my class in the Navy Seals, and I've been involved in numerous secret raids on Al-Quaeda, and I have over 300 confirmed kills. I am trained in gorilla warfare and I'm the top sniper in the entire US armed forces. You are nothing to me but just another target. I will wipe you the fuck out with precision the likes of which has never been seen before on this Earth, mark my fucking words. You think you can get away with saying that shit to me over the Internet? Think again, fucker. As we speak I am contacting my secret network of spies across the USA and your IP is being traced right now so you better prepare for the storm, maggot. The storm that wipes out the pathetic little thing you call your life. You're fucking dead, kid. I can be anywhere, anytime, and I can kill you in over seven hundred ways, and that's just with my bare hands. Not only am I extensively trained in unarmed combat, but I have access to the entire arsenal of the United States Marine Corps and I will use it to its full extent to wipe your miserable ass off the face of the continent, you little shit. If only you could have known what unholy retribution your little \"clever\" comment was about to bring down upon you, maybe you would have held your fucking tongue. But you couldn't, you didn't, and now you're paying the price, you goddamn idiot. I will shit fury all over you and you will drown in it. You're fucking dead, kiddo.";
unsigned char off[328] = {7, 6, 5, 7, 4, 6, 7, 0, 7, 5, 0, 4, 2, 7, 1, 5, 7, 5, 6, 4, 4, 3, 7, 7, 1, 1, 5, 4, 4, 3, 5, 1, 7, 0, 5, 0, 6, 2, 3, 5, 4, 4, 3, 0, 7, 0, 6, 5, 7, 1, 4, 4, 0, 5, 5, 1, 7, 6, 5, 7, 3, 1, 6, 5, 1, 0, 4, 5, 0, 6, 0, 1, 6, 3, 0, 6, 4, 0, 5, 5, 3, 1, 5, 2, 4, 3, 6, 5, 7, 2, 5, 1, 6, 3, 2, 3, 3, 6, 2, 6, 6, 5, 5, 3, 1, 0, 2, 6, 1, 1, 0, 6, 7, 5, 3, 5, 3, 6, 4, 7, 7, 6, 7, 2, 5, 5, 3, 2, 0, 2, 1, 4, 4, 5, 2, 0, 4, 5, 5, 7, 4, 2, 7, 
2, 0, 0, 5, 6, 1, 4, 5, 5, 4, 6, 0, 1, 5, 5, 6, 3, 4, 6, 6, 6, 0, 5, 3, 7, 1, 3, 6, 6, 6, 5, 5, 5, 3, 1, 5, 3, 5, 7, 4, 1, 3, 4, 5, 6, 0, 1, 4, 3, 3, 6, 2, 6, 4, 5, 3, 7, 0, 5, 0, 2, 0, 1, 7, 7, 4, 1, 5, 3, 1, 5, 4, 1, 7, 1, 3, 1, 5, 5, 3, 0, 7, 5, 2, 6, 0, 5, 5, 5, 3, 5, 5, 0, 0, 0, 5, 5, 4, 7, 6, 5, 2, 2, 1, 0, 1, 7, 5, 2, 1, 0, 5, 0, 1, 3, 5, 7, 3, 6, 6, 2, 7, 1, 1, 1, 6, 5, 5, 4, 7, 0, 5, 4, 3, 0, 4, 5, 7, 5, 4, 0, 5, 6, 6, 7, 7, 6, 0, 7, 0, 5, 3, 3, 1, 5, 0, 5, 1, 3, 3, 5, 0, 5, 5, 3, 6, 0, 1, 6, 1, 5, 5, 2, 5, 7, 7, 2, 7, 4, 2, 5, 1, 6, 4, 0};

void printFlag(){
    printf("I hid the flag, what do you expect to find here?\n");
    // char* ptr = text;
    // int x = 1337;
    // unsigned char mask;
    // int bit;
    // for(int i = 0; i < 41*8; i++){
    //     mask = 1 << off[i];
    //     x = ((947 + x * 811) % 1516);
    //     bit = (text[x] & mask) != 0;
    //     printf("%d", bit);
    // }
}

void hideFlag(){
    srand(time(NULL));
    char* ptr = text;
    int r;
    int x = 1337;
    unsigned char mask;
    int bit;
    for(int i = 0; i < 41*8; i++){
        r = rand();
        mask = (-1 >> (8 - off[i]) | r) & 1 << off[i];
        x = ((947 + x * 811) % 1516);
        text[x] = text[x] ^ mask;
    }
}

int main(){
    hideFlag();
    printFlag();
}