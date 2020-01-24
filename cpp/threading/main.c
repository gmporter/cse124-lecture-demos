#include <stdio.h>
#include <pthread.h>

#define NUM_THREADS 10

void* hello_world(void* arg) {
    short my_number = (short)arg;
    printf("Hello from thread %d\n", my_number);

    return NULL;
}

int main(void) {
    short i, ret;
    pthread_t my_threads[NUM_THREADS];

    printf("Press any key to exit the program\n");

    // Create all of the threads
    for(i = 0; i < NUM_THREADS; i++) {
        ret = pthread_create(&my_threads[i],
                             NULL,
                             hello_world,
                             (void*)i);
        if (ret != 0) {
            fprintf(stderr, "Failed to create thread %d!\n", i);
            return 1;
        }

        ret = pthread_detach(my_threads[i]);

        if (ret != 0) {
            fprintf(stderr, "Failed to detach thread %d!\n", i);
            return 1;
        }
    }

    getchar();

    return 0;
}
