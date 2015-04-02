package main

import (
  "fmt"
  "net/http"
)

type String string

type Struct struct {
  Greeting string
  Punct    string
  Who      string
}

func (s String) ServeHTTP(w http.ResponseWriter, r *http.Request){
  fmt.Fprint(w, s)
}

func (s* Struct) ServeHTTP(w http.ResponseWriter, r *http.Request){
  fmt.Fprint(w, s.Greeting + s.Punct + " " + s.Who)
}

func main() {
  var str String = String("I'm a frayed knot.")
  var strct* Struct = &Struct{"Hello", ":", "Gophers!"}

  http.Handle("/string", str)
  http.Handle("/struct", strct)
  http.ListenAndServe("localhost:4000", nil)
}