
/*******************************************************************************
/
/      filename:  barrierserver.go
/
/   description:  Client server implementation using GO.
/
/        author:  Sunkaralakunta Venkatarama Reddy, Rakesh
/      login id:  SP_19_CPS444_11
/
/         class:  CPS 444
/    instructor:  Perugini
/    assignment:  Homework #2
/
/      assigned:  March 6, 2019
/           due:  April 3, 2019
/
/******************************************************************************/
package main

import (
   "fmt"
   "os"
   "io"
	"time"
	"strconv"
	"syscall"
)

func main() {
   args := os.Args[1:]
   if len(args) != 2 {
      fmt.Println(os.Stderr, "Invalid number of arguments!")
      fmt.Println(os.Stderr, "Usage: ./barrierserver.go <name> <number_of_clients>")
      os.Exit(1)
	}
   //fmt.Printf("Size.. %s\n", args[1])
   size, err := strconv.Atoi(os.Args[2])
	if err != nil || size < 1 {
      fmt.Println("Invalid number of clients")
      os.Exit(2)
	} 
   //fmt.Printf("Validation complete %d\n", size)
	requestPipe := args[0] + ".request"
	releasePipe := args[0] + ".release"
	syscall.Mkfifo(requestPipe, 0666)
	syscall.Mkfifo(releasePipe, 0666)
	fmt.Println("Barrier server started.")
   for {
      buf := make([]byte, 1)
      message := ""
      requestFd ,err := syscall.Open(requestPipe, os.O_RDWR, 0600)    
      if err != nil {
         fmt.Printf("Failed to open %s pipe.\n", requestPipe)
         os.Exit(3)
      }
      
      //fmt.Println("Reading...")
      dataRead := 0
      for dataRead < size {
         i, err := syscall.Read(requestFd, buf)
         if err == io.EOF {
            //fmt.Println("EOF..")
            i, _ = syscall.Read(requestFd, buf)
         }
         if dataRead >= size {
            break
         }
         dataRead = dataRead + 1
         message = message + string(buf[:i])
         fmt.Printf("server read %s in 1 byte, size %d\n", string(buf[:i]), dataRead)
      }
      //fmt.Println("Closing request pipe")
	   syscall.Close(requestFd)

      //write to name.release pipe
      releaseFd, err := syscall.Open(releasePipe, os.O_WRONLY, 0666)
      if err != nil {
         fmt.Printf("Failed to open %s pipe.\n", releasePipe)
         os.Exit(3)
      }
      _, err = syscall.Write(releaseFd, []byte(message))
      if err != nil {
         fmt.Println("Error in writing to release pipe in barrier server.")
         os.Exit(4)
      }
      fmt.Printf("server just wrote %s in %d bytes to release pipe.\n", message, dataRead)
      syscall.Close(releaseFd)
      time.Sleep(time.Second * 3)
   }
}
