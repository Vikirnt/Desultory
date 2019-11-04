#include <stdio.h>

typedef struct {
    int id;
    int packetRate;
    int ttl;
} Machine;

typedef struct {
    int n;
    int r;
    int max;
    int removal;
    int buffer;
    Machine* mls;
} LeakyBucket;

Machine* inputMachines(int n) {
    printf("Start entering machines -> \n");

    Machine* mls = (Machine*) malloc(n * sizeof(Machine));
    for(int i = 0; i < n; i++) {
        mls[i].id = i + 1;

        printf("%d : Enter packet rate > ", i + 1);
        scanf("%d", &mls[i].packetRate);

        printf("%d : Enter time till when machine will be active > ", i + 1);
        scanf("%d", &mls[i].ttl);
    }

    return mls;
}

void runSimulation(LeakyBucket* lb) {
    printf("\n\n STARTING SIMULATION \n\n");

    int time = 0;
    lb->buffer = 0;

    Machine* mls = lb->mls;

    int flag = 1;
    while(flag == 1 || lb->buffer > 0) {
        flag = 0;
        time += 1;
        printf("\t TIME %d \n", time);

        for(int i = 0; i < lb->n; i++) {
            if(time <= mls[i].ttl && time % mls[i].packetRate == 0) {
                flag = 1;
                printf("> Machine %d (r%d) sent a packet\n", mls[i].id, mls[i].packetRate);
                if(lb->buffer == lb->max) {
                    printf("$ PACKET DROPPED!\n ");
                } else {
                    lb->buffer++;
                }
            }

            if(time == mls[i].ttl) {
                printf("\t# Machine %d has died!\n", mls[i].id);
            }
        }

        if(lb->buffer > 0 && time % lb->r == 0) {
            lb->buffer--;
            printf("\t* Bucket removed a packet\n");
        }

        printf("\t& REMAINING BUFFER = %d \n\n", lb->buffer);
    }
}

int main() {
    LeakyBucket lb;

    printf("Enter regulated rate > ");
    scanf("%d", &lb.r);

    printf("Enter max capacity of bucket > ");
    scanf("%d", &lb.max);

    printf("Enter number of machines > ");
    scanf("%d", &lb.n);

    lb.mls = inputMachines(lb.n);

    runSimulation(&lb);
}

