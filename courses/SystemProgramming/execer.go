package main

import (
	"fmt"
	"os"
	"flag"
)

func main() {
	fmt.Println("Execer file")
	flag.Parse()
	namedPipe := flag.Args()[0]
	fmt.Printf("Opening named pipe for writing %s\n", namedPipe)
	stdout, _ := os.OpenFile(namedPipe, os.O_RDWR, 0600)
	fmt.Println("Writing")
	stdout.Write([]byte("hello"))
	stdout.Close()
}
