package org.launchcode.bookworm.data.model;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String author;
    private String image;
    private String description;


    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public int getId() {
        return id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
