CC=gcc
CFLAGS=-ggdb -Wall -pedantic -pthread -Wno-pointer-to-int-cast -Wno-int-to-pointer-cast

all: thread_creation

thread_creation: main.c
	$(CC) $(CFLAGS) -o thread_creation main.c

clean:
	rm -rf thread_creation thread_creation.dSYM
