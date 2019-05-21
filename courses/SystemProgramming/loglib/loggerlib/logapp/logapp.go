package main

import (
	"fmt"
	"loggerlib/logapp/logapp_helperfuns"
)

func main() {
	_, err := logapp_helper.Addmsg()
	str, err := logapp_helper.Getlog()
	fmt.Println(str)
	fmt.Println(err)
	logapp_helper.Clearlog()
	str, _ = logapp_helper.Getlog()
	fmt.Println(str)
	fmt.Println(err)
	_, err = logapp_helper.Addmsg22()
	fmt.Println(str)
	fmt.Println(err)
	str, _ = logapp_helper.Getlog()
	fmt.Println(str)
	fmt.Println(err)
	err = logapp_helper.Savelog("m.p")
	fmt.Println(err)
}
