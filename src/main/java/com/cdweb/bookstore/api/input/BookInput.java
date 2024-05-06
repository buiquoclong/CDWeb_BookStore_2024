package com.cdweb.bookstore.api.input;

import org.springframework.web.multipart.MultipartFile;

public class BookInput {
    private int id;

    private String title;

    private int year_public;

    private int total_page;

    private String publisher;

    private String description;

    private int price;

    private double discount_percent;
    private int authorId;
    private int quantitySold;
    private int categoryId;
    private boolean hot;
    private boolean news;
    private MultipartFile images;

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

    public double getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(double discount_percent) {
        this.discount_percent = discount_percent;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public boolean isNews() {
        return news;
    }

    public void setNews(boolean news) {
        this.news = news;
    }

    public MultipartFile getImages() {
        return images;
    }

    public void setImages(MultipartFile images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "BookInput{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year_public=" + year_public +
                ", total_page=" + total_page +
                ", publisher='" + publisher + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", discount_percent=" + discount_percent +
                ", authorId=" + authorId +
                ", quantitySold=" + quantitySold +
                ", categoryId=" + categoryId +
                ", hot=" + hot +
                ", news=" + news +
                ", images=" + images +
                '}';
    }
}
