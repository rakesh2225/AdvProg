#include <stdio.h>

int main(int argc, char *argv) {
	int fd[2];
	int pipeConnected = pipe(fd);
	if (pipeConnected < 0) {
		exit(1);
	}
	printf(fd[0]);
	printf(fd[1]);
	return 0;
}
