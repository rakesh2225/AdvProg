/*******************************************************************************
/
/      filename:  waittobarrier.go
/
/   description:  Client implementation using GO.
/
/        author:  Sunkaralakunta Venkatarama Reddy, Rakesh
/      login id:  SP_19_CPS444_11
/
/         class:  CPS 444
/    instructor:  Perugini
/    assignment:  Homework #6
/
/      assigned:  March 6, 2019
/           due:  March 20, 2019
/
/******************************************************************************/
package waitatbarrier

import (
   "fmt"
   "os"
   "syscall"
	"errors"
)

func Waitatbarrier(name string, i int, bid int) error {
   fmt.Printf("i: %d, pid: %d: just arrived at barrier %d\n", i, os.Getpid(), os.Getppid())
   requestFd, err := syscall.Open(name + ".request", os.O_WRONLY, 0666)
   if err != nil {
      fmt.Printf("WB> DEBUG: Failed to open %s pipe for writeonly.\n", requestFd)
      return errors.New("Failed to open pipe for writeonly.")
   }
	one_byte := []byte{'A'}
	
   _, err = syscall.Write(requestFd, one_byte)
   for err != nil {
      //fmt.Println("WB> DEBUG: Error in writing to request pipe...")
      return errors.New("Failed to write byte to request pipe\n")
   }
   fmt.Printf("client %d just wrote 'A' in 1 byte to request pipe\n", i)
   syscall.Close(requestFd)

   releaseFd, err := syscall.Open(name + ".release", os.O_RDWR, 0600)
   if err != nil {
      fmt.Println("WB> DEBUG: Failed to open release %s pipe in RDWR only")
      os.Exit(5)
   }
   buf := make([]byte, 1)
   //fmt.Println("WB> DEBUG: Reading...")
   syscall.Read(releaseFd, buf)
   fmt.Printf("i: %d, pid: %d: ppid: %d just just read 'A' in 1 byte from the release pipe.\n", i, os.Getpid(), os.Getppid())
   //fmt.Println("WB> DEBUG: Closing release pipe")
   syscall.Close(releaseFd)
   fmt.Printf("i: %d, pid: %d: just passed barrier %d\n", i, os.Getpid(), os.Getppid())
   return nil
}

