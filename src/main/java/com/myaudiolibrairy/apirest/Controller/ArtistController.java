package com.myaudiolibrairy.apirest.Controller;

import com.myaudiolibrairy.apirest.Model.Album;
import com.myaudiolibrairy.apirest.Model.Artist;
import com.myaudiolibrairy.apirest.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.myaudiolibrairy.apirest.repository.ArtistRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController

@RequestMapping (value = "/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;



    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Artist findById(@PathVariable("id") Long id) {
        if (id==0) {
            throw new EntityNotFoundException("L'artiste d'identifiant " + id  + " n'existe pas." );
        }
        if (id==null) {
            throw new EntityNotFoundException("L'artiste d'identifiant " + id + " n'existe pas." );
        }
        return artistRepository.findOne(id);
    }


    @RequestMapping(value = "")
    public Page<Artist> findAll (
            @RequestParam(value = "name",required = false) String name,
            @RequestParam ("page") Integer page,
            @RequestParam ("size") Integer size,
            @RequestParam ("sortDirection") String sortDirection,
            @RequestParam ("sortProperty") String sortProperty
    ) {
        Pageable pageRequest = new PageRequest(page, size, Sort.Direction.fromString(sortDirection), sortProperty);
        System.out.println("findAll called name=" + name);
        if (name==null)
            return artistRepository.findAll(pageRequest);
        else return artistRepository.findByNameIsContaining(name, pageRequest);
    }

    // Après des difficultés constantes et changeantes d'avoir des conflits entre les deux @RequestMapping qui ne se différenciaient que par leurs @RequestParams, idée de faire une seule reuqête avec le paramètre "name" non obligatoire et qui appellent deux méthodes différentes selon la présence ou non de ce paramètre.

   /*@RequestMapping (value = "")
    public Page<Artist> findAll (
            @RequestParam ("page") Integer page,
            @RequestParam ("size") Integer size,
            @RequestParam ("sortDirection") String sortDirection,
            @RequestParam ("sortProperty") String sortProperty
            ) {
        Pageable pageRequest = new PageRequest(page, size, Sort.Direction.fromString(sortDirection), sortProperty);
        return artistRepository.findAll(pageRequest);
    }*/

    @RequestMapping (method = RequestMethod.POST, consumes = "application/json", value = "")
    public Artist createArtist(@RequestBody Artist artist) {
        if (artistRepository.findByName(artist.getName())!= null) {
            throw new EntityExistsException("L'artiste " + artist.getName() + " est déjà présent dans la liste.");
        }
        if (artist.getName().equals("")){
            throw new IllegalArgumentException("Vous devez indiquer le nom d'artiste");
        }
        return artistRepository.save(artist);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json", value="/{id}")
    public Artist updateArtist(@PathVariable Long id, @RequestBody Artist artist){
        return artistRepository.save(artist);
    }

    @RequestMapping (method = RequestMethod.DELETE, value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable ("id") Long id){
        Artist artist=artistRepository.findOne(id);
        artistRepository.delete(artist);
    }

}

