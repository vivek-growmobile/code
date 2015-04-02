package main

import (
  "io/ioutil"
  "net/http"
  "html/template"
  "regexp"
)

type Page struct {
  Title string
  Body []byte
}

var templates = template.Must(template.ParseFiles("view.html", "edit.html"))

var validPath = regexp.MustCompile("^/(edit|save|view)/([a-zA-Z0-9]+)$")


func (p *Page) save() error {
  filename := p.Title + ".txt"
  return ioutil.WriteFile(filename, p.Body, 0600)
}

func loadPage(title string) (*Page, error) {
  filename := title + ".txt"
  body, err := ioutil.ReadFile(filename)
  if err != nil {
    return nil, err
  }
  return &Page{Title: title, Body: body}, nil
}

func makeHandler(fn func(http.ResponseWriter, *http.Request, string)) http.HandlerFunc {
  return func(w http.ResponseWriter, r *http.Request){
    m := validPath.FindStringSubmatch(r.URL.Path)
    if m == nil {
      http.NotFound(w,r)
      return
    }
    fn(w, r, m[2])
  }
}

func viewHandler(w http.ResponseWriter, r *http.Request, title string){
  p, err := loadPage(title)
  if err != nil {
    http.Redirect(w, r, "/edit/"+title, http.StatusFound)
    return
  }
  renderTemplate(w, "view", p)
}

func editHandler(w http.ResponseWriter, r *http.Request, title string){
  pg, err := loadPage(title)
  if err != nil {
    pg = &Page{Title: title}
  }
  renderTemplate(w, "edit", pg)
}

func saveHandler(w http.ResponseWriter, r *http.Request, title string){
  body := r.FormValue("body")
  pg := &Page{Title: title, Body: []byte(body)}
  err := pg.save()
  if err != nil {
    http.Error(w, err.Error(), http.StatusInternalServerError)
    return
  }
  http.Redirect(w, r, "/view/"+title, http.StatusFound)
}

func renderTemplate(w http.ResponseWriter, tmpl string, pg *Page){
  err := templates.ExecuteTemplate(w, tmpl + ".html", pg)
  if err != nil {
    http.Error(w, err.Error(), http.StatusInternalServerError)
  }
}

func main() {
  http.HandleFunc("/view/", makeHandler(viewHandler))
  http.HandleFunc("/edit/", makeHandler(editHandler))
  http.HandleFunc("/save/", makeHandler(saveHandler))
  http.ListenAndServe(":8080", nil)

  // p1 := &Page{Title: "Test Page", Body: []byte("This is a sample Page.")}
  // p1.save()
  // p2, err := loadPage("Test Page")
  // if err != nil {
  //   fmt.Println(err)
  // } else {
  //   fmt.Println(string(p2.Body))
  // }
}