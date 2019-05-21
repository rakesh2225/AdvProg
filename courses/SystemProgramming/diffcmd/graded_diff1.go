/*******************************************************************************
/  Grade: 47 / 60
/
/  * 6 / 6 for style
/  * 32 / 32 for correct basic diff functionality
/  * 0 / 2 for NOT prompting for input
/  * 2 / 2 for correct exit status
/  * 1 / 2 for recognizing both files and the - for stdin
/  * 0 / 8 for the flags working properly
/     * 0 / 2 for -l
/     * 0 / 2 for -t
/     * 0 / 2 for -m
/     * 0 / 2 for -a
/  * 2 / 2 for handling non-existent files properly
/  * 0 / 2 for handling invalid options properly
/  * 2 / 2 for handling more than two file names given
/  * 2 / 2 for writing all error messages to stderr
/
/     comments: program prompted for input when using - or - -, and only
/     compared one line.  No flags were accepted by the program.
/
/******************************************************************************/
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
import "strings"


/******************************************************************************
/   purpose : Read complete line from a file reader.
/   isPrefix: is set when complete line could not be read because it is too large,
/            subsequest readlines will reset isPrefix once all the characters are read.
/*****************************************************************************/

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


/******************************************************************************
/
/   purpose: Compares contents of both the files and prints the line number 
/            where files do not match
/   file1  : first file name 
/   file2  : second file name to compare with file1
/
/*****************************************************************************/

func compareFiles(file1 string, file2 string) {
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
   
   /* 
      Checks the first line from both files, if either one line from a file is 
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
}


/******************************************************************************
/
/ purpose: Compare the given file content with the standard input
/          and print the line numbers which do no match
/ file1  : fileName provided by the user
/ val    : string provided by the user in standard input
/
/*****************************************************************************/

func compareFileStd(file1 string, val string) {
   fh1, err1 := os.Open("./" + file1)
   if err1 != nil {
      fmt.Fprintf(os.Stderr, "Error opening file %#v", file1)
      fmt.Println(" ");
      os.Exit(1)
   }
   defer fh1.Close()
   lineNumber := 0
   reader1 := bufio.NewReader(fh1)
   lineF1, error1 := readCompleteLine(reader1)
   
   if error1 == nil {
      lineNumber = lineNumber + 1
      if lineF1 != val {   
         fmt.Println(lineNumber)
      }
   }
   lineF1, error1 = readCompleteLine(reader1)
   for error1 == nil {
      lineNumber = lineNumber + 1
      fmt.Println(lineNumber)
      lineF1, error1 = readCompleteLine(reader1)
   }
}

/****************************************************************************
/ purpose : main entry for program executing
/
/****************************************************************************/

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
   arg1 := os.Args[1]
   arg2 := os.Args[2]
   
   txt1 := ""
   txt2 := ""
   
   if arg1 == "-" {
      reader := bufio.NewReader(os.Stdin)
      fmt.Print("Enter text1: ")
      text1, _ := reader.ReadString('\n')
      txt1 = strings.TrimSuffix(text1, "\n")
   }
   if arg2 == "-" {
      reader := bufio.NewReader(os.Stdin)
      fmt.Print("Enter text2: ")
      text2, _ := reader.ReadString('\n')
      txt2 = strings.TrimSuffix(text2, "\n")
   }
   if arg1 == "-" && arg2 == "-" {
      if txt1 != txt2 {
         fmt.Println("1")
      }
   } else if arg1 == "-" && arg2 != "-" {
      compareFileStd(arg2, txt1)
   } else if arg2 == "-" && arg1 != "-" {
      compareFileStd(arg1, txt2)
   } else {
      compareFiles(arg1, arg2)
   } 
}
