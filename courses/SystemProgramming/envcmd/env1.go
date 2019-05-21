/*******************************************************************************
/
/      filename:  env1.go
/
/   description:  Implements the UNIX env utility.
/
/        author:  Sunkaralakunta Venkatarama Reddy, Rakesh
/      login id:  SP_19_CPS444_11
/
/         class:  CPS 444
/    instructor:  Perugini
/    assignment:  Homework #2
/
/      assigned:  January 23, 2019
/           due:  January 30, 2019
/
/******************************************************************************/

package main

import (
   "fmt"
   "os"
   "os/exec"
   "strings"
   "flag"
)

var ignoreFlag *bool

/**************************************************************************
/
/ purpose : To parse the flags sent from command prompt
/
/*************************************************************************/

func init() {
   iFlag := flag.Bool("i", false, 
		"Ignore previous environment values and use the new value pairs for next process execution.")
   flag.Parse()
   ignoreFlag = iFlag;
}

/*************************************************************************
/
/ purpose: handle the ignore flag,set env values,and exe any shell command
/ flagArgs: flag arguments sent in the command prompt
/
/************************************************************************/

func handleFlagArguments(flagArgs []string) {
	argCount := 0
   for _, v := range flagArgs {
		argCount = argCount + 1
      pair := strings.Split(v, "=")
      if len(pair) > 1 {
         //Handle -i option when setting the variable
         os.Setenv(pair[0], strings.Join(pair[1:], "="))
         fmt.Printf("%v=%v",pair[0],strings.Join(pair[1:], "="))
         fmt.Println()
      } else if len(pair) == 1 {
			if argCount < len(flag.Args()) {
				tailArgs := strings.Join(flag.Args()[argCount:], " ")
				out, err := exec.Command(pair[0], tailArgs).Output()
           	if (err != nil) {
              	fmt.Fprintf(os.Stderr, "Error executing command %v", err)
           	}
          	fmt.Println(string(out[:]))
			} else {
           	out, err := exec.Command(pair[0]).Output()
           	if err != nil {
              	fmt.Fprintf(os.Stderr, "Error executing command %v", err)
           	}
           	fmt.Println(string(out[:]))
			}
         break
		}
	}
}


/*************************************************************************
/
/	purpose: Main entry for program execution
/
/************************************************************************/

func main() {
   if (!(*ignoreFlag)) {
		//Refresh the shell if ignore flag is not set. 
		//Sorry could not find another way out once the env is cleared.
		_, err := exec.Command("/home/SP_19_CPS444_11/.kshrc").Output()
 		if err != nil {
			fmt.Fprintf(os.Stderr, "Error executing command %v", err)
 		}
		for _, v := range os.Environ() {
         pair := strings.Split(v, "=")
         fmt.Printf("%v=%v\n",pair[0], pair[1])
      }
   }
	//temp varibale to hold PATH, to be reset after clear env to 
	//execute shell commands
	path := os.Getenv("PATH")
	if (*ignoreFlag) {
		os.Clearenv()
	}
	os.Setenv("PATH", path)
	handleFlagArguments(flag.Args())
}
