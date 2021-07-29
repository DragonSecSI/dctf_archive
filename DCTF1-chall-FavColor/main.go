package main

import (
	"fmt"
	"io/ioutil"
	"log"
	"math"
	"net/http"
	"strconv"
	"strings"
)

var logString string

// ================================================================ //
// Constants
// ================================================================ //
var colors = [8]string{
	"red", "orange", "yellow", "green", "blue", "indigo", "violet",
}

/*
var translations = map[string]string{
	"red":    "rdeča",
	"orange": "oranžna",
	"yellow": "rumena",
	"green":  "zelena",
	"blue":   "modra",
	"indigo": "indigo",
	"violet": "vijola",
}
*/
var arrays = [8]([256]uint64){}
var maxVal = ^uint64(0)

// ================================================================ //
// Web Stuff
// ================================================================ //
func preparePage(color, err string) string {
	body, _ := ioutil.ReadFile("./page.html")
	body = []byte(strings.Replace(
		string(body),
		"{{ LOG }}",
		logString,
		1,
	))
	if color != "" {
		return strings.Replace(
			string(body),
			"{{ RESPONSE }}",
			fmt.Sprintf(
				`<div id="response" class="color-%s">Your favourite color is %s!</div>`,
				color,
				//translations[color],
				color,
			),
			1,
		)
	} else if err != "" {
		return strings.Replace(
			string(body),
			"{{ RESPONSE }}",
			`<div id="response" class="error">Access denied.<br>Error ID: dctf{taste_the_2209_rainbow}</div>`,
			1,
		)
	} else {
		return strings.Replace(string(body), "{{ RESPONSE }}", "", 1)
	}
}
func page(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "%s", preparePage("", ""))
}
func style(w http.ResponseWriter, r *http.Request) {
	http.ServeFile(w, r, "./page.css")
}
func icon(w http.ResponseWriter, r *http.Request) {
	http.ServeFile(w, r, "./favicon.ico")
}
func favcolor(w http.ResponseWriter, r *http.Request) {
	logString = ""
	r.ParseForm()
	ln, _ := strconv.ParseUint(r.PostFormValue("number"), 10, 64)
	name := r.PostFormValue("name")
	if !rateLimit(name, ln) {
		fmt.Fprintf(w, "%s", preparePage(getColor(name, ln), ""))
	} else {
		fmt.Fprintf(w, "%s", preparePage("", "forbidden"))
	}
}

// ================================================================ //
// Backend Stuff
// ================================================================ //
func getColor(name string, luckynum uint64) string {
	sum := 0
	h := hash(name, luckynum)
	for _, val := range h {
		sum += int(val)
	}
	return colors[sum%7]
}
func hash(name string, ln uint64) [8]byte {
	logString += fmt.Sprintf("Entering hash(%s, %d)\n", name, ln)
	name = fmt.Sprintf("%8v", name)
	logString += fmt.Sprintf("Expanded name to '%s'\n", name)
	h := [8]byte{}
	for i := 0; i < 8; i++ {
		digit := (ln % uint64(math.Pow10(8-i))) / uint64(math.Pow10(7-i))
		logString += fmt.Sprintf("    digit is %d,  letter is '%c'\n", digit, name[i])
		h[i] = byte((uint64(name[i])*13 + digit) % 256)
		logString += fmt.Sprintf("    h[%d] is %d\n", i, h[i])
	}
	logString += fmt.Sprintf("returning from hash() with %+v\n", h)
	return h
}
func rateLimit(name string, luckynum uint64) bool {
	logString += fmt.Sprintf("Entering rateLimit(%s, %d)\n", name, luckynum)
	indices := hash(name, luckynum)
	logString += fmt.Sprintf("Hash indices are %+v\n", indices)
	numMaxedOut := 0
	for i := 0; i < 8; i++ {
		if arrays[i][indices[i]] == maxVal {
			numMaxedOut++
		}
	}
	if numMaxedOut == 8 {
		logString += fmt.Sprintf("Returning from rateLimit with %v\n", true)
		return true
	}
	for i := 0; i < 8; i++ {
		arrays[i][indices[i]]++
	}
	logString += fmt.Sprintf("Returning from rateLimit with %v\n", false)
	return false
}
func initArrays() {
	logString += "Entering initArrays()\n"
	for i := 0; i < 8; i++ {
		for j := 0; j < 256; j++ {
			arrays[i][j] = 0
		}
	}
	// To je za unega tipa ka nas vedno DoS-a
	arrays[0][94] = maxVal
	arrays[1][124] = maxVal
	arrays[2][237] = maxVal
	arrays[3][26] = maxVal
	arrays[4][94] = maxVal
	arrays[5][141] = maxVal
	arrays[6][87] = maxVal
	arrays[7][202] = maxVal

	logString += "Initialized array to\n"
	for i := 0; i < 8; i++ {
		for j := 0; j < 256; j++ {
			if arrays[i][j] > 0 {
				logString += fmt.Sprintf("    %3d: %d\n", j, arrays[i][j])
			}
		}
	}
	logString += "End of initArrays()\n"
}

// ================================================================ //
// Main
// ================================================================ //
func main() {
	initArrays()
	http.HandleFunc("/", page)
	http.HandleFunc("/page.css", style)
	http.HandleFunc("/favicon.ico", icon)
	http.HandleFunc("/getfav", favcolor)
	log.Fatal(http.ListenAndServe(":8080", nil))
}
