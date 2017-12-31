/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bstbookdb;

/**
 *
 * @author DeiNerd
 */
public class Book {
    public String isbnNo;
    public String title;
    public String author;
    public String publisher;
    public String year;
    public Book left;
    public Book right;
    
    public Book(String no,String name,String writer,String pub,String yr){
        isbnNo = no;
        title = name;
        author = writer;
        publisher = pub;
        year = yr;
    }


}
