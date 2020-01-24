#include <stdio.h>      /* for printf() and fprintf() */
#include <sys/socket.h> /* for recv() and send() */
#include <unistd.h>     /* for close() */
#include <errno.h>

#include "Practical.h"

#define RCVBUFSIZE 32   /* Size of receive buffer */

void HandleTCPClient(int clntSocket)
{
    char echoBuffer[RCVBUFSIZE];        /* Buffer for echo string */
    int recvMsgSize;                    /* Size of received message */

    /* Receive message from client */
    if ((recvMsgSize = recv(clntSocket, echoBuffer, RCVBUFSIZE, 0)) < 0)
	{
		if (errno != EAGAIN && errno != EWOULDBLOCK) {
        	DieWithSystemMessage("recv() failed");
		} else {
			printf("Timeout occured, closing connection\n");
			close(clntSocket);
			return;
		}
	}

    /* Send received string and receive again until end of transmission */
    while (recvMsgSize > 0)      /* zero indicates end of transmission */
    {
        /* Echo message back to client */
        if (send(clntSocket, echoBuffer, recvMsgSize, 0) != recvMsgSize)
            DieWithSystemMessage("send() failed");

        /* See if there is more data to receive */
        if ((recvMsgSize = recv(clntSocket, echoBuffer, RCVBUFSIZE, 0)) < 0)
		{
			if (errno != EAGAIN && errno != EWOULDBLOCK) {
				DieWithSystemMessage("recv() failed");
			} else {
				printf("Timeout occured, closing connection\n");
				close(clntSocket);
				return;
			}
		}
    }

    close(clntSocket);    /* Close client socket */
}
