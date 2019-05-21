package logapp_helper

import (
	"time"
	"loggerlib/loggerlib"
)

func Addmsg() (int, error) {
	data := new(loggerlib.Data_t)
	data.Logged_time = time.Now().Local()
	data.Str = "This is my first message"
	loggerlib.Addmsg(*data)
	
	data2 := new(loggerlib.Data_t)
	data2.Logged_time = time.Now().Local()
	data2.Str = "This is my second message"
	res, err := loggerlib.Addmsg(*data2)
	return res, err
}

func Getlog() (string, error){
	return loggerlib.Getlog()
}

func Clearlog() {
	loggerlib.Clearlog()
}

func Addmsg22() (int, error) {
	data := new(loggerlib.Data_t)
	data.Logged_time = time.Now().Local()
	data.Str = "This is my third message"
	loggerlib.Addmsg(*data)
	
	data2 := new(loggerlib.Data_t)
	data2.Logged_time = time.Now().Local()
	data2.Str = "This is my fourth message"
	
	res, err := loggerlib.Addmsg(*data2)
	return res, err 
}

func Savelog(filename string) (error) {
	return loggerlib.Savelog(filename)
}
