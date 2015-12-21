package reto.android.chorro.pau.Model;

import android.graphics.Bitmap;

import java.util.Vector;

/**
 * Created by pauchorroyanguas on 20/12/15.
 */
public class Book {

    private int id;
    private String nameFile;
    private String title;
    private String author;
    private String cover;
    private String path;

    public Book() {}

    public Book(int id, String title, String author, String cover) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.cover = cover;
    }

    public Book(int id, String title, String author, String cover, String path) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.cover = cover;
        this.path = path;
    }

    public Book(String path, String author, String title, String nameFile, int id) {
        this.path = path;
        this.author = author;
        this.title = title;
        this.nameFile = nameFile;
        this.id = id;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public static Vector<Book> getMockBooks(){

        Vector<Book> books = new Vector<>();

        Book book1 = new Book(0, "Libro 1", "Autor 1", "books");
        books.add(book1);
        Book book2 = new Book(1, "Libro 2", "Autor 2", "books");
        books.add(book2);
        Book book3 = new Book(2, "Libro 3", "Autor 3", "books");
        books.add(book3);
        Book book4 = new Book(3, "Libro 4", "Autor 4", "books");
        books.add(book4);
        Book book5 = new Book(4, "Libro 5", "Autor 5", "books");
        books.add(book5);
        Book book6 = new Book(5, "Libro 6", "Autor 6", "books");
        books.add(book6);
        Book book7 = new Book(6, "Libro 7", "Autor 7", "books");
        books.add(book7);
        Book book8 = new Book(7, "Libro 8", "Autor 8", "books");
        books.add(book8);

        return books;

    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }
}
