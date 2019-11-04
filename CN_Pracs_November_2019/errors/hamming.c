#include <stdio.h>
#include <stdlib.h>

#define EVEN_PARITY 0
#define ODD_PARITY  1

// Pre-compilation setting for parity
#define PARITY_SETTING EVEN_PARITY

// Bits are just integers
typedef int bit;

// Given an array of bits, convert to hamming code
bit* toHamming(bit input[]) {
    bit* hamming = (bit*) malloc(sizeof(bit) * 11);
    // Remember, index - 1

    // Parity bits 1, 2, 4, 8 => Positions 0, 1, 3, 7
    // hamming[0] = Parity bit 1
    // hamming[1] = Parity bit 2
    hamming[2]  = input[0];
    // hamming[3] = Parity bit 4
    hamming[4]  = input[1];
    hamming[5]  = input[2];
    hamming[6]  = input[3];
    // hamming[7] = Parity bit 8
    hamming[8]  = input[4];
    hamming[9]  = input[5];
    hamming[10] = input[6];
    
    // Compute parity bits.
    hamming[0] = PARITY_SETTING + hamming[2] + hamming[4] + hamming[6] + hamming[8] + hamming[10];
    hamming[1] = PARITY_SETTING + hamming[2] + hamming[5] + hamming[6] + hamming[9] + hamming[10];
    hamming[3] = PARITY_SETTING + hamming[4] + hamming[5] + hamming[6];
    hamming[7] = PARITY_SETTING + hamming[8] + hamming[9] + hamming[10];

    hamming[0] = (hamming[0] + PARITY_SETTING) % 2;
    hamming[1] = (hamming[1] + PARITY_SETTING) % 2;
    hamming[3] = (hamming[3] + PARITY_SETTING) % 2;
    hamming[7] = (hamming[7] + PARITY_SETTING) % 2;

    return hamming;
}

// Given a hamming code, return position of error
int correctError(bit hamming[11]) {
    int p[4] = { 0, 0, 0, 0 };

    p[0] = hamming[0] + hamming[2] + hamming[4] + hamming[6] + hamming[8] + hamming[10];
    p[1] = hamming[1] + hamming[2] + hamming[5] + hamming[6] + hamming[9] + hamming[10];
    p[2] = hamming[3] + hamming[4] + hamming[5] + hamming[6];
    p[3] = hamming[7] + hamming[8] + hamming[9] + hamming[10];

    p[0] = (p[0] + PARITY_SETTING) % 2;
    p[1] = (p[1] + PARITY_SETTING) % 2;
    p[2] = (p[2] + PARITY_SETTING) % 2;
    p[3] = (p[3] + PARITY_SETTING) % 2;

    int pos = (8 * p[3]) + (4 * p[2]) + (2 * p[1]) + (1 * p[0]);
    return pos;
}

// Convert array of char to array of bits
bit* ca2ba(char s[], int size) {
    bit* bitArray = (bit*) malloc(sizeof(bit) * size);
    for(int i = 0; i < size; i++) {
        bitArray[size - i - 1] = s[i] - '0';
    }
    return bitArray;
}

int main() {
    printf("***** HAMMING CODE BY VIKRANT *****\n\n");

    // --------------------------------------------------

    printf("~~~~~ GENERATION ~~~~~\n");
    
    char s[7];
    printf("Enter a binary string of length 7 > ");
    scanf("%s", s);

    bit* inputWord = ca2ba(s, 7);
    bit* hamming = toHamming(inputWord);
    for(int i = 10; i >= 0; i--) {
        printf("%d", hamming[i]);
    }
    printf("\n");

    // --------------------------------------------------

    printf("\n~~~~~ CORRECTION ~~~~~\n");

    int inv = -1;
    printf("Enter index to invert [1-11] > ");
    scanf("%d", &inv);

    if(1 <= inv && inv <= 11) {
        hamming[inv - 1] = !hamming[inv - 1];
    }

    int pos = correctError(hamming);
    if(pos == 0) {
        printf("No error in received code.\n");
    } else {
        printf("Error at pos %d\n", pos);
    }
}
