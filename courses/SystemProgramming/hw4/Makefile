CC = gcc
APP = flip
CFLAG = -DBSD -DNDEBUG -O -c

all: $(APP) man

$(APP): $(APP).o getopt.o
	@gcc -s -o $(APP) $(APP).o getopt.o 

$(APP).o: $(APP).c $(APP).h
	@gcc $(CFLAG) $(APP).c

getopt.o: getopt.c $(APP).h
	@gcc $(CFLAG) getopt.c

man: $(APP).1
	@nroff -man $(APP).1 > $(APP).man

clean:
	-rm $(APP) *.o $(APP).man
