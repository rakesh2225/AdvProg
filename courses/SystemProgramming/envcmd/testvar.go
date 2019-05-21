package main

import (
	"fmt"
	"os"
)

func main() {
	fmt.Printf("A=\"%s\"\n", os.Getenv("A"))
	fmt.Printf("B=\"%s\"\n", os.Getenv("B"))
}
