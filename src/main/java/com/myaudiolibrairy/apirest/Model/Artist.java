package com.myaudiolibrairy.apirest.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class Artist {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column (name="name")
    private String name;


    @OneToMany (mappedBy="artist", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    //Utilisation de CascadeType.REMOVE pour pouvoir supprimer un artiste dont des albums sont enregistrés dans la base
    @JsonIgnoreProperties ("artist")
    private List<Album> albums;


    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
