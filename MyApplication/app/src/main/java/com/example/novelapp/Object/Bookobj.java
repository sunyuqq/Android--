package com.example.novelapp.Object;

public class Bookobj {

 private String id;
 private String bookname;
 private String tag;
 private String descripe;
 private String author;
 private String img;
 public Bookobj(String id, String img, String bookname, String tag, String descripe, String author){

  this.id = id;
  this.img=img;
  this.bookname = bookname;
  this.tag = tag;
  this.descripe = descripe;
  this.author = author;

 }

 public String getId(){
  return  id;
 }
 public String getBookname(){
  return bookname;
 }
 public String getTag(){
  return tag;
 }
 public String getDescripe(){
  return descripe;
 }
 public String getAuthor() {
  return author;
 }
 public String getImg() {       return img;    }
 public void setImg(String img) {        this.img = img;    }
}