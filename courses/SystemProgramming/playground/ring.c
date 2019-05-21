#include <stdio.h>

int main() {
	printf("Hi");
	int fd[2];
	int x;
	pipe(fd);
	printf("Dup2");
	dup2(fd[0], 0);
	dup2(fd[1], 1);

	close(fd[0]);
	close(fd[1]);

	pipe(fd);
	printf("Two pipes created.");

	x = fork();
	if (x) {
		dup2(fd[1], 1);
	} else {
		dup2(fd[0], 0);
	}
	close(fd[0]);
	close(fd[1]);

	int i;
	int temp;
	for (i = 1; i <= 10; i++) {
		if (x) {
			printf("%d \n", i);
			fflush(stdout);
			scanf("%d", &temp);
			fprintf(stderr, "%d\n", temp);
		} else {
			scanf("%d", &temp);
			printf("%d \n", temp);
			fflush(stdout);
		}
	}
	return 0;
}
