package com.cdweb.bookstore.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "bookimage")
public class BookImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageID;
    @Column
    private String path;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookID")
    private BookEntity book;

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }
}
