CC=g++
CFLAGS=-ggdb -std=c++11 -Wall -pedantic -Wno-sign-compare

all: client server

client: TCPClient.c DieWithMessage.c
	$(CC) $(CFLAGS) -o client TCPClient.c DieWithMessage.c

server: HandleTCPClient.c TCPEchoServer.c DieWithMessage.c
	$(CC) $(CFLAGS) -o server HandleTCPClient.c TCPEchoServer.c DieWithMessage.c

clean:
	rm -rf client server
