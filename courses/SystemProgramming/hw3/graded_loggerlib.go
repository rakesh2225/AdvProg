/*******************************************************************************
/  Grade: 54 / 60
/
/  *  0 / 6 for style (note 1)
/  *  22 / 22 for saving the log to a file
/  *  10 / 10 for clearing the log
/  *  22 / 22 for adding messages and retrieving messages
/  *     11 / 11 for adding
/  *     11 / 11 for retrieving
/
/  Additional notes:
/
/  *  It installed, but did not compile. But because it was a simple fix
/     and worked perfecly otherwise, I let you go with zero style points
/     instead of zero overall. If a function returns a value that you don't
/     want to store, put an underscore in its place:
/     _, err1 := f.WriteString(str)
/     Go doesn't want you declaring variables that you don't use, and
/     refuses to compile if you try.
/
/******************************************************************************/

/*******************************************************************************
/
/      filename:  loggerlib.go
/
/   description:  Implements logging library using linked list.
/
/        author:  Sunkaralakunta Venkatarama Reddy, Rakesh
/      login id:  SP_19_CPS444_11
/
/         class:  CPS 444 
/    instructor:  Perugini
/    assignment:  Homework #3
/
/      assigned:  February 4, 2019
/           due:  February 12, 2019
/
/******************************************************************************/

package loggerlib

import (
   "os"
   "fmt"
   "time"
   "bytes"
   "errors"
   "strings"
)

type Data_t struct {
   Logged_time time.Time
   Str string
}

type Log_t struct {
   item Data_t
   next *Log_t
}

var months map[string]string
var headptr *Log_t
var tailptr *Log_t

const DATE_TIME_LAYOUT = "Jan _2 2006 15:04:05"

/**************************************************************************
*
* init will initialize the months map for date formatter
*
/*************************************************************************/
func init() {
   months = make(map[string]string)
   months["Jan"] = "01"; months["Feb"] = "02"; months["Mar"] = "03"
   months["Apr"] = "04"; months["May"] = "05"; months["Jun"] = "06"
   months["Jul"] = "07"; months["Aug"] = "08"; months["Sep"] = "09"
   months["Oct"] = "10"; months["Nov"] = "11"; months["Dec"] = "12"
}


/**************************************************************************
*
* Purpopse: This method will prepare a printable format for each node
* data    : data structure which has time and message
* returns formatted string.
*
/*************************************************************************/
func stringify(data Data_t) (string) {
   timeSlice := strings.Split(data.Logged_time.Format(DATE_TIME_LAYOUT), " ")
   msg := "Time: " + months[timeSlice[0]] + "/"
   if len(timeSlice[1]) == 1 {
      msg += "0"
   }
   msg += timeSlice[1] + "/" + timeSlice[2] + " " + timeSlice[3] + "\n"
   msg += "Message: " + data.Str + "\n\n"
   return msg
}


/**************************************************************************
*
* Purpopse: To add messages into the logs maintained as linked list.
* data    : data structure which has time and message
* returns 0, nil if successful otherwise -1, "error message" if unsuccesful
*
/*************************************************************************/
func Addmsg(data Data_t) (int, error) {
   if data == (Data_t{}) {
      return -1, errors.New("can not add empty message to the log.")
   }
   newMsg := new(Log_t)
   newMsg.item = data
   if headptr == nil {
      headptr = newMsg
   } else {
      tailptr.next = newMsg
   }
   tailptr = newMsg
   return 0, nil
}


/**************************************************************************
*
* Purpopse: To check if the log list is empty.
* returns true if the list is empty otherwise false.
*
/*************************************************************************/
func isEmpty() bool{
   if headptr == nil {
      return true;
   }
   return false;
}


/**************************************************************************
*
* Purpopse: To clear messages from the list.
*
/*************************************************************************/
func Clearlog() {
   headptr = nil;
   tailptr = nil;
}


/**************************************************************************
*
* Purpopse: To get all the logs maintained in the linked list.
* returns fromatted string and error message if any errors occured.
*
/*************************************************************************/
func Getlog() (string, error) {
   if isEmpty() {
      return "", errors.New("no new messages in the log.")
   }
   var buffer bytes.Buffer
   travelptr := headptr
   for ok := true; ok; ok = (travelptr != nil) {
      buffer.WriteString(stringify(travelptr.item))
      travelptr = travelptr.next
   }
   return buffer.String(), nil
}


/**************************************************************************
*
* Purpopse: To save all the logs maintained in the linked list in to a file
* filename: name of the file to which logs has to be written.
* returns error message if any errors occured, otherwise nil.
*
/*************************************************************************/
func Savelog(filename string) error {
	if len(filename) < 1 {
      return errors.New("provide filename")
	}
   if isEmpty() {
      return errors.New("there are no logs to write to the file.")
   }
   f, err := os.OpenFile(filename, os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0644)
   if err != nil {
      return errors.New("error creating/openning the file. Could not save logs to the file.")
   }
   defer f.Close()
   str, err := Getlog()
   if err != nil {
      return err
	}
   len, err1 := f.WriteString(str)
   if err1 != nil {
		fmt.Println(err1)
      return errors.New("failed writing to the file")
   }
   return nil
}

