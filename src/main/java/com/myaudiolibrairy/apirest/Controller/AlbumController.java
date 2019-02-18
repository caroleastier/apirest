package com.myaudiolibrairy.apirest.Controller;

import com.myaudiolibrairy.apirest.Model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.myaudiolibrairy.apirest.repository.AlbumRepository;
import com.myaudiolibrairy.apirest.repository.ArtistRepository;

@RestController

@RequestMapping (value="/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @RequestMapping (method = RequestMethod.GET, value = "/{id}")
    public Album findById (@PathVariable("id") Long id) {
        return albumRepository.findOne(id);
    }

    @RequestMapping (method = RequestMethod.POST, consumes = "application/json", value = "")
    public Album addAlbum(@RequestBody Album album){
        if (album.getTitle().equals("")){
            throw new IllegalArgumentException("Le champs titre ne peut pas être vide");
        }
        return albumRepository.save(album);
    }

    @RequestMapping (method = RequestMethod.DELETE, value="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable ("id") Long id){
        Album album=albumRepository.findOne(id);
        albumRepository.delete(album);
    }

}
