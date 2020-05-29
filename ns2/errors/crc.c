#include <stdio.h>
#include <math.h>

// Bit is just int
typedef int bit;

// Convert integer to bit array
bit* i2ba(int x, int size) {
    bit* n = (bit*) malloc (sizeof(bit) * size);
    for(int s = size - 1; s >= 0; s--) {
        n[s] = x % 2;
        x /= 2;
    }
    return n;
}

// Convert string of 0s and 1s to integer
int ca2i(char* input, int size) {
    int x = 0;
    for(int i = 0; i < size; i++)
        x += (int) pow(2, size - i - 1) * (input[i] - '0');
    return x;
}

// Convert data len 6 and key len 4 strings to give CRC form of data
bit* toCrc(char* sdata, char* skey) {
    int data = ca2i(sdata, 6);
    int key = ca2i(skey, 4);

    int rem = data % key;
    printf("DATA %d KEY % d REM %d\n", data, key, rem);

    data <<= 3;
    data += rem;

    return i2ba(data, 9);
}

// Check data len 9 with key len 4 for any errors
int checkError(char* sdata, char* skey) {
    int data = ca2i(sdata, 9);
    int key = ca2i(skey, 4);

    int rem = data % key;
    return rem != 0;
}

int main() {
    printf("***** CRC *****\n");
    char* skey = (char*) malloc(sizeof(char) * 4);
    printf("Enter 4 bit key > ");
    scanf("%s", skey);
   

 
    printf("\n\n----- GENERATION -----\n");
    char* sdata = (char*) malloc(sizeof(char) * 6);
    printf("Enter 6 bit data > ");
    scanf("%s", sdata);
    
    bit* crc = toCrc(sdata, skey);
    for(int i = 0; i < 9; i++) {
        printf("%d", crc[i]);
    }
    printf("\n");

    

    printf("\n\n---- DETECTION -----\n");
    char* srec = (char*) malloc(sizeof(char) * 9);
    printf("Enter 9 bit data received for the same key > ");
    scanf("%s", srec);

    int error = checkError(srec, skey);
    if(error) {
        printf("Error detected!\n");
    } else {
        printf("No error in received data!\n");
    }
    
}

