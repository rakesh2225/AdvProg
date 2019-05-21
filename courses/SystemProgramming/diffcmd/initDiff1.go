/*******************************************************************************
/
/      filename:  diff1.go
/
/   description:  Implements the UNIX env utility.
/
/        author:  Sunkaralakunta Venkatarama Reddy, Rakesh
/      login id:  SP_19_CPS444_11
/
/         class:  CPS 444
/    instructor:  Perugini
/    assignment:  Homework #1
/
/      assigned:  January 16, 2019
/           due:  January 23, 2019
/
/******************************************************************************/

package main

import "os"
import "fmt"
import "bufio"

/*
   Read complete line from a file reader.
   isPrefix is set when complete line could not be read because it is too large,
   subsequest readlines will reset isPrefix once all the characters are read.
*/
func readCompleteLine(reader *bufio.Reader) (string, error) {
   var (
      isPrefix bool = true
      lineRead, completeLine []byte
      err error = nil
   )
   for isPrefix && err == nil {
      lineRead, isPrefix, err = reader.ReadLine()
      completeLine = append(completeLine, lineRead...)
   }
   return string(completeLine), err
}

/*
   Compares contents of both the files.
*/
func compareFiles(file1 string, file2 string) {
   //fmt.Printf("Comparing %#v and %#v files.", file1, file2)
   //fmt.Println()
   fh1, err1 := os.Open("./" + file1)
   if err1 != nil {
      fmt.Fprintf(os.Stderr, "Error opening file %#v", file1)
      fmt.Println(" ");
      os.Exit(1)
   }
   defer fh1.Close()
   
   fh2, err2 := os.Open("./" + file2)
   if err2 != nil {
      fmt.Fprintf(os.Stderr, "Error opening file %#v", file2)
      fmt.Println(" ");
      os.Exit(1)
   }
   defer fh2.Close()
   
   lineNumber := 0
   reader1 := bufio.NewReader(fh1)
   lineF1, error1 := readCompleteLine(reader1)
   
   reader2 := bufio.NewReader(fh2)
   lineF2, error2 := readCompleteLine(reader2)
   
   /*assign to blank as it will pop error if not used when for loop not entered*/
   _ = lineF1
   _ = lineF2
   
   /* Checks the first line from both files, if either one line from a file is 
      present then it enters for loop to compare other lines until EOF is 
      reached in both the files.
   */
   for error1 == nil || error2 == nil {
      lineNumber = lineNumber + 1
      //line content comparision
      if lineF1 != lineF2 {
         fmt.Println(lineNumber)
      }
      if error1 == nil {
         lineF1, error1 = readCompleteLine(reader1)
      }
      if error2 == nil {
         lineF2, error2 = readCompleteLine(reader2)
      }
   }
   //return "Comparing files complete.";
}

func main() {
   args := os.Args[1:]
   if len(args) < 2 {
      fmt.Fprintln(os.Stderr, "Invalid number of args.")
      fmt.Fprintln(os.Stderr, "Usage: ./diff1 <fname1> <fname2>")
      os.Exit(1)
   }
   if len(args) > 2 {
      fmt.Fprintf(os.Stderr, "diff: extra operand %#v", os.Args[3])
      fmt.Println()
      fmt.Fprintln(os.Stderr, "Usage: go run diff1.go <fname1> <fname2>")
      os.Exit(2)
   }
   file1 := os.Args[1]
   file2 := os.Args[2]
   compareFiles(file1, file2)
}
