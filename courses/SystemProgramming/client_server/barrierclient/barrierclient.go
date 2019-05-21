/*******************************************************************************
/
/      filename:  barrierclient.go
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
package main

import (
   "os"
   "strconv"
   "./waitatbarrier"
)

func main() {

   i,_ := strconv.Atoi(os.Args[1])

   /* first barrier */
   waitatbarrier.Waitatbarrier("barrier", i, 1)

   /* second barrier */
   waitatbarrier.Waitatbarrier("barrier", i, 2)

   os.Exit(0)
}
