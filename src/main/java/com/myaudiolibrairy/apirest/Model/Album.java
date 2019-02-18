package com.myaudiolibrairy.apirest.Model;


import javax.persistence.*;

@Entity
public class Album {


    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column (name="title")
    private String title;


    @ManyToOne
    @JoinColumn (name="artistId")
    private Artist artist;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
