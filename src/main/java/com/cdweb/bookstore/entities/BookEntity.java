package com.cdweb.bookstore.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    private int id;
    @Column(name = "title", length = 200)
    private String title;

    private int year_public;

    private int total_page;
    @Column(name = "publisher", length = 200)
    private String publisher;

    private String description;

    private int price;
    @Column(name = "discount_percent")
    private double discountPercent;

    @Column(name = "quantity_sold")
    private int quantitySold;

    private LocalDate created_at;

    private LocalDate updated_at;
    private boolean active;
    private boolean news;
    private boolean hot;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryID")
    private CategoryEntity category;
    @ManyToOne
    @JoinColumn(name = "authorID")
    private AuthorEntity author;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book",
            cascade = CascadeType.REMOVE)
    List<BookImageEntity> images = new ArrayList<>();

    //getter & setter
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

    public int getYear_public() {
        return year_public;
    }

    public void setYear_public(int year_public) {
        this.year_public = year_public;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isNews() {
        return news;
    }

    public void setNews(boolean news) {
        this.news = news;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public List<BookImageEntity> getImages() {
        return images;
    }

    public void setImages(List<BookImageEntity> images) {
        this.images = images;
    }
}
